package middleware;

import java.io.*;

public class GestoreCache implements Serializable{
    private static String tipo;
    private static int valore;
    private static String data;
    private static boolean valida;
    public GestoreCache(Transazione t){ //(01)
       this.tipo = t.getTipo();
       this.valore = t.getValore();
       this.data = t.getData();
     }
    public GestoreCache() throws FileNotFoundException{ //(02)
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../../cache/cache.bin"))){

            GestoreCache gc = (GestoreCache) ois.readObject();

            tipo=gc.tipo;
            valore=gc.valore;
            data=gc.data;
            valida=true;


        }catch(IOException | ClassNotFoundException e){
            valida=false;
            System.out.println("Errore cache"+e.getMessage());
        }
    }
    public void salvaCache(){ //(03)
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../../cache/cache.bin")))
        {
            oos.writeObject(this);
             System.out.println("salvata");
        }catch(Exception e){
            System.out.println("Errore salvataggio dati cache");
        }
    }
    public GestoreCache leggiCache() { //(04)
        if(valida){

            System.out.println("prelevata");
            return this;
        }
        else
            return null;
    }


}


//(01): Costruttore per inizializzare i campi della classe quando si è già in possesso di un oggetto Transazione;
//(02): Costruttore per inizializzare i campi della classe quando si ha un oggetto Transazione salvato su un file binario;
//(03): Funzione utilizzata per salvare la cache generando un file binario;
//(04): Funzione utilizzata per leggere la cache salvata precedentemente su un file binario;
