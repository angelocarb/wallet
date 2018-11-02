
package middleware;
import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.net.*;

public class ClientLog extends Thread {
    private final Evento evento;
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final String IP_ADDRESS = "0.0.0.0";
    private String stringaXml;

    private ClientLog(Evento evento) {

        this.evento = evento;

    }

    private String serializzaEvento() {
        XStream xstream = new XStream();
        xstream.alias("Evento", Evento.class);
        xstream.useAttributeFor(Evento.class,"nomeApp");

        stringaXml = XML_HEADER + System.lineSeparator()+ xstream.toXML(evento);

        return stringaXml;
    }

    @Override
    public void run() {
        String serverLogAddess = GestoreConfigurazione.getConfigurazione().IPServerLogXML;
        Integer serverLogPort = GestoreConfigurazione.getConfigurazione().PortaServerLogXML;

        try (Socket socket = new Socket(serverLogAddess, serverLogPort);

             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());){
            dos.writeUTF(serializzaEvento());

        } catch (UnknownHostException e) {
                 System.out.println("Errore durante l'invio del log: " + e.getLocalizedMessage());
        } catch (IOException e) {
                 
                 System.out.println("Nessuna connessione con il Server: " + e.getLocalizedMessage());
        }

    }

    public static void pressioneInserisci(String nomeApp){
        Evento e = new Evento(nomeApp , "PRESSIONE PULSANTE INSERISCI" , IP_ADDRESS , System.currentTimeMillis());
        (new ClientLog(e)).start();
    }
    
    public static void pressioneElimina(String nomeApp){
        Evento e = new Evento(nomeApp , "PRESSIONE PULSANTE ELIMINA" , IP_ADDRESS , System.currentTimeMillis());
        (new ClientLog(e)).start();
    }

    public static void aperturaApplicazione(String nomeApp) {

        Evento e = new Evento(nomeApp, "AVVIO APPLICAZIONE", IP_ADDRESS, System.currentTimeMillis());

        (new ClientLog(e)).start();

    }

    public static void chiusuraApplicazione(String nomeApp) {

        Evento e = new Evento(nomeApp, "CHIUSURA APPLICAZIONE", IP_ADDRESS, System.currentTimeMillis());

        (new ClientLog(e)).start();

    }
}
