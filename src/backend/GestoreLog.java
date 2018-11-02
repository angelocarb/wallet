package backend;

import middleware.GestoreXML;
import java.io.DataInputStream;
import java.io.*;
import java.net.*;
import java.nio.file.*;


public class GestoreLog {
    private int portaServer;
    
    public GestoreLog(int portaServer){
        this.portaServer = portaServer;
    }
    
    
    public void start(){
        while(true){
            System.out.println("In attesa di connessioni...");
            
            try(ServerSocket servSock = new ServerSocket(portaServer);
                Socket sock = servSock.accept();
                    DataInputStream dis = new DataInputStream(sock.getInputStream());
                    ){
                String event = dis.readUTF();
                System.out.println("Ricevuto: " + event);
                appendiFile(event);
                
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void appendiFile(String xml){
        GestoreXML xmlManager = new GestoreXML(null, "../../log/evento.xsd");
        
        if (!xmlManager.validaXML(xml)) {
            return;
        }
        
        String ls = System.lineSeparator();
        xml+=ls+ls;

        try {
            if (!Files.exists(Paths.get("../../log/log.xml")))
                Files.createFile(Paths.get("../../log/log.xml"));

            Files.write(Paths.get("../../log/log.xml"), xml.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Impossibile scrivere sul file di log" + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        GestoreLog logServer = new GestoreLog(8080);
        logServer.start();
    }
}
