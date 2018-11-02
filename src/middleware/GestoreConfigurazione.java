package middleware;
import frontend.ParametriConfigurazione;


public class GestoreConfigurazione {

    private static ParametriConfigurazione config;

    public static ParametriConfigurazione getConfigurazione() {

        if (config == null) {
            //config = caricaConfigurazione("./res/xml/configurazioni.xml", "./res/xml/configurazioni.xsd");
            config = caricaConfigurazione("../../xml/configurazioni.xml", "../../xml/configurazioni.xsd");
        }

        return config;
    }

    private static ParametriConfigurazione caricaConfigurazione(String xmlPath, String xsdPath) {

        GestoreXML xmlManager = new GestoreXML(xmlPath, xsdPath);
        xmlManager.getStreamXML().alias("Configurazioni", ParametriConfigurazione.class);
        return (ParametriConfigurazione) xmlManager.caricaOggettoXML();

    }

}
