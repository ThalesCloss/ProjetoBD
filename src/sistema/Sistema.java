/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import controlador.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author tcloss
 */
public class Sistema extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxLogin = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        Parent root = (Parent) fxLogin.load();
        Stage palco = new Stage(StageStyle.DECORATED);
        Scene cena = new Scene(root);
        LoginController loginController = (LoginController) fxLogin.getController();
        loginController.setPalco(palco);
        palco.setScene(cena);
        palco.setTitle("Login");
        palco.showAndWait();
        if(loginController.isLogado())
        {
            FXMLLoader fxPrincipal=new FXMLLoader(getClass().getResource("../view/principal.fxml"));
            root=(Parent) fxPrincipal.load();
            cena.setRoot(root);
            stage.setScene(cena);
            stage.setTitle("Sistema");
            stage.show();
        }
        
    }

}
