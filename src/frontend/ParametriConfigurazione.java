package frontend;

import java.io.Serializable;

public class ParametriConfigurazione implements Serializable { //(01)
    public String IPServerLogXML;
    public String IPClientLogXML;
    public int PortaServerLogXML;
    public String UtenteDB;
    public String PasswordDB;  
    public String HostnameDB;
    public int PortaDB;  
}



//(01) Classe contenitore per i vari parametri contenuti in config;