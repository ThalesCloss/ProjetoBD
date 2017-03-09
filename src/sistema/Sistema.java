/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocio.NegocioException;
import negocio.UsuarioNegocio;
import persistencia.Conexao;
import persistencia.PersistenciaException;
import vo.UsuarioVO;

/**
 *
 * @author tcloss
 */
public class Sistema extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(new URL("view/usuario/cadastro.fxml"));
        Scene sena = new Scene(root);
        stage.setScene(sena);
        stage.setTitle("Cad usr");
        stage.showAndWait();
    }
    
}
