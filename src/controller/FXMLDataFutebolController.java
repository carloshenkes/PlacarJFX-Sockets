package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import mainC.Main;

/**
 * FXML Controller class
 *
 * @author Carlos Eduardo Henkes
 */
public class FXMLDataFutebolController implements Initializable {

    @FXML
    private Button bInicia;
    @FXML
    private Button bTimes;
    @FXML
    private Button bVoltar;
    @FXML
    private ToggleButton tbPausa;
    @FXML
    private TextField tfTimeEsquerda;
    @FXML
    private Label lTimeEsquerda;
    @FXML
    private Button bPontosEsquerda;
    @FXML
    private Button bFaltaEsquerda;
    @FXML
    private Button bAmareloEsquerda;
    @FXML
    private Button bVermelhoEsquerda;
    @FXML
    private TextField tfTimeDireita;
    @FXML
    private Label lTimeDireita;
    @FXML
    private Button bPontosDireita;
    @FXML
    private Button bFaltaDireita;
    @FXML
    private Button bAmareloDireita;
    @FXML
    private Button bVermelhoDireita;

    private int pontosDireita = 0;
    private int pontosEsquerda = 0;
    private int faltasDireita = 0;
    private int faltasEsquerda =0;
    private int amareloDireita = 0;
    private int amareloEsquerda =0;
    private int vermelhoDireita = 0;
    private int vermelhoEsquerda =0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bTimes.setOnMouseClicked((MouseEvent ev) -> {
            lTimeDireita.setText(tfTimeDireita.getText());
            lTimeEsquerda.setText(tfTimeEsquerda.getText());
            try {
                Main.mandaMSG("!NOME_TIMES@" + tfTimeEsquerda.getText() + "@" + tfTimeDireita.getText());
            } catch (Exception e) {

            }
        });

        bPontosDireita.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@DIREITA@SOMA@UM");
                if (volta.equals("!BLZ")) {
                    pontosDireita++;
                }
            } catch (IOException e) {

            }
        });

        bPontosEsquerda.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@ESQUERDA@SOMA@UM");
                if (volta.equals("!BLZ")) {
                    pontosEsquerda++;
                }
            } catch (IOException e) {

            }
        });

        bFaltaDireita.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@FALTA@DIREITA@SOMA");
                if (volta.equals("!BLZ")) {
                    faltasDireita++;
                }
            } catch (IOException e) {

            }
        });
        bFaltaEsquerda.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@FALTA@ESQUERDA@SOMA");
                if (volta.equals("!BLZ")) {
                    faltasEsquerda++;
                }
            } catch (IOException e) {

            }
        });
        
        bAmareloDireita.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@AMARELO@DIREITA@SOMA");
                if (volta.equals("!BLZ")) {
                    amareloDireita++;
                }
            } catch (IOException e) {

            }
        });
        bAmareloEsquerda.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@AMARELO@ESQUERDA@SOMA");
                if (volta.equals("!BLZ")) {
                    amareloEsquerda++;
                }
            } catch (IOException e) {

            }
        });
        
        bVermelhoDireita.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@VERMELHO@DIREITA@SOMA");
                if (volta.equals("!BLZ")) {
                    vermelhoDireita++;
                }
            } catch (IOException e) {

            }
        });
        bVermelhoEsquerda.setOnMouseClicked((MouseEvent ev) -> {
            try {
                String volta = Main.mandaMSG("!TIME@VERMELHO@ESQUERDA@SOMA");
                if (volta.equals("!BLZ")) {
                    vermelhoEsquerda++;
                }
            } catch (IOException e) {

            }
        }); 
        bVoltar.setOnMouseClicked((MouseEvent e) -> {
            Main.loadScene("/tela/FXMLPrincipal.fxml");
        });

    }

}
