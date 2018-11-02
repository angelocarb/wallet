/* rappresenta la singola transazione che fa parte dell'array di transazioni restituito dalla funzione GestoreDB.estraiDaAnnoSelezionato*/

package backend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Filter {
    private final SimpleStringProperty mese;
    private final SimpleIntegerProperty entrate;
    private final SimpleIntegerProperty uscite;
    
    
    
    public Filter(String mese , int entrate , int uscite){
        this.mese = new SimpleStringProperty(mese);
        this.entrate = new SimpleIntegerProperty(entrate);
        this.uscite = new  SimpleIntegerProperty(uscite);
    }
    
    public String getMese(){
        return mese.get();
    }
    
    public int getEntrate(){
        return entrate.get();
    }
    
    public int getUscite(){
        return uscite.get();
    }
    
    
}
