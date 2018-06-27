package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//import javafxSet.FXMLSetDadosController;
//import javafxlogin.FXMLLoginController;

/**
 *
 * @author Elison
 */
public class FXMLBasquetebolController implements Initializable {

    //Botões e Labels padrão.
    @FXML
    private AnchorPane apBasquete;
    @FXML
    private MediaView mvBasquete;
    @FXML
    private Label lCronometroB;
    @FXML
    private Label lPeriodo;
    @FXML
    private Label l24seg;
    @FXML
    private Label lTimeA;
    @FXML
    private Label lTimeB;
    @FXML
    private Label lFaltasA;
    @FXML
    private Label lFaltasB;
    @FXML
    private Label lPontosA;
    @FXML
    private Label lPontosB;
    @FXML
    private ImageView ivTimeA;
    @FXML
    private ImageView ivTimeB;
    
    //Botões e Labels do Cliente
    @FXML
    private Button bplaycron;
    @FXML
    private Button bstopcron;
    @FXML
    private Button bplayshot;
    @FXML
    private Button bstopshot;
    @FXML
    private Button bmaispontosA;
    @FXML
    private Button bmenospontosA;
    @FXML
    private Button bmaisfaltasA;
    @FXML
    private Button bmenosfaltasA;
    @FXML
    private Label lmaispontosA;
    @FXML
    private Label lmenospontosA;
    @FXML
    private Label lmaispontosB;
    @FXML
    private Label lmenospontosB;
    @FXML
    private Button bmaispontosB;
    @FXML
    private Button bmenospontosB;
    @FXML
    private Button bmaisfaltasB;
    @FXML
    private Button bmenosfaltasB;
    @FXML
    private Button bmaisperiodo;
    @FXML
    private Button bmenosperiodo;
  
    

    private static File file = new File("src/videos/Propaganda.mp4");
    private static final String mediaurl = file.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;

    public boolean stopc = true;
    private int segundo = 60;
    private int minuto = 10;
    
    //Cronometro
    public void iniciaCronometro() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (stopc == true) {
                    segundo--;

                    if (segundo == 00) {
                        minuto--;
                        segundo = 59;
                    }

//                    if (minuto == 60) {
//
//                        minuto = 0;
//                    }
                    String min = minuto <= 9 ? "0" + minuto : minuto + "";
                    String seg = segundo <= 9 ? "0" + segundo : segundo + "";

                    Platform.runLater(() -> {

                        lCronometroB.setText(min + ":" + seg);
                    });
                    Thread.sleep(1000);

                }
                return null;
            }
        };
        new Thread(t).start();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        media = new Media(mediaurl);
        mediaplayer = new MediaPlayer(media);
        mvBasquete.setMediaPlayer(mediaplayer);
        mediaplayer.play();
       iniciaCronometro();

    }

}
