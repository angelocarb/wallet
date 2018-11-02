package frontend;
import backend.GestoreDB;
import middleware.ClientLog;
import middleware.GestoreCache;
import middleware.GestoreConfigurazione;
import middleware.Transazione;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class AvvioWallet extends Application {
    private Button pulsanteInserisci,pulsanteElimina,pulsanteFiltra;
    private Label labelTipo , labelData , labelValore;
    private TextField campoTipo , campoValore;
    private TabellaTransazioni tabellaTransazioni;
    private DatePicker dp;
    private ChoiceBox YEARS;
    private TextField contoAttuale;
    private TextField saldoAnnuale;
    int totaleContoAttuale;
    int idSelezionato;
    GraficoTransazioni barChart;
    private final String NomeApp = "WALLET";
    ObservableList<String> listaAnni;
    GestoreCache gc;
    
    int entrateAnnoSelezionato;
    int usciteAnnoSelezionato;
        
   
    
     
    @Override
    public void start(Stage stage){
         ParametriConfigurazione configurazione = GestoreConfigurazione.getConfigurazione();

        if (configurazione == null) {
            System.out.println("Errore durante la configurazione dell'applicazione");
            System.exit(-1);
        }else{
            System.out.println("Configurazioni prelevate");
        }
        ClientLog.aperturaApplicazione(NomeApp);
        
        
        VBox root = new VBox();
        root.getChildren().add(generaInterfaccia(configurazione));
        
        PreparaAzioniPulsanteInserisci();
        PreparaAzioniPulsanteElimina();
        PreparaAzioniTabella();
        settaAzioniChiusuraApplicazione(stage);
        PreparaAzioniChoiceBox();
        
         
        Scene scene = new Scene(root, 800, 600);
        boolean add = scene.getStylesheets().add("style.css");
        root.getStyleClass().add("content");
        contoAttuale.getStyleClass().add("contoAttuale");
        saldoAnnuale.getStyleClass().add("saldoAnnuale");
        
        stage.setTitle("WALLET");
        stage.setScene(scene);
        stage.show();

        
    }
    
    
    private VBox generaInterfaccia(ParametriConfigurazione pc){
        
        GridPane gp = new GridPane();
        dp = new DatePicker();
        labelValore = new Label("Valore");
        labelTipo = new Label("Tipo");
        labelData = new Label("Data");
        campoTipo = new TextField();
        campoValore = new TextField();
        pulsanteInserisci = new Button("Inserisci");
        pulsanteElimina = new Button("Elimina");
        tabellaTransazioni = new TabellaTransazioni();
        listaAnni = FXCollections.observableArrayList();
        listaAnni = GestoreDB.estraiAnni();
        YEARS = new ChoiceBox(listaAnni);
        //YEARS = new ChoiceBox(FXCollections.observableArrayList(GestoreDB.estraiAnni()));
        YEARS.setTranslateX(150);
        YEARS.setTranslateY(20);
        YEARS.setValue("2018");
        contoAttuale = new TextField();
        saldoAnnuale = new TextField();
        saldoAnnuale.setTranslateX(10);
        entrateAnnoSelezionato = GestoreDB.estraiEntrateAnnoSelezionato("2018");
        usciteAnnoSelezionato = GestoreDB.estraiUsciteAnnoSelezionato("2018");
        int differenza = entrateAnnoSelezionato + usciteAnnoSelezionato;
        
        totaleContoAttuale = GestoreDB.aggiornaContoAttuale();
        contoAttuale.setText("Saldo Totale \u20AC"+" "+totaleContoAttuale);
        saldoAnnuale.setText(""+"Saldo Annuale \u20AC"+" "+differenza);
        
        saldoAnnuale.setDisable(true);
        
        contoAttuale.setDisable(true);
        pulsanteElimina.setDisable(true);
        
       barChart = new GraficoTransazioni();
       barChart.aggiornaGrafico();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(labelTipo,0,1,1,1);
        gp.add(campoTipo,1,1,1,1);
        gp.add(labelValore,0,2,1,1);
        gp.add(campoValore,1,2,1,1);
        gp.add(labelData,0,3,1,1);
        gp.add(dp,1,3,1,1);
        gp.add(pulsanteInserisci,0,5,1,1);
        gp.add(pulsanteElimina,1,5,1,1);
        gp.add(YEARS,0,10,1,1);
        HBox.setHgrow(tabellaTransazioni, Priority.ALWAYS);
        tabellaTransazioni.aggiornaListaTransazioni(GestoreDB.caricaTransazioni());
        gp.setPadding(new Insets(8));
        
       
        HBox hb = new HBox();
        HBox contoAtt = new HBox();
        contoAtt.getChildren().addAll(contoAttuale , saldoAnnuale);
        hb.getChildren().addAll(tabellaTransazioni ,gp);
        
        VBox content = new VBox();
        content.getChildren().addAll(hb ,contoAtt,barChart);
        return content;
    }
    
    public void PreparaAzioniPulsanteInserisci(){
        pulsanteInserisci.setOnAction((ActionEvent ev) -> {
        ClientLog.pressioneInserisci(NomeApp);
        tabellaTransazioni.aggiornaListaTransazioni(
        GestoreDB.caricaTransazioni());
        String tipoInserito;
        String valoreInserito;
        tipoInserito = campoTipo.getText();
        valoreInserito = campoValore.getText();
        LocalDate dataInserita = dp.getValue();
        GestoreDB.InserisciTransazione(tipoInserito , Integer.parseInt(valoreInserito) , dataInserita.format(DateTimeFormatter.ISO_DATE));
        tabellaTransazioni.aggiornaListaTransazioni(GestoreDB.caricaTransazioni());
        totaleContoAttuale = GestoreDB.aggiornaContoAttuale();
        contoAttuale.setText("Saldo Totale \u20AC"+" "+totaleContoAttuale);
        
        entrateAnnoSelezionato = GestoreDB.estraiEntrateAnnoSelezionato("2018");
        usciteAnnoSelezionato = GestoreDB.estraiUsciteAnnoSelezionato("2018");
        int differenza = entrateAnnoSelezionato + usciteAnnoSelezionato;
         saldoAnnuale.setText(""+"Saldo Annuale \u20AC"+" "+differenza);
        
        String annoSelezionato = YEARS.getValue().toString();
        barChart.aggiornaGraficoDaSelezioneAnno(annoSelezionato);
        
        
        
        
        campoValore.clear();
        campoTipo.clear();
        dp.getEditor().clear();
        
        });
        
    }
    
    public void PreparaAzioniPulsanteElimina(){
        pulsanteElimina.setOnAction((ActionEvent ev) -> {
            ClientLog.pressioneElimina(NomeApp);
        GestoreDB.RimuoviTransazione(idSelezionato);
        pulsanteElimina.setDisable(true);
        tabellaTransazioni.aggiornaListaTransazioni(GestoreDB.caricaTransazioni());
        totaleContoAttuale = GestoreDB.aggiornaContoAttuale();
        contoAttuale.setText("Saldo Totale \u20AC"+" "+totaleContoAttuale);
        String annoSelezionato = YEARS.getValue().toString();
        barChart.aggiornaGraficoDaSelezioneAnno(annoSelezionato);
        
        
        });
    }
    
    public void PreparaAzioniTabella(){
         tabellaTransazioni.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                pulsanteElimina.setDisable(false);
                idSelezionato = ((Transazione)newSelection).getID();
            }
        });
    }
    
    /* ogni volta che seleziono un anno devo aggiornare il grafico */
    public void PreparaAzioniChoiceBox(){    
        YEARS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String annoSelezionato = YEARS.getValue().toString();
            barChart.aggiornaGraficoDaSelezioneAnno(annoSelezionato);
            entrateAnnoSelezionato = GestoreDB.estraiEntrateAnnoSelezionato(annoSelezionato);
            usciteAnnoSelezionato = GestoreDB.estraiUsciteAnnoSelezionato(annoSelezionato);
            int diff = entrateAnnoSelezionato + usciteAnnoSelezionato;
            saldoAnnuale.setText(""+"Saldo Annuale \u20AC"+" "+diff);
            
        });
        
    }
    
     private void settaAzioniChiusuraApplicazione(Stage stage) { //(02)
        stage.setOnCloseRequest(event -> {
            try {
                ClientLog.chiusuraApplicazione(NomeApp);
                new GestoreCache().salvaCache();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AvvioWallet.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    
    

         
    
     public static void main(String[] args){
         launch(args);
     }
     
    
    }
    
    
    
     
     
     
    
     


