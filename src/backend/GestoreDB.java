/*Esegue tutte le query al Database e gestisce il collegamento col Database;*/

package backend;
import middleware.Transazione;
import frontend.ParametriDB;
import java.util.*;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class GestoreDB{
    
    private static ParametriDB parametriDB = new ParametriDB("root" , "" ,"localhost" , "3306" , "wallet" );
    private static final String queryInserimentoTransazione = "INSERT INTO transazioni(Tipo,Valore,Data)VALUES(?,?,?)";
    private static final String queryEliminazioneTransazione = "DELETE FROM transazioni WHERE IDtrans = ?";
    private static final String queryLetturaTransazioni = "SELECT* FROM transazioni ORDER BY Data DESC";
    private static final String queryAggiornaConto = "SELECT SUM(Valore) AS Totale FROM transazioni";
    private static final String queryEstraiDaAnnoSelezionato = ""
            + "SELECT SUM(D.entrate) as entrate , SUM(D.uscite) as uscite, D.dataFormattata FROM (\n" +
"	SELECT IF (Valore > 0 , Valore , 0) AS entrate , IF(Valore < 0, Valore , 0) as uscite , \n" +
"	date_format(Data , \"%M\") as dataFormattata \n" +
"	from transazioni Where Year(Data) = ? \n" +
"	\n" +
") AS D \n" +
"\n" +
"GROUP BY dataFormattata\n" +
"ORDER BY STR_TO_DATE( dataFormattata , \"%M\")";
    private static final String queryEstraiAnni = "SELECT DISTINCT YEAR(Data) AS anno\n" + "FROM transazioni ";
    private static final String queryEstraiEntrateAnnoSelezionato = "select SUM(Valore) as entrate from transazioni where YEAR(Data) = ? AND Valore > 0";
    private static final String queryEstraiUsciteAnnoSelezionato = "select SUM(Valore) as uscite from transazioni where YEAR(Data) = ? AND Valore < 0";
    
 
    public static Connection getConnection(ParametriDB pdb){
       String utente = parametriDB.getUtente();
        String password = parametriDB.getPWD();
        String hostname = parametriDB.getHostnameDB();
        String porta = parametriDB.getPortaDB();
        String nomeDB = parametriDB.getNomeDB();
        
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://"+hostname+":"+porta+"/"+nomeDB,utente,"");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return con;
    }

    
    public static int InserisciTransazione(String tipo, int valore, String data){ //(01)
        try(
            Connection con = getConnection(parametriDB);
            PreparedStatement ps = con.prepareStatement(queryInserimentoTransazione);
            ){
            ps.setString(1, tipo);
            ps.setInt(2, valore);
            ps.setString(3,data);
            
            ps.executeUpdate();
            return -1;
            }catch(SQLException ex){
                System.out.println("Errore inserimento transazione:"+" "+ex.getMessage());
                return -1;
            }

    }
    
    public static boolean RimuoviTransazione(int index){ //(02)
        try(
            Connection con = getConnection(parametriDB);
            PreparedStatement ps = con.prepareStatement(queryEliminazioneTransazione);
            ){
            ps.setInt(1, index);
            return ps.executeUpdate() > 0;
            }catch(SQLException ex){
                System.out.println("Errore eliminazione transazione"+ex.getMessage());
                return false;
            }
    }
    
   

    
    
    public static List<Transazione> caricaTransazioni(){
        List<Transazione> listaTransazioni = new ArrayList<>();
        try(Connection con = getConnection(parametriDB);
                PreparedStatement ps = con.prepareStatement(queryLetturaTransazioni);
            ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listaTransazioni.add(new Transazione(rs.getInt("IDtrans") ,rs.getString("Tipo") ,rs.getInt("Valore") , rs.getString("Data")));
            }
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return listaTransazioni;
    }
    
    public static int aggiornaContoAttuale(){
        int totale = 0;
        try(Connection con = getConnection(parametriDB);
                PreparedStatement ps = con.prepareStatement(queryAggiornaConto);
                ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                totale = rs.getInt("Totale");
            }
            
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    return totale;
    }
    
    public static List<Filter> estraiDaAnnoSelezionato(String annoSelezionato){
        List<Filter> listaTransDaAnnoSelezionato = new ArrayList<>();
        try(Connection con = getConnection(parametriDB);
                PreparedStatement ps = con.prepareStatement(queryEstraiDaAnnoSelezionato);){
            ps.setString(1, annoSelezionato);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                listaTransDaAnnoSelezionato.add(new Filter(rs.getString("dataFormattata") , rs.getInt("entrate") , rs.getInt("uscite")));
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    return listaTransDaAnnoSelezionato;
    }
    
    public static ObservableList<String> estraiAnni(){
        ObservableList<String> listaAnniEstratti = FXCollections.observableArrayList();
        try(Connection con = getConnection(parametriDB);
                PreparedStatement ps = con.prepareStatement(queryEstraiAnni);){
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                listaAnniEstratti.add(rs.getString("anno"));
        }catch(SQLException ex ){
            ex.printStackTrace();
        }
    return listaAnniEstratti;
    }
    
   public static int estraiEntrateAnnoSelezionato(String selezionato){
       int entrate = 0;
       try(Connection con = getConnection(parametriDB);
               PreparedStatement ps = con.prepareStatement(queryEstraiEntrateAnnoSelezionato);){
           ps.setString(1, selezionato);
           ResultSet rs = ps.executeQuery();
           while(rs.next())
               entrate = rs.getInt("entrate");
       }catch(SQLException ex){
           ex.printStackTrace();
       }
    return entrate;
   }
   
   public static int estraiUsciteAnnoSelezionato(String selezionato){
       int uscite = 0;
       try(Connection con = getConnection(parametriDB);
               PreparedStatement ps = con.prepareStatement(queryEstraiUsciteAnnoSelezionato);){
           ps.setString(1, selezionato);
           ResultSet rs = ps.executeQuery();
           while(rs.next())
               uscite = rs.getInt("uscite");
       }catch(SQLException ex){
           ex.printStackTrace();
       }
    return uscite;
   }
   
 
}






/*SELECT SUM(D.entrate) as entrate , SUM(D.uscite) as uscite, D.dataFormattata FROM (
	SELECT IF (Valore > 0 , Valore , 0) AS entrate , IF(Valore < 0, Valore , 0) as uscite , 
	date_format(Data , "%M") as dataFormattata 
	from transazioni Where Year(Data) = 2018 
	
) AS D 

GROUP BY dataFormattata
ORDER BY STR_TO_DATE( dataFormattata , "%M")*/