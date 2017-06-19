package it.polito.tdp.movimenti;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.movimenti.bean.Circoscrizione;
import it.polito.tdp.movimenti.bean.Model;
import it.polito.tdp.movimenti.bean.Stat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MovimentiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblStatus;

    @FXML
    private ProgressBar pbProgress;

    @FXML
    private ChoiceBox<Circoscrizione> boxCircPartenza;

    @FXML
    private Button btnElenca;

    @FXML
    private TextField txtLunghezza;

    @FXML
    private Button btnRicerca;

    @FXML
    private TextArea txtResult;

    @FXML
    void doElenca(ActionEvent event) {
    	txtResult.clear();
    	Circoscrizione c=boxCircPartenza.getValue();
    	if(c==null){
    		txtResult.appendText("Seleziona una circoscrizione.\n");
    		return;
    	}
    	
    	List<Stat> temp=model.cercaMovimenti(c);
    	txtResult.appendText("Elenco movimenti per "+c.getNum()+"\n");
    	for(Stat s: temp){
    		txtResult.appendText(s.getC()+" --> "+s.getNumMovimenti()+"\n");
    	}
    }

    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear();
    	Circoscrizione c=boxCircPartenza.getValue();
    	if(c==null){
    		txtResult.appendText("Seleziona una circoscrizione.\n");
    		return;
    	}
    	int lung=0;
    	try{
    		lung=Integer.parseInt(txtLunghezza.getText());
    	}catch(NumberFormatException n){
    		txtResult.appendText("Inserire una numero intero come lunghezza.\n");
    		return;
    	}
    	
    	model.creaGrafo();
    	List<Circoscrizione> temp=model.trovaCammino(c,lung);
    	txtResult.appendText("Sequenza di circoscrizioni:\n");
    	txtResult.appendText(temp.toString());
    }

    @FXML
    void initialize() {
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'Movimenti.fxml'.";
        assert pbProgress != null : "fx:id=\"pbProgress\" was not injected: check your FXML file 'Movimenti.fxml'.";
        assert boxCircPartenza != null : "fx:id=\"boxCircPartenza\" was not injected: check your FXML file 'Movimenti.fxml'.";
        assert btnElenca != null : "fx:id=\"btnElenca\" was not injected: check your FXML file 'Movimenti.fxml'.";
        assert txtLunghezza != null : "fx:id=\"txtLunghezza\" was not injected: check your FXML file 'Movimenti.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Movimenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Movimenti.fxml'.";
    }
    
    Model model;
	public void setModel(Model model){
		this.model=model;
		
		boxCircPartenza.getItems().addAll(model.getCircoscrizioni());
	}
    
}

