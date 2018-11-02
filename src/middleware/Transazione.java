/* Classe che contiene le informazioni relative ad una data transazione */

package middleware;

import javafx.beans.property.*;

public class Transazione{ //(01)
    private final  SimpleIntegerProperty idTransazione;
    private final SimpleStringProperty tipo;
    private final SimpleIntegerProperty valore;
    private final SimpleStringProperty data;
    
    
    
    

    public Transazione(int idTransazione , String nuovoTipo, int nuovoValore , String nuovaData){// (02)
     this.tipo=new SimpleStringProperty(nuovoTipo);
     this.valore=new SimpleIntegerProperty(nuovoValore);
     this.data = new SimpleStringProperty(nuovaData);
     this.idTransazione = new SimpleIntegerProperty(idTransazione);
     
     
     
    }
    /* public int getIdTransazione(){
         return idTransazione;
     }*/
     public String getTipo(){
         return tipo.get();
     }
    
     public int getValore(){
         return valore.get();
     }
     public String getData(){
         return  data.get();
     }
     
     public int getID(){
         return idTransazione.get();
     }
     

}

//(01): Classe bean che rappresenta la tabella Transazione del database
//(02): Costruttore per la classe Transazione che inizializza i vari parametri di un oggetto Transazione
