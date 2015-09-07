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
import no.arkivlab.innsyn.models.n5.FondsCreator;
import no.arkivlab.innsyn.models.n5.Record;
import no.arkivlab.innsyn.models.n5.Series;
import no.arkivlab.innsyn.models.n5.StorageLocation;

public class ArkivstrukturWriter extends XMLWriter{

	public ArkivstrukturWriter() throws FileNotFoundException,
			UnsupportedEncodingException, XMLStreamException,
			FactoryConfigurationError {
		super(null);

	}

	protected void printSingleStartElement(String elementName) throws XMLStreamException {

		indentationLevel++;
		for (int i=0; i<indentationLevel; i++) {
			writer.writeCharacters(indentationString);	
	 	}
		writer.writeStartElement(elementName);
		writer.writeCharacters(System.lineSeparator());					
	}

	protected void printSingleEndElement() throws XMLStreamException {

		for (int i=0; i<indentationLevel; i++) {
			writer.writeCharacters(indentationString);	
		}
		writer.writeEndElement();
		writer.writeCharacters(System.lineSeparator());
		indentationLevel--;
	}

	protected void printElement(String data, String elementName, boolean required) throws XMLStreamException {
		
		if (data != null) {
			for (int i=0; i<indentationLevel; i++) {
				writer.writeCharacters(indentationString);	
		 	}				
			writer.writeStartElement(elementName);
			writer.writeCharacters(data);
			writer.writeEndElement();
			writer.writeCharacters(System.lineSeparator());
		}		
		else if (required == true) {
			 //System.out.println();
			 //logger.error(elementName + " is required, but has no value ");
		}

	}
	
	protected void prePrintFonds(Fonds fonds) throws XMLStreamException {
		
		printSingleStartElement("arkiv");				
		indentationLevel++;
		printElement(fonds.getSystemId(), "systemId", REQUIRED);
		printElement(fonds.getTitle(), "tittel", REQUIRED);
		printElement(fonds.getDescription(), "beskrivelse", REQUIRED);
		printElement(fonds.getFondsStatus(), "arkivstatus", REQUIRED);
		printElement(fonds.getDocumentMedium(), "dokumentmedium", REQUIRED);
		if (fonds.getCreatedDate() != null) {
			printElement(fonds.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}
		printElement(fonds.getCreatedBy(), "opprettetAv", REQUIRED);
		if (fonds.getFinalisedDate() != null) {
			printElement(fonds.getFinalisedDate().toString(), "avsluttetDato", REQUIRED);			
		}
		else {
			printElement(null, "avsluttetDato", REQUIRED);
		}
		printElement(fonds.getFinalisedBy(), "avsluttetBy", REQUIRED);
		indentationLevel--;		
	}
	
	protected void prePrintFondsCreator(FondsCreator fondsCreator) throws XMLStreamException {	
		printSingleStartElement("arkivskaper");				
		indentationLevel++;
		printElement(fondsCreator.getFondsCreatorId(), "arkivskaperID", REQUIRED);
		printElement(fondsCreator.getFondsCreatorName(), "arkivskaperNavn", REQUIRED);
		printElement(fondsCreator.getDescription(), "beskrivelse", REQUIRED);
		indentationLevel--;		
	}
	
	protected void prePrintSeries(Series series) throws XMLStreamException {
		printSingleStartElement("arkivdel");
		indentationLevel++;
		printElement(series.getSystemId(), "systemId", REQUIRED);
		printElement(series.getTitle(), "tittel", REQUIRED);
		printElement(series.getDescription(), "beskrivelse", REQUIRED);
		printElement(series.getSeriesStatus(), "arkivdelstatus", REQUIRED);
		printElement(series.getDocumentMedium(), "dokumentmedium", REQUIRED);
		if (series.getCreatedDate() != null) {
			printElement(series.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}
		printElement(series.getCreatedBy(), "opprettetAv", REQUIRED);
		if (series.getFinalisedDate() != null) {
			printElement(series.getFinalisedDate().toString(), "avsluttetDato", REQUIRED);
		}
		else {
			printElement(null, "avsluttetDato", REQUIRED);
		}
		printElement(series.getFinalisedBy(), "avsluttetBy", REQUIRED);
		indentationLevel--;		
	}
	
	protected void prePrintClassificationSystem(ClassificationSystem classificationSystem) throws XMLStreamException {		
		printSingleStartElement("klassifikasjonSystem");				
		indentationLevel++;
		printElement(classificationSystem.getSystemId(), "systemId", REQUIRED);
		printElement(classificationSystem.getTitle(), "tittel", REQUIRED);
		printElement(classificationSystem.getDescription(), "beskrivelse", REQUIRED);
		
		printElement(classificationSystem.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		if (classificationSystem.getCreatedDate() != null) {
			printElement(classificationSystem.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}		
		printElement(classificationSystem.getCreatedBy(), "opprettetAv", REQUIRED);
		if (classificationSystem.getFinalisedDate() != null) {
			printElement(classificationSystem.getFinalisedDate().toString(), "avsluttetDato", REQUIRED);			
		}
		else {
			printElement(null, "avsluttetDato", REQUIRED);			
		}
		printElement(classificationSystem.getFinalisedBy(), "avsluttetBy", REQUIRED);
		indentationLevel--;		
	}
	
	protected void prePrintClass(Class klass) throws XMLStreamException {
		printSingleStartElement("klasse");				
		indentationLevel++;
		printElement(klass.getSystemId(), "systemId", REQUIRED);
		printElement(klass.getTitle(), "tittel", REQUIRED);
		printElement(klass.getDescription(), "beskrivelse", REQUIRED);
		if (klass.getCreatedDate() != null) {
			printElement(klass.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}
		printElement(klass.getCreatedBy(), "opprettetAv", REQUIRED);

		if (klass.getFinalisedDate() != null) {
			printElement(klass.getFinalisedDate().toString(), "avsluttetDato", REQUIRED);
		}
		else {
			printElement(null, "avsluttetDato", REQUIRED);			
		}
		printElement(klass.getFinalisedBy(), "avsluttetBy", REQUIRED);
		indentationLevel--;		
	}
	
	protected void prePrintFile(File file) throws XMLStreamException {
		printSingleStartElement("mappe");	
		indentationLevel++;
		printElement(file.getSystemId(), "systemId", REQUIRED);
		printElement(file.getTitle(), "tittel", REQUIRED);
		printElement(file.getDescription(), "beskrivelse", REQUIRED);
		printElement(file.getDocumentMedium(), "dokumentmedium", REQUIRED);
		if (file.getCreatedDate() != null) {		
			printElement(file.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}
		printElement(file.getCreatedBy(), "opprettetAv", REQUIRED);
		if (file.getFinalisedDate() != null) {
			printElement(file.getFinalisedDate().toString(), "avsluttetDato", REQUIRED);
		}
		else {
			printElement(null, "avsluttetDato", REQUIRED);			
		}
		printElement(file.getFinalisedBy(), "avsluttetBy", REQUIRED);
		indentationLevel--;		
	}

	protected void prePrintRecord(Record record) throws XMLStreamException {
		printSingleStartElement("registrering");	
		indentationLevel++;
		printElement(record.getSystemId(), "systemId", REQUIRED);
		if (record.getCreatedDate() != null) {		
			printElement(record.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}
		printElement(record.getCreatedBy(), "opprettetAv", REQUIRED);
		if (record.getArchivedDate() != null) {
			printElement(record.getArchivedDate().toString(), "avsluttetDato", REQUIRED);
		}
		else {
			printElement(null, "avsluttetDato", REQUIRED);			
		}
		printElement(record.getArchivedBy(), "avsluttetBy", REQUIRED);
		indentationLevel--;		
	}

	
	protected void prePrintDocumentDescription(DocumentDescription documentDescription, HashSet<Author> authors) throws XMLStreamException {
		printSingleStartElement("dokumentbeskrivelse");	
		indentationLevel++;
		printElement(documentDescription.getSystemId(), "systemId", REQUIRED);
		printElement(documentDescription.getDocumentType(), "dokumenttype", REQUIRED);
		printElement(documentDescription.getDocumentStatus(), "dokumentstatus", REQUIRED);
		printElement(documentDescription.getTitle(), "tittel", REQUIRED);
		printElement(documentDescription.getDescription(), "beskrivelse", !REQUIRED);


		for (Author author : authors) {
			printElement(author.getAuthor(), "forfatter", !REQUIRED);    
		}		

		if (documentDescription.getCreatedDate() != null) {		
			printElement(documentDescription.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}
		printElement(documentDescription.getCreatedBy(), "opprettetAv", REQUIRED);
		printElement(documentDescription.getDocumentMedium(), "dokumentmedium", REQUIRED);
		// get the related storageLocation
		StorageLocation referenceStorageLocation = documentDescription.getReferenceStorageLocation();
		if (referenceStorageLocation != null) {
			printElement(referenceStorageLocation.getStorageLocation(), "oppbevaringssted", !REQUIRED);    
		}		
		// TODO REFERANSE ARKIVDEL
		//printElement(documentDescription., "", REQUIRED);
		printElement(documentDescription.getAssociatedWithRecordAs(), "tilknyttetRegistreringSom", REQUIRED);
		if (documentDescription.getDocumentNumber() != null) {
			printElement(documentDescription.getDocumentNumber().toString(), "dokumentnummer", REQUIRED);
		}
		else {
			printElement(null, "dokumentnummer", REQUIRED);
		}
		
		if (documentDescription.getAssociationDate() != null) {
			printElement(documentDescription.getAssociationDate().toString(), "tilknyttetDato", REQUIRED);
		}
		else {
			printElement(null, "tilknyttetDato", REQUIRED);			
		}
		printElement(documentDescription.getAssociatedBy(), "tilknyttetAv", REQUIRED);
		indentationLevel--;		
	}
	
	protected void prePrintDocumentObject(DocumentObject documentObject) throws XMLStreamException {
		printSingleStartElement("dokumentobjekt");	
		indentationLevel++;

		if (documentObject.getVersionNumber() != null) {
			printElement(documentObject.getVersionNumber().toString(), "versjonsnummer", REQUIRED);
		}
		else {
			printElement(null, "versjonsnummer", REQUIRED);
		}
		
		printElement(documentObject.getVariantFormat(), "variantformat", REQUIRED);		
		printElement(documentObject.getFormat(), "format", REQUIRED);
		printElement(documentObject.getFormatDetails(), "formatDetaljer", REQUIRED);
		
		if (documentObject.getCreatedDate() != null) {		
			printElement(documentObject.getCreatedDate().toString(), "opprettetDato", REQUIRED);
		}
		else {
			printElement(null, "opprettetDato", REQUIRED);
		}
		printElement(documentObject.getCreatedBy(), "opprettetAv", REQUIRED);
		
		
		printElement(documentObject.getReferenceDocumentFile(), "referanseDokumentfil", REQUIRED);
		printElement(documentObject.getChecksum(), "sjekksum", REQUIRED);
		printElement(documentObject.getChecksumAlgorithm(), "sjekksumAlgoritme", REQUIRED);
		
		if (documentObject.getFileSize() != null) {
			printElement(documentObject.getFileSize().toString(), "filstoerrelse", REQUIRED);
		}
		else
			printElement(null, "filstoerrelse", REQUIRED);
		indentationLevel--;		
	}
	
	protected void postPrintFonds(Fonds fonds) throws XMLStreamException {	
		// close the opened <arkiv> element
		printSingleEndElement();
		writer.flush();		
	}
	
	protected void postPrintFondsCreator(FondsCreator fondsCreator) throws XMLStreamException {	
		// close the opened <arkivskaper> element
		printSingleEndElement();
		writer.flush();		
	}
	protected void postPrintSeries(Series series) throws XMLStreamException {
		// close the opened <arkivdel> element
		printSingleEndElement();		
		writer.flush();		
	}
	
	protected void postPrintClassifcationSystem(ClassificationSystem classificationSystem) throws XMLStreamException {
		// close the opened <klassifikasjonsystem> element
		printSingleEndElement();		
		writer.flush();		
	}
	
	protected void postPrintClass(Class klass) throws XMLStreamException {
		// close the opened <klasse> element
		printSingleEndElement();		
		writer.flush();		
	}

	protected void postPrintFile(File file) throws XMLStreamException {
		// close the opened <mappe> element
		printSingleEndElement();		
		writer.flush();		
	}
	
	protected void postPrintRecord(Record record) throws XMLStreamException {
		// close the opened <record> element
		printSingleEndElement();		
		writer.flush();		
	}	
	
	protected void postPrintDocumentDescription(DocumentDescription documentDescription) throws XMLStreamException {
		// close the opened <documentDescription> element
		printSingleEndElement();		
		writer.flush();		
	}
	
	protected void postPrintDocumentObject(DocumentObject documentObject) throws XMLStreamException {
		// close the opened <documentObject> element
		printSingleEndElement();		
		writer.flush();		
	}
}
