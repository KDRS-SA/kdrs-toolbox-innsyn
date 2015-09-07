package no.arkivlab.innsyn.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriter {

	protected static boolean REQUIRED = true;
	protected static boolean NOTREQUIRED = false;
	protected static boolean OPEN = true;
	protected static boolean CLOSE = false;
	
	protected XMLStreamWriter  writer;

	protected int indentationLevel = -1;
	protected String indentationString = "    ";
	
	public XMLWriter(String directory) throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException, FactoryConfigurationError {
			OutputStream outputStream = new FileOutputStream(new java.io.File("arkivstrutur.xml"));
			writer = XMLOutputFactory.newInstance().createXMLStreamWriter(
					System.out);
	                //new OutputStreamWriter(outputStream, "utf-8"));
			
			writer.writeStartDocument();
	 }
}
