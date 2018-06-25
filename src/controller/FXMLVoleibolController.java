/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
//import javafxFutebol.*;
//import javafxPrincipal.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//import javafxSet.FXMLSetDadosController;
//import javafxlogin.FXMLLoginController;

/**
 *
 * @author Elison
 */
public class FXMLVoleibolController implements Initializable {

    @FXML
    private AnchorPane apVolei;
    @FXML
    public BorderPane bpVolei;
    @FXML
    private MediaView mvDireitaVolei;
    @FXML
    private MediaView mvEsquerdaVolei;
    @FXML
    private Label lTimeEsquerda;
    @FXML
    private Label lTimeDireita;
    @FXML
    private Label lPlacarEsquerda;
    @FXML
    private Label lPlacarDireita;
    @FXML
    private Label lSetsEsquerda;
    @FXML
    private Label lSetsDireita;
    @FXML
    private Label lSet;
    @FXML
    private Label lPrimeiroSet;
    @FXML
    private Label lSegundoSet;
    @FXML
    private Label lTerceiroSet;
    @FXML
    private Label lQuartoSet;
    @FXML
    private Label lCronometroVolei;
    @FXML
    private ImageView ivTimeEsquerda;
    @FXML
    private ImageView ivTimeDireita;

    //botões cliente
    @FXML
    private Button bmaispontosesquerda;
    @FXML
    private Button bmaispontosdireita;
    @FXML
    private Button bmenospontosesquerda;
    @FXML
    private Button bmenospontosdireita;
    @FXML
    private Button bstartcron;
    @FXML
    private Button bstopcron;
    @FXML
    private Button bmaisfaltaesquerda;
    @FXML
    private Button bmaisfaltadireita;
    @FXML
    private Button bmenosfaltaesquerda;
    @FXML
    private Button bmenosfaltadireita;
    @FXML
    private Button bmaissetesquerda;
    @FXML
    private Button bmaissetdireita;
    @FXML
    private Button bmenossetesquerda;
    @FXML
    private Button bmenossetdireita;
    @FXML
    private Button bmaisset;
    @FXML
    private Button bmenosset;
    @FXML
    private Button bfimdoset;

    private int pontose = 0;
    private int pontosd = 0;

    private int faltase = 0;
    private int faltasd = 0;

    private static FXMLLoginController lc;

    protected static BooleanProperty v = new SimpleBooleanProperty();


    private static File file = new File("src/videos/Propaganda.mp4");
    private static final String mediaurl = file.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;


    public boolean stopc = true;
    private int segundo = 0;
    private int minuto = 0;
    private int hora = 0;

    public void iniciaCronometro() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (stopc == true) {
                    segundo++;

                    if (segundo == 60) {
                        minuto++;
                        segundo = 0;
                    }

                    if (minuto == 60) {
                        hora++;
                        minuto = 0;
                    }
                    String min = minuto <= 9 ? "0" + minuto : minuto + "";
                    String seg = segundo <= 9 ? "0" + segundo : segundo + "";
                    String hor = hora <= 9 ? "0" + hora : hora + "";

                    Platform.runLater(() -> {

                        lCronometroVolei.setText(hor + ":" + min + ":" + seg);
                    });
                    Thread.sleep(1000);

                }
                return null;
            }
        };
        new Thread(t).start();

    }

    //configuração do placar
    public void Placar() {
        Task t2 = new Task() {
            @Override
            protected Object call() throws Exception {

                //Soma 1 gol ao placar do time da esquerda
                //ao pressionar <-
                apVolei.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                    if (event.getCode().equals(KeyCode.LEFT)) {

                        pontose = Integer.parseInt(lPlacarEsquerda.getText());
                        pontose = (pontose + 1);
                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText(spe);
                        });
                    }
                    //Soma 1 gol ao placar do time da direita
                    //ao pressionar ->
                    if (event.getCode().equals(KeyCode.RIGHT)) {

                        pontosd = Integer.parseInt(lPlacarDireita.getText());
                        pontosd = (pontosd + 1);
                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarDireita.setText(spd);
                        });
                    }
                });
                //Soma 1 gol ao placar do time da esquerda
                //ao pressionar o botão +
                bmaispontosesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontose = Integer.parseInt(lPlacarEsquerda.getText());
                    pontose = (pontose + 1);

                    String spe = Integer.toString(pontose);

                    Platform.runLater(() -> {

                        lPlacarEsquerda.setText(spe);
                    });
                });

                //Soma 1 gol ao placar do time da direita
                //ao pressionar o botão +
                bmaispontosdireita.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPlacarDireita.getText());
                    pontosd = (pontosd + 1);

                    String spd = Integer.toString(pontosd);

                    Platform.runLater(() -> {

                        lPlacarDireita.setText(spd);
                    });
                });

                //Subtrai 1 gol do placar do time da esquerda
                //ao pressionar o botão -
                bmenospontosesquerda.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    pontose = Integer.parseInt(lPlacarEsquerda.getText());

                    if (pontose == 0) {
                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText(spe);
                        });
                    } else {
                        pontose = (pontose - 1);

                        String spe = Integer.toString(pontose);

                        Platform.runLater(() -> {

                            lPlacarEsquerda.setText(spe);
                        });
                    }
                });

                //Subtrai 1 gol do placar do time da direita
                //ao pressionar o botão -
                bmenospontosdireita.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    pontosd = Integer.parseInt(lPlacarDireita.getText());
                    if (pontosd == 0) {
                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarDireita.setText(spd);
                        });
                    } else {
                        pontosd = (pontosd - 1);

                        String spd = Integer.toString(pontosd);

                        Platform.runLater(() -> {

                            lPlacarDireita.setText(spd);
                        });
                    }
                });
                return null;
            }
        };
        new Thread(t2).start();

    }
    //final da configuração do placar
    
    //função abaixo, pega o time que foi setado
    //na tela de configuração do placar
    public void pegarTime(String nomea, String nomeb) {

        this.lTimeEsquerda.setText(nomea);
        this.lTimeDireita.setText(nomeb);

    }
    // final do pega time

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        media = new Media(mediaurl);
        mediaplayer = new MediaPlayer(media);
        mvDireitaVolei.setMediaPlayer(mediaplayer);
        mvEsquerdaVolei.setMediaPlayer(mediaplayer);
        mediaplayer.play();
        iniciaCronometro();
        Placar();
    }

}
