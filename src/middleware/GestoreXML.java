
package middleware;
import com.thoughtworks.xstream.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.validation.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.nio.file.*;

public class GestoreXML {
     private final String xmlFilePath;
    private final String xsdFilePath;

    private final XStream xStream;

    public GestoreXML(String xmlPath, String xsdPath) {

        this.xmlFilePath = xmlPath;
        this.xsdFilePath = xsdPath;

        xStream = new XStream();

    }

    /** Dato il percorso del file XML (se valido) genera un oggetto che viene restituito dalla funzione
     * */
    public Object caricaOggettoXML() {

        try {
            String inputXML = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
            if (!validaXML(inputXML)) {
                return null;
            }

            return xStream.fromXML(inputXML);

        } catch (IOException e) {
            System.out.println("Impossibile caricare il file XML: " + e.getLocalizedMessage());
            return null;
        }

    }

    /** Validazione della stringa utilizzando InputSource e StringReader
     *  in modo da poter validare non solo i file
     * */
    public boolean validaXML(String xml) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document XMLDoc = documentBuilder.parse(new InputSource(new StringReader(xml)));

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(new File(xsdFilePath)));

            schema.newValidator().validate(new DOMSource(XMLDoc));

            return true;

        } catch (ParserConfigurationException e) {
            System.out.println("File xml non valido, " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("Errore di I/O: " + e.getLocalizedMessage());
        } catch (SAXException e) {
            System.out.println("Errore: " + e.getLocalizedMessage());
        }

        return false;
    }

    public XStream getStreamXML() {
        return xStream;
    }
}
