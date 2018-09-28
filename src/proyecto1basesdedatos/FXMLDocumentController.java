/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1basesdedatos;

import domain.JsonFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import logic.JsonAdmin;
import logic.RelationalModelGenerator;

/**
 *
 * @author Wilmata
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextArea jsonInputTextArea;
    public static JsonFile jsonFile = new JsonFile();

    JsonAdmin jsonAdmin = new JsonAdmin();
    
    RelationalModelGenerator relationalModelGenerator = new  RelationalModelGenerator();
    
    public void cargarButton(ActionEvent event) throws Exception {
        jsonFile = jsonAdmin.readJson(jsonInputTextArea.getText());
        relationalModelGenerator.entityGenerator();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
