package mainC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainC extends Application {

    public static Stage primaryStage;
    public static Scene sceneBasquete, scenePrincipal;
    public static Class thisClass;
    public static Socket cliente;
    public static ObjectInputStream entrada;
    public static ObjectOutputStream saida;
    public String TimeEsquerda, TimeDireita;

    /**
     * A classe principal da aplicação em JavaFX
     */
    public MainC() {
        thisClass = getClass();
    }

    /**
     * Inicia o layout da aplicação
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        setStage(primaryStage);
        conectar();
        loadScene("/telas/FXMLLogin.fxml");
    }

    public static void main(String[] args) {
        launch(args);

    }

    public void setStage(Stage s) {
        this.primaryStage = s;
    }

    public static void loadScene(String local) {

        try {
            Parent root = FXMLLoader.load(thisClass.getClass().getResource(local));
            scenePrincipal = new Scene(root);
            Platform.runLater(() -> {
                primaryStage.setScene(scenePrincipal);
                primaryStage.setTitle("Cliente");
                //primaryStage.initStyle(StageStyle.TRANSPARENT);
                primaryStage.centerOnScreen();
                primaryStage.show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static void conectar() throws IOException {
        cliente = new Socket("localhost", 9696);
        entrada = new ObjectInputStream(cliente.getInputStream());
        saida = new ObjectOutputStream(cliente.getOutputStream());
    }

    public static String mandaMSG(String msg) throws IOException {

        saida.writeUTF(msg);
        saida.flush();

        String retorno = entrada.readUTF();
        System.out.println(retorno);

//        entrada.close();
//        saida.close();
        return retorno;
    }

    
}
