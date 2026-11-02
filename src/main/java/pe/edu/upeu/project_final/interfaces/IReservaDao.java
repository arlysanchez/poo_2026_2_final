package pe.edu.upeu.project_final.interfaces;

import pe.edu.upeu.project_final.modelo.Reserva;

import java.util.List;

public interface IReservaDao {
    public boolean insert(Reserva r);
    public boolean update(Reserva r);
    public Reserva searchById(int id);
    public boolean delete(Reserva r);
    public List<Reserva> listAll();
    public boolean updateStatus(Reserva r);
    public boolean validateHabitacion(Reserva r);
}
