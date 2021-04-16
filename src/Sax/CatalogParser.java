/* DESARROLLO III (15 de abril de 2021) - Tarea: Procesar XML con el API SA

Modificar el ejemplo de XML_DEMO del "Catálogo" para además de calcular la media del precio de los CDs
en el catálogo, el programa al final también muestre la desviación estándar (DS) de los precios. Algunas
recomendaciones:

1. Para calcular la DS se requiere primero obtener la media. Por lo tanto, es conveniente que los precios de
   cada CD se almacenen en un ArrayList<Double>. De esta manera, se podra calcular la diferencia de cada valor
   con respecto a la media como se indica en la fórmula.

2. Es recomendable que el cálculo de la DS se efectue en un método que se mande llamar cuando se llegue al final
   del documento.
*/

package Sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class CatalogParser {

    public static final boolean VALIDATE = false;

    public static void main(String[] args) {

        try {
            // Crear el parser
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Realizar validación
            factory.setValidating(VALIDATE);

            SAXParser saxParser = factory.newSAXParser();

            XMLReader reader = null;
            reader = saxParser.getXMLReader();

            // Definir clase para manejo de errores
            reader.setErrorHandler(new CatalogErrorHandler());

            // Definir clase con métodos callback para manejar eventos  
            CatalogItemHandler handler = new CatalogItemHandler();
            
            reader.setContentHandler(handler);
            
            InputSource is = new InputSource("cd_catalog.xml");
            reader.parse(is);
            
        } catch (Exception e) {
            System.out.println("Oops!");
            e.printStackTrace();
        }
    }
}
