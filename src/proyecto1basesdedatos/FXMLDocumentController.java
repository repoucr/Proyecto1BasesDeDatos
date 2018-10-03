/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1basesdedatos;

import domain.JsonFile;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.JsonAdmin;
import logic.RelationalModelGenerator;

/**
 *
 * @author Wilmata
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button cargarButton;
    @FXML
    private Button nosotrosButton;
    @FXML
    private TextArea jsonInputTextArea;
    @FXML
    private Label messageLabel;
    public static JsonFile jsonFile = new JsonFile();
    private Image image;

    JsonAdmin jsonAdmin = new JsonAdmin();
    
    RelationalModelGenerator relationalModelGenerator = new  RelationalModelGenerator();
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    public void cargarButton(ActionEvent event) throws Exception {
        if (jsonInputTextArea.getText().equals("")) {
            messageLabel.setText("Please drop a JSON type text.");
        } else {
        jsonFile = jsonAdmin.readJson(jsonInputTextArea.getText());
        relationalModelGenerator.entityGenerator();
        messageLabel.setText("The script has been generated correctly.");
        }
    }
     @FXML
    private void aboutUsButton(MouseEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();     
        window.setScene(scene);
        window.show();
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     jsonInputTextArea.setTooltip(new Tooltip("Drop an entity-relationship model text in a JSON format"));
     cargarButton.setTooltip(new Tooltip("Click to generate a script"));
     nosotrosButton.setTooltip(new Tooltip("Information about developers"));
     cargarButton.setEffect(new ImageInput(new Image("/images/ejecucion.png")));
     nosotrosButton.setEffect(new ImageInput(new Image("/images/info.png")));
   
    }

   

}
