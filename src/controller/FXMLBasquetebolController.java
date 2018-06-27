package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import mainC.MainC;
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
    private Label lFaltasE;
    @FXML
    private Label lFaltasD;
    @FXML
    private Label lPontosE;
    @FXML
    private Label lPontosD;
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

    
        //botões clientes
    @FXML
    public TextField tfTimeDireita;
    @FXML
    public TextField tfTimeEsquerda;
    @FXML
    public Button bTimes;
    @FXML
    public Button bSoma1PontoE;
    @FXML
    public Button bSoma2PontoE;    
    @FXML
    public Button bSoma3PontoE;
@FXML
    public Button bMenos1PontoE;
    @FXML
    public Button bMenos2PontoE;
    @FXML
    public Button bMenos3PontoE;
    
    @FXML
    public Button bSoma1PontoD;
    @FXML
    public Button bSoma2PontoD;
    @FXML
    public Button bSoma3PontoD;
    @FXML
    public Button bMenos1PontoD;
    @FXML
    public Button bMenos2PontoD;
    @FXML
    public Button bMenos3PontoD;

    @FXML
    public Button bSomaFaltaE;
    @FXML
    public Button bMenosFaltaE;
    
    @FXML
    public Button bSomaFaltaD;
    @FXML
    public Button bMenosFaltaD;
    
    @FXML
    public Button bSomaPeriodo;
    @FXML
    public Button bMenosPeriodo;

    @FXML
    public Button bVolta;
    
    private int pontosE,pontosD,faltasDireita,faltasEsquerda;

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
       
       bTimes.setOnMouseClicked((MouseEvent ev) -> {
            try {
                MainC.mandaMSG("!NOME_TIMES@" + tfTimeEsquerda.getText() + "@" + tfTimeDireita.getText());
            } catch (Exception e) {

            }
        });
        bSoma1PontoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@ESQUERDA@SOMA@UM");
                pontosE++;
            } catch (Exception e) {

            }
            if (pontosE > 9) {
                Platform.runLater(() -> {
                    lPontosE.setText("" + pontosE);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosE.setText("0" + pontosE);
                });
            }
        });

        bSoma1PontoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@DIREITA@SOMA@UM");
                pontosD++;
            } catch (IOException e) {

            }
            if (pontosD > 9) {
                Platform.runLater(() -> {
                    lPontosD.setText("" + pontosD);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosD.setText("0" + pontosD);
                });
            }
        });
        
        bSoma2PontoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@ESQUERDA@SOMA@DOIS");
                pontosE=pontosE+2;
            } catch (Exception e) {

            }
            if (pontosE > 9) {
                Platform.runLater(() -> {
                    lPontosE.setText("" + pontosE);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosE.setText("0" + pontosE);
                });
            }
        });

        bSoma2PontoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@DIREITA@SOMA@DOIS");
                pontosD=pontosD+2;
            } catch (IOException e) {

            }
            if (pontosD > 9) {
                Platform.runLater(() -> {
                    lPontosD.setText("" + pontosD);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosD.setText("0" + pontosD);
                });
            }
        });
        
        bSoma3PontoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@ESQUERDA@SOMA@TRES");
                pontosE=pontosE+3;
            } catch (Exception e) {

            }
            if (pontosE > 9) {
                Platform.runLater(() -> {
                    lPontosE.setText("" + pontosE);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosE.setText("0" + pontosE);
                });
            }
        });

        bSoma3PontoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@DIREITA@SOMA@TRES");
                pontosD=pontosD+3;
            } catch (IOException e) {

            }
            if (pontosD > 9) {
                Platform.runLater(() -> {
                    lPontosD.setText("" + pontosD);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosD.setText("0" + pontosD);
                });
            }
        });

        bSomaFaltaD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@FALTA@DIREITA@SOMA");
                faltasDireita++;
            } catch (IOException e) {

            }
            if (faltasDireita > 9) {
                Platform.runLater(() -> {
                    lFaltasD.setText("" + faltasDireita);
                });
            } else {
                Platform.runLater(() -> {
                    lFaltasD.setText("0" + faltasDireita);
                });
            }
        });
        
        bSomaFaltaE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@FALTA@ESQUERDA@SOMA");
                faltasEsquerda++;
            } catch (IOException e) {

            }
            if (faltasEsquerda > 9) {
                Platform.runLater(() -> {
                    lFaltasE.setText("" + faltasEsquerda);
                });
            } else {
                Platform.runLater(() -> {
                    lFaltasE.setText("0" + faltasEsquerda);
                });
            }
        });
       
       bMenos1PontoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@ESQUERDA@MENOS@UM");
                pontosE--;
            } catch (Exception e) {

            }
            if (pontosE > 9) {
                Platform.runLater(() -> {
                    lPontosE.setText("" + pontosE);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosE.setText("0" + pontosE);
                });
            }
        });

        bMenos1PontoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@DIREITA@MENOS@UM");
                pontosD--;
            } catch (IOException e) {

            }
            if (pontosD > 9) {
                Platform.runLater(() -> {
                    lPontosD.setText("" + pontosD);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosD.setText("0" + pontosD);
                });
            }
        });

        bMenos2PontoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@ESQUERDA@MENOS@DOIS");
                pontosE=pontosE-2;
            } catch (Exception e) {

            }
            if (pontosE > 9) {
                Platform.runLater(() -> {
                    lPontosE.setText("" + pontosE);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosE.setText("0" + pontosE);
                });
            }
        });

        bMenos2PontoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@DIREITA@MENOS@DOIS");
                pontosD=pontosD-2;
            } catch (IOException e) {

            }
            if (pontosD > 9) {
                Platform.runLater(() -> {
                    lPontosD.setText("" + pontosD);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosD.setText("0" + pontosD);
                });
            }
        });
        
        bMenos3PontoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@ESQUERDA@MENOS@TRES");
                pontosE=pontosE-3;
            } catch (Exception e) {

            }
            if (pontosE > 9) {
                Platform.runLater(() -> {
                    lPontosE.setText("" + pontosE);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosE.setText("0" + pontosE);
                });
            }
        });

        bMenos3PontoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@DIREITA@MENOS@TRES");
                pontosD=pontosD-3;
            } catch (IOException e) {

            }
            if (pontosD > 9) {
                Platform.runLater(() -> {
                    lPontosD.setText("" + pontosD);
                });
            } else {
                Platform.runLater(() -> {
                    lPontosD.setText("0" + pontosD);
                });
            }
        });
        
        bMenosFaltaD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@FALTA@DIREITA@MENOS");
                faltasDireita--;
            } catch (IOException e) {

            }
            if (faltasDireita > 9) {
                Platform.runLater(() -> {
                    lFaltasD.setText("" + faltasDireita);
                });
            } else {
                Platform.runLater(() -> {
                    lFaltasD.setText("0" + faltasDireita);
                });
            }
        });
        bMenosFaltaE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@FALTA@ESQUERDA@MENOS");
                faltasEsquerda--;
            } catch (IOException e) {

            }
            if (faltasEsquerda > 9) {
                Platform.runLater(() -> {
                    lFaltasE.setText("" + faltasEsquerda);
                });
            } else {
                Platform.runLater(() -> {
                    lFaltasE.setText("0" + faltasEsquerda);
                });
            }
        });

        bVolta.setOnMouseClicked((MouseEvent e) -> {
            MainC.loadScene("/telas/FXMLPrincipal.fxml");
            stopc=false;
        });
        
    }

}
