package pe.edu.upeu.project_final.modelo;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {
    private final IntegerProperty idreserva = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> fechaReserva = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> fechaIngreso = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> fechaSalida = new SimpleObjectProperty<>();
    private final StringProperty estado = new SimpleStringProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();
    //asociacion de las llaves foraneas
    private final ObjectProperty<Cliente> cliente = new SimpleObjectProperty<>();
    private final ObjectProperty<Habitacion> habitacion = new SimpleObjectProperty<>();
    private final ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>();

    public Reserva() {
    }


    public final Usuario getUsuario() {
        return usuario.get();
    }

    public final void setUsuario(Usuario value) {
        usuario.set(value);
    }

    public final ObjectProperty<Usuario> usuarioProperty() {
        return usuario;
    }




    public final Habitacion getHabitacion() {
        return habitacion.get();
    }

    public final void setHabitacion(Habitacion value) {
        habitacion.set(value);
    }

    public final ObjectProperty<Habitacion> habitacionProperty() {
        return habitacion;
    }


    public final Cliente getCliente() {
        return cliente.get();
    }

    public final void setCliente(Cliente value) {
        cliente.set(value);
    }

    public final ObjectProperty<Cliente> clienteProperty() {
        return cliente;
    }


    public final double getTotal() {
        return total.get();
    }

    public final void setTotal(double value) {
        total.set(value);
    }

    public final DoubleProperty totalProperty() {
        return total;
    }


    public final String getEstado() {
        return estado.get();
    }

    public final void setEstado(String value) {
        estado.set(value);
    }

    public final StringProperty estadoProperty() {
        return estado;
    }


    public final LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public final void setFechaSalida(LocalDate value) {
        fechaSalida.set(value);
    }

    public final ObjectProperty<LocalDate> fechaSalidaProperty() {
        return fechaSalida;
    }


    public final LocalDate getFechaIngreso() {
        return fechaIngreso.get();
    }

    public final void setFechaIngreso(LocalDate value) {
        fechaIngreso.set(value);
    }

    public final ObjectProperty<LocalDate> fechaIngresoProperty() {
        return fechaIngreso;
    }


    public final LocalDate getFechaReserva() {
        return fechaReserva.get();
    }

    public final void setFechaReserva(LocalDate value) {
        fechaReserva.set(value);
    }

    public final ObjectProperty<LocalDate> fechaReservaProperty() {
        return fechaReserva;
    }


    public final int getIdreserva() {
        return idreserva.get();
    }

    public final void setIdreserva(int value) {
        idreserva.set(value);
    }

    public final IntegerProperty idreservaProperty() {
        return idreserva;
    }


}
