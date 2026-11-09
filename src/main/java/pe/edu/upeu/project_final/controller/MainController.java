package pe.edu.upeu.project_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pe.edu.upeu.project_final.modelo.UserSession;


import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentArea;
    @FXML
    private Label lblUsuario;

    @FXML
    public void initialize(){
        if (UserSession.getInstance().getUserLogin() !=null){
            lblUsuario.setText("Bienvenido, " +
                    UserSession.getInstance().getUserLogin().getNombre());
        }
        showHome();
    }
    @FXML
    private  void showHome(){
        loadView("home-view.fxml");
    }
    @FXML
    private void showClient(){
        loadView("cliente-view.fxml");
    }
    @FXML
    private void showHabitacion(){
        loadView("habitacion-view.fxml");
    }
    @FXML
    private void showReserva(){
        loadView("reserva-view.fxml");
    }
    @FXML
    private void handleLogout(){
        UserSession.getInstance().cleanSession();
        System.exit(0);
    }
    //metodo que llama y busca a las vistas
    private void loadView(String fxml){
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/pe/edu/upeu/project_final/views/"+fxml));
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            System.err.println("Error de carga de vista"+e.getMessage());
        }
    }

}
