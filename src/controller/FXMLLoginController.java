/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mainC.Main;

/**
 *
 * @author Elison
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField tflogin;
    @FXML
    private PasswordField pfsenha;
    @FXML
    private Button bacessar;
    @FXML
    private Button bsair;

    @FXML
    void checkLogin(KeyEvent ev) {
        if (ev.getCode() == KeyCode.ENTER) {
            login();
        } else if (ev.getCode() == KeyCode.ESCAPE) {
            Main.getStage().close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bacessar.setOnMouseClicked((MouseEvent e) -> {
            login();
//        if (tflogin.getText().equals("admin") && pfsenha.getText().equals("admin")) {
            //              Main.loadScene("");
            //        try {
            //  
            //} catch (Exception ex) {
            //      Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
            //    }
            //  } else {
            //        Alert alert = new Alert(Alert.AlertType.ERROR);
            //        alert.setTitle("Erro");
            //        alert.setHeaderText("Login ou Senha inválidos!");
            //        alert.setContentText("Digite novamente seu Login e sua Senha! ");
            //        alert.show();
            //     //     }
        });

        bsair.setOnMouseClicked((MouseEvent e) -> {
            Main.getStage().close();
        });
    }

    private void login() {
        /*try {
            Main.conectar();
        } catch (IOException e) {

        }
        */
        String user = tflogin.getText();
        String password = pfsenha.getText();
        if (user.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Login ou Senha em branco!");
            alert.setContentText("Digite seu Login e sua Senha! ");
            alert.show();
        } else {
            try {
                String[] msg = Main.mandaMSG("!LOGIN@" + user + "@" + password).split("\\@");
                if (msg[0].equals("!LOGADO")) {
                    if (msg[1].equals("ADM")) {
                        // implementar tela de cadastro
                    } else if (msg[1].equals("PLACAR")) {
                        Main.loadScene("/tela/FXMLPrincipal.fxml");
                    } else if (msg[1].equals("PROP")) {
                        // implementar tela de propaganda
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Login ou Senha inválidos!");
                    alert.setContentText("Digite novamente seu Login e sua Senha! ");
                    alert.show();
                }
            } catch (IOException ex) {

            }

        }

    }

    public void fecha(ActionEvent e) {
        Main.getStage().close();
    }
}
