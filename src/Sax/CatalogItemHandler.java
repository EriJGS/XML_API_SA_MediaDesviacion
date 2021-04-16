package Sax;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CatalogItemHandler extends DefaultHandler {

    private double sum = 0.0;
    private int count = 0;
    private double media = 0.0;
    private double desviacion = 0.0;
 
    private String title;
    private String price;
    ArrayList<Double> precios = new ArrayList<>();

    private boolean bPrice = false;
    private boolean bTitle = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Se invoca cuando se detecta el inicio del elemento e.g <student>    
        if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
            count++;
        } else if (qName.equalsIgnoreCase("title")) {
            bTitle = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Se invoca cuando se detecta el cierre del elemento e.g </student> 
        if (qName.equalsIgnoreCase("cd")) {
            System.out.printf("%-25s - %s\n", title, price);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        // Se invoca cuando para obtener los datos contenidos en un elemento 
        if (bPrice) {
            price = new String(ch, start, length);
 
            precios.add(Double.parseDouble(price));
            
            sum = sum + Double.parseDouble(price);
            bPrice = false;
        } else if (bTitle) {
            title = new String(ch, start, length);
            bTitle = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        // Al final del documento se imprimirá la media y desviación
        media = sum / count;
        
        double sumaV = 0;
        for (Double precio : precios) {
            sumaV += Math.pow((precio - media), 2);
        }
        
        desviacion = Math.sqrt(sumaV / count);
        
        System.out.printf("\nAverage price: %19f \nDeviation: %23f \n", media, desviacion);
    }

}
