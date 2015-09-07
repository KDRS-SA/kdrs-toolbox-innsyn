package no.arkivlab.innsyn.export;

import javax.xml.stream.XMLStreamException;

import no.arkivlab.innsyn.models.n5.Class;
import no.arkivlab.innsyn.models.n5.ClassificationSystem;
import no.arkivlab.innsyn.models.n5.Fonds;
import no.arkivlab.innsyn.models.n5.Series;

public interface IArkivstrukturHandler {
	public boolean startExportFromFonds(Fonds fonds); 
	public void processSeries(Fonds fonds) throws XMLStreamException;	
	public boolean processClassificationSystem(Series series) throws XMLStreamException;
	public boolean processClass(ClassificationSystem classificationSystem) throws XMLStreamException;
	public boolean processFile(Class klass) throws XMLStreamException;
	public boolean processFile(Series series) throws XMLStreamException;
}
