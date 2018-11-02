
package middleware;
import java.io.*;

public class Evento implements Serializable {

    public final String nomeApp;
    public final String tag;
    public String clientIP;
    public long timestamp;

    public Evento(String nomeApp, String tag, String clientIP, long timestamp) {

        this.nomeApp = nomeApp;
        this.tag = tag;
        this.clientIP = clientIP;
        this.timestamp = timestamp;

    }

}