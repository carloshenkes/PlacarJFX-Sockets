package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import mainC.MainC;

/**
 *
 * @author Elison
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private Button bFutebol;

    @FXML
    private Button bFutsal;

    @FXML
    private Button bBasquetebol;

    @FXML
    private Button bVoleibol;

    @FXML
    private Button bHandebol;

    @FXML
    private Button bSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bFutebol.setOnMouseClicked((MouseEvent e) -> {
            MainC.loadScene("/telas/FXMLFutebol.fxml");
            try {
                MainC.mandaMSG("!ABRE_FUTEBOL");
            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        bBasquetebol.setOnMouseClicked((MouseEvent e) -> {
            MainC.loadScene("/telas/FXMLBasquetebol.fxml");           
            try {
                MainC.mandaMSG("!ABRE_BASQUETE");
            } catch (Exception ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        bVoleibol.setOnMouseClicked((MouseEvent e) -> {
            MainC.loadScene("/telas/FXMLVoleibol.fxml");           
            try {
                MainC.mandaMSG("!ABRE_VOLEIBOL");
            } catch (Exception ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        bSair.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });

    }

    public void fecha() {
        MainC.getStage().close();
    }

}
