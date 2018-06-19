package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mainC.Main;

/**
 * FXML Controller class
 *
 * @author Elison
 */
public class FXMLSetDadosController implements Initializable {

    @FXML
    private Button bDireito;
    @FXML
    private Button bEsquerdo;
    @FXML
    private Button bVoltar;
    @FXML
    private Button bContinuar;
    @FXML
    private ImageView ivDireito;
    @FXML
    private ImageView ivEsquerdo;
    @FXML
    private TextField tfTimeEsquerda;
    @FXML
    private TextField tfTimeDireita;

    private int esporte;
    protected static StringProperty timea = new SimpleStringProperty();
    protected static StringProperty timeb = new SimpleStringProperty();
    private static FXMLPrincipalController pc;

    @FXML
    private void ContinuarButtonAction(Event e) {
        timea.setValue(tfTimeEsquerda.getText());
        timeb.setValue(tfTimeDireita.getText());

    }

    public String retornaTimeA() {

        return timea.get();

    }

    public String retornaTimeB() {

        return timeb.get();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bContinuar.setOnMouseClicked((MouseEvent e) -> {

            //timea.setValue(tfTimeDireita.getText());
            //timeA = tfDireito.getText();
            //  JavaFXFutebol fb = new JavaFXFutebol();
            try {
                Main.mandaMSG("!NOME_TIMES@" + tfTimeEsquerda.getText() + "@" + tfTimeDireita.getText());
                //    fb.start(new Stage());
            } catch (Exception ex) {
                //  Logger.getLogger(FXMLFutebolController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        bVoltar.setOnMouseClicked((MouseEvent e) -> {
            Main.loadScene("/tela/FXMLPrincipal.fxml");
        });

    }

}
