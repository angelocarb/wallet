/* Classe che contiene i parametri di configurazione necessari per il funzionamento dell'applicativo */

package frontend;



public class ParametriDB {
   
    private final String UtenteDB;
    private final String nomeDB;
    private final String PasswordDB;  
    private final String HostnameDB;
    private final String PortaDB; 
    
    public ParametriDB(String utenteDB , String passwordDB , String hostnameDB , String portaDB , String nomeDB){
        this.UtenteDB = utenteDB;
        this.PasswordDB = passwordDB;
        this.HostnameDB = hostnameDB;
        this.PortaDB = portaDB;
        this.nomeDB = nomeDB;
    }
    
    public String getUtente(){
        return UtenteDB;
    }
    
    public String getPWD(){
        return PasswordDB;
    }
    
     public String getHostnameDB(){
        return HostnameDB;
    }
     
      public String getPortaDB(){
        return PortaDB;
    }
      
      public String getNomeDB(){
          return nomeDB;
      }
      
      
    
    
}
