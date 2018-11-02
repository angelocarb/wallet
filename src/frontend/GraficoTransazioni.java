/*http://tutorials.jenkov.com/javafx/barchart.html*/
package frontend;
import backend.Filter;
import backend.GestoreDB;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class GraficoTransazioni extends BarChart {
    private XYChart.Series dataSeries1;
    private XYChart.Series dataSeries2;
    private ObservableList<Filter> listaMesi;
     
    
    public GraficoTransazioni(){
        super(new CategoryAxis(),(new NumberAxis()));
        setLegendVisible(true);
        setAnimated(true);
        listaMesi= FXCollections.observableArrayList();
        setData(listaMesi);
        
        setTitle("Transazioni per ogni mese");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Mesi");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Euro");
        dataSeries1 = new XYChart.Series();
        dataSeries2 = new XYChart.Series();
        
        
    }
    
   public void aggiornaGrafico() {
       List<Filter> dati = null;
       
        dati = GestoreDB.estraiDaAnnoSelezionato("2018");
        
        
        Series<String, Integer> mesiEntrate = new Series<>();
        Series<String , Integer> mesiUscite = new Series<>();
        for(int i = 0; i < dati.size(); i++){
             int uscite = Math.abs(dati.get(i).getUscite());
                mesiEntrate.getData().add(new XYChart.Data(dati.get(i).getMese() , dati.get(i).getEntrate()));
                mesiUscite.getData().add(new XYChart.Data(dati.get(i).getMese() , uscite));
                
              
        }
        mesiEntrate.setName("Entrate");
        getData().add(mesiEntrate);
        mesiUscite.setName("Uscite");
        getData().add(mesiUscite);
        
        
     List<Series> nuovo = new ArrayList<>();
     nuovo.add(mesiEntrate);
     nuovo.add(mesiUscite);
  
     this.setData(FXCollections.observableArrayList(nuovo));
     
     
   }
   
   public void aggiornaGraficoDaSelezioneAnno(String annoSelezionato){
       List<Filter> dati = null;
       
        dati = GestoreDB.estraiDaAnnoSelezionato(annoSelezionato);
        
        
        Series<String, Integer> mesiEntrate = new Series<>();
        Series<String , Integer> mesiUscite = new Series<>();
        for(int i = 0; i < dati.size(); i++){
             int uscite = Math.abs(dati.get(i).getUscite());
                mesiEntrate.getData().add(new XYChart.Data(dati.get(i).getMese() , dati.get(i).getEntrate()));
                mesiUscite.getData().add(new XYChart.Data(dati.get(i).getMese() , uscite));
                
              
        }
        mesiEntrate.setName("Entrate");
        getData().add(mesiEntrate);
        mesiUscite.setName("Uscite");
        getData().add(mesiUscite);
        
        
     List<Series> nuovo = new ArrayList<>();
     nuovo.add(mesiEntrate);
     nuovo.add(mesiUscite);
  
     this.setData(FXCollections.observableArrayList(nuovo));
     
   }
   
 
  
}
   
  
    
    
    
    
    




