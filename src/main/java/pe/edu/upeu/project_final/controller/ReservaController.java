package pe.edu.upeu.project_final.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.edu.upeu.project_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.project_final.interfaces.IReservaDao;
import pe.edu.upeu.project_final.modelo.Reserva;
import pe.edu.upeu.project_final.utils.UtilAlerts;

import java.time.LocalDate;
import java.util.Optional;

public class ReservaController {
    private static UtilAlerts utilAlerts = new UtilAlerts();
    @FXML
    private TableView<Reserva> TableReserva;
    @FXML
    private TableColumn<Reserva,Number> ColNumero;
    @FXML
    private TableColumn<Reserva,Integer> ColId;
    @FXML
    private TableColumn<Reserva,String> ColCliente;
    @FXML
    private TableColumn<Reserva,String> ColHabitacion;
    @FXML
    private TableColumn<Reserva, LocalDate> ColIngreso;
    @FXML
    private TableColumn<Reserva, LocalDate> ColSalida;
    @FXML
    private TableColumn<Reserva, Double> ColTotal;
    @FXML
    private TableColumn<Reserva,String> ColEstado;

    private final IReservaDao reservaDao = new ReservaDaoImpl();
    private ObservableList<Reserva> listaReserva;

    @FXML
    private void initialize(){
        ColNumero.setCellFactory(colum -> new TableCell<>(){
            @Override
            protected void updateItem(Number item, boolean empty){
                super.updateItem(item,empty);
                if (empty){
                    setText(null);
                }else{
                    setText(String.valueOf(getIndex() +1));
                }
            }
        });
        ColId.setCellValueFactory(
                cd -> cd.getValue().idreservaProperty().asObject());
        ColCliente.setCellValueFactory(
                cd -> cd.getValue().getCliente().nombreProperty());
        ColHabitacion.setCellValueFactory(
                cd -> cd.getValue().getHabitacion().numeroProperty() );
        ColIngreso.setCellValueFactory(
                cd -> cd.getValue().fechaIngresoProperty());
        ColSalida.setCellValueFactory(
                cd -> cd.getValue().fechaSalidaProperty());
        ColTotal.setCellValueFactory(
                cd-> cd.getValue().totalProperty().asObject());
        ColEstado.setCellValueFactory(
                cd->{
                    String estadoBD = cd.getValue().getEstado();
                    String estadoTexto = switch (estadoBD){
                        case "R" -> "Reservada";
                        case "O" -> "Ocupada";
                        case "T" -> "Terminada";
                        case "C" -> "Cancelada";
                        default -> "Desconocido";
                    };
                    return new SimpleStringProperty(estadoTexto);
                });
        ColEstado.setCellFactory(
                cl-> new TableCell<Reserva,String>(){
                    @Override
                    protected void updateItem(String estado, boolean empty){
                        super.updateItem(estado,empty);
                        if (empty || estado == null){ setText(null);setStyle("");
                        }else{
                            setText(estado);
                            switch (estado){
                                case "Reservada"-> setStyle("-fx-text-fill: #1B5E20;-fx-font-weight: bold;");
                                case "Ocupada"-> setStyle("-fx-text-fill: #B71C1C;-fx-font-weight: bold;");
                                case "Terminada"-> setStyle("-fx-text-fill: #0D47A1;-fx-font-weight: bold;");
                                case "Cancelada"-> setStyle("-fx-text-fill: #424242;-fx-font-weight: bold;");
                                default -> setStyle("");
                            }
                        }
                    }

                });


        cargarDatos();

    }
    private void cargarDatos(){
        listaReserva = FXCollections.observableArrayList(reservaDao.listAll());
        TableReserva.setItems(listaReserva);
    }

    @FXML
    private void handleNew(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/pe/edu/upeu/project_final/views/reserva-form.fxml"));
            Parent root =loader.load();
            ReservaFormController controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Nueva Reserva");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (controller.isSaved()){
                cargarDatos();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = utilAlerts.mostrarAlerts("Error",
                    "No se pudo abrir el formulario","ERROR");
        }

    }
    @FXML
    private void handleCheckIn(){
        updateStatus("O","Confirmar CheckIn",
                "Desea marcar la habitacion como ocupada?");
    }
    @FXML
    private void handleCheckOut(){
        updateStatus("T","Confirmar CheckOut",
                "Desea finalizar la reserva?");
    }
    @FXML
    private void handleCancel(){
        updateStatus("C","Confirmar Cancelación",
                "Esta seguro de CANCELAR esta reserva?");
    }
    private void updateStatus(String nuevoEstado, String titulo, String mensaje){
        Reserva select = TableReserva.getSelectionModel().getSelectedItem();
        if (select == null){
            Alert alert = utilAlerts.mostrarAlerts("Seleccion requerida",
                    "seleccione una reserva de la tabla","WARNING");
            alert.showAndWait();
            return;
        }
        Alert confir = utilAlerts.mostrarAlerts(titulo,mensaje,"CONFIRMATION");

        Optional<ButtonType> result = confir.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            select.setEstado(nuevoEstado);
            if (reservaDao.updateStatus(select)){
                cargarDatos();
            }
        }
    }

}
