package Sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author rnavarro
 */
public class CatalogErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        display("Warning: ", exception);
        throw (exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        display("Error: ", exception);
        throw (exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        display("Fatal Error: ", exception);
        throw (exception);
    }

    private void display(String type, SAXParseException e) {
        System.out.println(type + ": " + e.getMessage());
        System.out.println("Line " + e.getLineNumber() + " Column " + e.getColumnNumber());
        System.out.println("System ID: " + e.getSystemId());
    }

}
