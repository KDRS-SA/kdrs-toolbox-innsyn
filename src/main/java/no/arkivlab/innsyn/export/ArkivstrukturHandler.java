package no.arkivlab.innsyn.export;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import no.arkivlab.innsyn.models.n5.Author;
import no.arkivlab.innsyn.models.n5.Class;
import no.arkivlab.innsyn.models.n5.ClassificationSystem;
import no.arkivlab.innsyn.models.n5.DocumentDescription;
import no.arkivlab.innsyn.models.n5.DocumentObject;
import no.arkivlab.innsyn.models.n5.File;
import no.arkivlab.innsyn.models.n5.Fonds;
import no.arkivlab.innsyn.models.n5.Record;
import no.arkivlab.innsyn.models.n5.Series;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;


/*
 * TODO: Submappe and subfonds and the various allowed structures
 *  
 */

public class ArkivstrukturHandler implements IArkivstrukturHandler {

	protected ArkivstrukturWriter arkivstrukturWriter;
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	protected Transaction tx;
	protected StatelessSession statelessSession; 
	
	public ArkivstrukturHandler() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException, FactoryConfigurationError {
		super();
		arkivstrukturWriter = new ArkivstrukturWriter();		
	}

	public boolean startExportFromFonds(Fonds fonds) {
		boolean returnValue = true;
		
		this.statelessSession = sessionFactory.openStatelessSession();
		
		try {

		    this.tx = statelessSession.beginTransaction();

		    ScrollableResults scrollableResults = statelessSession.createQuery("from Fonds").scroll(ScrollMode.FORWARD_ONLY);
		    while (scrollableResults.next()) {		    	
		    	Fonds subFonds = (Fonds) scrollableResults.get()[0];
		    	arkivstrukturWriter.prePrintFonds(subFonds);
		    	processSeries(subFonds);
		    	arkivstrukturWriter.postPrintFonds(subFonds);
		    }
		    tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			statelessSession.close();
		}	  
		return returnValue ;
	}
	
	public void processSeries(Fonds fonds) throws XMLStreamException {
		
	    Long id = fonds.getId();	    
	    Query query = (Query) statelessSession.createQuery("from Series where referenceFonds.id = :refFonds")
				.setParameter("refFonds", id)
				.setReadOnly(true);
	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);	    
	    while (scrollableResults.next()) {		    	
	    	Series series = (Series) scrollableResults.get()[0];
	    	arkivstrukturWriter.prePrintSeries(series);
	    	boolean anythingProcessed = processClassificationSystem(series);
	    	if (anythingProcessed == false) {
	    		processFile(series); 
	    	} // if
	    	arkivstrukturWriter.postPrintSeries(series);
	    }  // while			
	}
	
	public boolean processClassificationSystem(Series series) throws XMLStreamException {
		
		boolean anythingProcessed = false;

	    Long id = series.getId();		    
	    //from DocumentDescription d, IN (d.referenceRecord) rR where rR.id = :referenceRecordId"
	    													//SELECT u FROM User u, IN (u.addresses) AS a WHERE a.city = 'Sydney'  
	    Query query = (Query) statelessSession.createQuery("from ClassificationSystem cS, IN (cS.referenceSeries) AS rS WHERE rS.id = :refS")
	    //		Query query = (Query) statelessSession.createQuery("from ClassificationSystem where referenceSeries.id = :refSeries")
	    		
				.setParameter("refS", id)
				.setReadOnly(true);
	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);	  
	    while (scrollableResults.next()) {
	    	anythingProcessed = true;
	    	ClassificationSystem classificationSystem = (ClassificationSystem) scrollableResults.get()[0];
	    	arkivstrukturWriter.prePrintClassificationSystem(classificationSystem);	    		    
	    	processClass(classificationSystem);	    	
	    	arkivstrukturWriter.postPrintClassifcationSystem(classificationSystem);
	    }
		return anythingProcessed;
	}
	
	public boolean processClass(ClassificationSystem classificationSystem) throws XMLStreamException {
		boolean anythingProcessed = false;
		
	    Long id = classificationSystem.getId();		    
	    Query query = (Query) statelessSession.createQuery("from Class where referenceClassificationSystem.id = :refClassificationSystem")
				.setParameter("refClassificationSystem", id)
				.setReadOnly(true);
	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);	   
	    while (scrollableResults.next()) {	
	    	anythingProcessed = true;
	    	Class klass = (Class) scrollableResults.get()[0];
	    	arkivstrukturWriter.prePrintClass(klass);
	    	boolean result = processFile(klass);
	    	if (result == false) {
	    		//result = processRecord(klass, series);
	    	}
	    	if (result == false) {
	    		//result = processDocumentDescription(klass, series);
	    	}
	    	arkivstrukturWriter.postPrintClass(klass);
	    }
		return anythingProcessed;
	}
	
	public boolean processFile(Series series) throws XMLStreamException {
		boolean anythingProcessed = false;
	    		    
	    Long referenceSeriesId = series.getId();
	    
	    Query query = (Query) statelessSession.createQuery("from File where referenceSeries.id = :referenceSeriesId")
				.setParameter("referenceSeriesId", referenceSeriesId)
				.setReadOnly(true);
	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
	    
	    while (scrollableResults.next()) {	
	    	anythingProcessed = true;
	    	File file = (File)scrollableResults.get()[0];
	    	arkivstrukturWriter.prePrintFile(file);	    	
	    	boolean result = processRecord(file);
	    	if (result == false) {
	    		//result = processDocumentDescription(klass, series);
	    	}
	    	
	    	arkivstrukturWriter.postPrintFile(file);
	    }
		return anythingProcessed;

	}
	
	public boolean processFile(Class klass) throws XMLStreamException {
		boolean anythingProcessed = false;

	    Long referenceClassId = klass.getId();		    
	    
	    Query query = (Query) statelessSession.createQuery("from File where referenceClass.id = :refClassId")
				.setParameter("refClassId", referenceClassId)
				.setReadOnly(true);
	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
	    
	    while (scrollableResults.next()) {	
	    	anythingProcessed = true;
	    	File file = (File)scrollableResults.get()[0];
	    	arkivstrukturWriter.prePrintFile(file);	    	
	    	boolean result = processRecord(file);
	    	if (result == false) {
	    		//result = processDocumentDescription(klass, series);
	    	}
	    	
	    	arkivstrukturWriter.postPrintFile(file);
	    }
		return anythingProcessed;
	}
	
	public boolean processRecord(File file) throws XMLStreamException {
		boolean anythingProcessed = false;
	    Long referenceFileId = file.getId();
	    
	    Query query = (Query) statelessSession.createQuery("from Record where referenceFile.id = :referenceFileId")
				.setParameter("referenceFileId", referenceFileId)
				.setReadOnly(true);
	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);	    
	    while (scrollableResults.next()) {
	    	anythingProcessed = true;
	    	Record record = (Record)scrollableResults.get()[0];
	    	arkivstrukturWriter.prePrintRecord(record);
	    	processDocumentDescription(record);
	    	arkivstrukturWriter.postPrintRecord(record);
	    }
		return anythingProcessed;
	}

	public boolean processDocumentDescription(Record record) throws XMLStreamException {
		boolean anythingProcessed = false;
	    Long referenceRecordId = record.getId();
	    
	    
	    
	    
	    Query query = (Query) statelessSession.createQuery("from DocumentDescription d, IN (d.referenceRecord) rR where rR.id = :referenceRecordId")
				.setParameter("referenceRecordId", referenceRecordId)
				.setReadOnly(true);
	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);	    
	    while (scrollableResults.next()) {
	    	anythingProcessed = true;
	    	DocumentDescription documentDescription = (DocumentDescription)scrollableResults.get()[0];
	    
	    	// I have to get the authors etc here, because I am using stateless sessions 
	    	Query queryAuthors = (Query) statelessSession.createQuery("from DocumentDescription d, IN (d.referenceAuthor) rA where rA.id = :referenceDocumentDescriptionId")
					.setParameter("referenceDocumentDescriptionId", referenceRecordId)
					.setReadOnly(true);
	    	ScrollableResults scrollableAuthorResults = queryAuthors.scroll(ScrollMode.FORWARD_ONLY);
	    	HashSet<Author> author = new HashSet<Author> ();
	    	
	    	while (scrollableAuthorResults.next()) {
	    		author.add((Author)scrollableAuthorResults.get()[0]);
	    	}
	    	arkivstrukturWriter.prePrintDocumentDescription(documentDescription, author);
	    	processDocumentObject(documentDescription);
	    	arkivstrukturWriter.postPrintDocumentDescription(documentDescription);
	    }
		return anythingProcessed;
	}

	public boolean processDocumentObject(DocumentDescription documentDescription) throws XMLStreamException {
		boolean anythingProcessed = false;
	    Long referenceDocumentDescriptionId =  documentDescription.getId();
	    
	    Query query = (Query) statelessSession.createQuery("from DocumentObject where referenceDocumentDescription.id = :referenceDocumentDescriptionId")
				.setParameter("referenceDocumentDescriptionId", referenceDocumentDescriptionId)
				.setReadOnly(true);

	    
	    ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);	    
	    while (scrollableResults.next()) {
	    	anythingProcessed = true;
	    	DocumentObject documentObject = (DocumentObject)scrollableResults.get()[0];
	    	arkivstrukturWriter.prePrintDocumentObject(documentObject);

	    	arkivstrukturWriter.postPrintDocumentObject(documentObject);
	    }
		return anythingProcessed;
	}
	
	
}
