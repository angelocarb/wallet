/*Classe rappresentante l’oggetto tabella contenente tutte le transazioni nel portafogli */
package frontend;

import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import middleware.Transazione;


public class TabellaTransazioni extends TableView<Transazione>{
      private final ObservableList<Transazione> listaTransazioni;
      private final TableColumn<Transazione,String> tipoCol = new TableColumn<>("Tipo"); 
      private final TableColumn<Transazione,String> dataCol = new TableColumn<>("Data");
      private final TableColumn<Transazione,String> valoreCol = new TableColumn<>("Valore");
      
        public TabellaTransazioni(){ //(02) 
        
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);   
        setMaxHeight(300);
        
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        valoreCol.setCellValueFactory(new PropertyValueFactory<>("valore"));    
        listaTransazioni=FXCollections.observableArrayList();
        setItems(listaTransazioni);
       
        
       
        getColumns().add(tipoCol);
        getColumns().add(dataCol);
        getColumns().add(valoreCol);
    }
        
        public void aggiornaListaTransazioni(List<Transazione> l){ //(03)
        listaTransazioni.clear();
        listaTransazioni.addAll(l);
    }
      
}