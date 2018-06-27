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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import mainC.MainC;

/**
 *
 * @author Elison
 */
public class FXMLFutebolController implements Initializable {

    @FXML
    private AnchorPane apFutebol;
    @FXML
    public BorderPane bpFutebol;
    @FXML
    private MediaView mvFutebol;
    @FXML
    private Label lTimeEsquerda;
    @FXML
    private Label lTimeDireita;
    @FXML
    private Label lPontosE;
    @FXML
    private Label lPontosD;
    @FXML
    private Label lCronometro;
    @FXML
    public ImageView ivFundo;
    @FXML
    private Label lFaltasE;
    @FXML
    private Label lFaltasD;
    @FXML
    private Label lAmareloD;
    @FXML
    private Label lAmareloE;
    @FXML
    private Label lVermelhoD;
    @FXML
    private Label lVermelhoE;

    private int faltasDireita = 0;
    private int faltasEsquerda = 0;
    private int amareloDireita = 0;
    private int amareloEsquerda = 0;
    private int vermelhoDireita = 0;
    private int vermelhoEsquerda = 0;

    //botões clientes
    @FXML
    public TextField tfTimeDireita;
    @FXML
    public TextField tfTimeEsquerda;
    @FXML
    public Button bTimes;
    @FXML
    public Button bSomaPontoE;
    @FXML
    public Button bMenosPontoE;
    @FXML
    public Button bSomaPontoD;
    @FXML
    public Button bMenosPontoD;
    @FXML
    public Button bSomaFaltaE;
    @FXML
    public Button bMenosFaltaE;
    @FXML
    public Button bSomaFaltaD;
    @FXML
    public Button bMenosFaltaD;
    @FXML
    public Button bSomaAmareloE;
    @FXML
    public Button bSomaVermelhoE;
    @FXML
    public Button bMenosVermelhoE;
    @FXML
    public Button bMenosAmareloE;
    @FXML
    public Button bSomaAmareloD;
    @FXML
    public Button bSomaVermelhoD;
    @FXML
    public Button bMenosVermelhoD;
    @FXML
    public Button bMenosAmareloD;
    @FXML
    public Button bSomaPeriodo;
    @FXML
    public Button bMenosPeriodo;
    @FXML
    public Button bSomaAcrescimo;
    @FXML
    public Button bVolta;
    @FXML
    public ToggleButton tbPausa;
    @FXML
    public Label lSomaAcre;
    @FXML
    public Label lacrescimo;
    @FXML
    public Label lcronacrescimo;
    @FXML
    public Rectangle rectangleacrescimos;

    private int pontosE = 0;
    private int pontosD = 0;
    private int periodo = 0;
    private int acrescimo = 0;

    //configuração multimedia {
    private static File file = new File("src/videos/Propaganda.mp4");
    private static final String mediaurl = file.toURI().toString();
    private MediaPlayer mediaplayer;
    private Media media;

    //configuração do cronometro
    public boolean stopc = true;
    public boolean cronoPausa = false;

    private int segundo = 0;
    private int minuto = 0;

    public void iniciaCronometro() {
        Task t = new Task<Void>() {

            @Override
            public Void call() throws Exception {
                while (stopc) {
                    segundo++;

                    if (segundo == 60) {
                        minuto++;
                        segundo = 0;
                    }

                    if (minuto == 60) {

                        minuto = 0;
                    }
                    String min = minuto <= 9 ? "0" + minuto : minuto + "";
                    String seg = segundo <= 9 ? "0" + segundo : segundo + "";

                    Platform.runLater(() -> {

                        lCronometro.setText(min + ":" + seg);
                    });
                    Thread.sleep(1000);

                    while (cronoPausa) {
                        Thread.sleep(100);
                    }

                }
                return null;
            }
        };

        new Thread(t).start();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ****************
        //Chama e inicia a thread do cronometro
        iniciaCronometro();

        bTimes.setOnMouseClicked((MouseEvent ev) -> {
            try {
                MainC.mandaMSG("!NOME_TIMES@" + tfTimeEsquerda.getText() + "@" + tfTimeDireita.getText());
            } catch (Exception e) {

            }
        });
        bSomaPontoE.setOnMouseClicked((MouseEvent ev) -> {
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

        bSomaPontoD.setOnMouseClicked((MouseEvent ev) -> {
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

        bSomaAmareloD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@AMARELO@DIREITA@SOMA");
                amareloDireita++;
            } catch (IOException e) {

            }
            if (amareloDireita > 9) {
                Platform.runLater(() -> {
                    lAmareloD.setText("" + amareloDireita);
                });
            } else {
                Platform.runLater(() -> {
                    lAmareloD.setText("0" + amareloDireita);
                });
            }
        });
        bSomaAmareloE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@AMARELO@ESQUERDA@SOMA");
                amareloEsquerda++;
            } catch (IOException e) {

            }
            if (amareloEsquerda > 9) {
                Platform.runLater(() -> {
                    lAmareloE.setText("" + amareloEsquerda);
                });
            } else {
                Platform.runLater(() -> {
                    lAmareloE.setText("0" + amareloEsquerda);
                });
            }
        });

        bSomaVermelhoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@VERMELHO@DIREITA@SOMA");
                vermelhoDireita++;
            } catch (IOException e) {

            }
            if (vermelhoDireita > 9) {
                Platform.runLater(() -> {
                    lVermelhoD.setText("" + vermelhoDireita);
                });
            } else {
                Platform.runLater(() -> {
                    lVermelhoD.setText("0" + vermelhoDireita);
                });
            }
        });
        bSomaVermelhoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@VERMELHO@ESQUERDA@SOMA");
                vermelhoEsquerda++;
            } catch (IOException e) {

            }
            if (vermelhoEsquerda > 9) {
                Platform.runLater(() -> {
                    lVermelhoE.setText("" + vermelhoEsquerda);
                });
            } else {
                Platform.runLater(() -> {
                    lVermelhoE.setText("0" + vermelhoEsquerda);
                });
            }
        });

        bMenosPontoE.setOnMouseClicked((MouseEvent ev) -> {
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

        bMenosPontoD.setOnMouseClicked((MouseEvent ev) -> {
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

        bMenosAmareloD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@AMARELO@DIREITA@MENOS");
                amareloDireita--;
            } catch (IOException e) {

            }
            if (amareloDireita > 9) {
                Platform.runLater(() -> {
                    lAmareloD.setText("" + amareloDireita);
                });
            } else {
                Platform.runLater(() -> {
                    lAmareloD.setText("0" + amareloDireita);
                });
            }
        });
        bMenosAmareloE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@AMARELO@ESQUERDA@MENOS");
                amareloEsquerda--;
            } catch (IOException e) {

            }
            if (amareloEsquerda > 9) {
                Platform.runLater(() -> {
                    lAmareloE.setText("" + amareloEsquerda);
                });
            } else {
                Platform.runLater(() -> {
                    lAmareloE.setText("0" + amareloEsquerda);
                });
            }
        });

        bMenosVermelhoD.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@VERMELHO@DIREITA@MENOS");
                vermelhoDireita--;
            } catch (IOException e) {

            }
            if (vermelhoDireita > 9) {
                Platform.runLater(() -> {
                    lVermelhoD.setText("" + vermelhoDireita);
                });
            } else {
                Platform.runLater(() -> {
                    lVermelhoD.setText("0" + vermelhoDireita);
                });
            }
        });
        bMenosVermelhoE.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!TIME@VERMELHO@ESQUERDA@MENOS");
                vermelhoEsquerda--;
            } catch (IOException e) {

            }
            if (vermelhoEsquerda > 9) {
                Platform.runLater(() -> {
                    lVermelhoE.setText("" + vermelhoEsquerda);
                });
            } else {
                Platform.runLater(() -> {
                    lVermelhoE.setText("0" + vermelhoEsquerda);
                });
            }
        });

        bSomaPeriodo.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!PERIODO@SOMA");
                periodo++;
            } catch (IOException e) {

            }
            Platform.runLater(() -> {
                lacrescimo.setText(periodo + "º");
            });
        });
        
        bMenosPeriodo.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!PERIODO@SOMA");
                periodo--;
            } catch (IOException e) {

            }
            Platform.runLater(() -> {
                lacrescimo.setText(periodo + "º");
            });
        });

        bSomaAcrescimo.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = MainC.mandaMSG("!ACRESCIMO@SOMA");
                acrescimo++;
            } catch (IOException e) {

            }
            Platform.runLater(() -> {
                lacrescimo.setText(acrescimo + "");
            });
        });

        tbPausa.setOnMouseClicked((MouseEvent e) -> {

            if (tbPausa.isSelected()) {
                //if (MainC.mandaMSG("#PAUSA_CRONO").equals("PAUSADO")) {
                cronoPausa = false;
                System.out.println("false");
                try {
                    String volta = MainC.mandaMSG("!CRONO$PAUSA");
                    //}
                } catch (IOException ex) {

                }
            } else {
                //if (MainC.mandaMSG("#CONTINUA_CRONOS").equals("CONTINUA")) {
                cronoPausa = true;
                System.out.println("true");
                try {
                    String volta = MainC.mandaMSG("!CRONO$CONTINUA");
                    //}
                } catch (IOException ex) {

                }
                //}
            }
        });

        bVolta.setOnMouseClicked((MouseEvent e) -> {
            MainC.loadScene("/telas/FXMLPrincipal.fxml");
            try {
                MainC.mandaMSG("VOLTOU");
            } catch (IOException ex) {
                Logger.getLogger(FXMLFutebolController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stopc = false;
        });

    }

}
