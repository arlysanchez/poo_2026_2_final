package pe.edu.upeu.project_final.implementacion;

import pe.edu.upeu.project_final.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final.modelo.Cliente;
import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.utils.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImpl implements IHabitacionDao {
    @Override
    public boolean insert(Habitacion h) {
        boolean flag = false;
        String query = "INSERT INTO habitacion(numero,numeroPiso,numeroHabitacion,tipo,precio,estado)" +
                "VALUES(?,?,?,?,?,?);";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1,h.getNumero());
            st.setString(2,h.getNumeroPiso());
            st.setString(3,h.getNumeroHabitacion());
            st.setString(4,h.getTipo());
            st.setDouble(5,h.getPrecio());
            st.setString(6,h.getEstado());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al insertar habitacion:" + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean update(Habitacion h) {
        return false;
    }

    @Override
    public Habitacion searchById(int id) {
        return null;
    }

    @Override
    public boolean delete(Habitacion h) {
        return false;
    }

    @Override
    public List<Habitacion> listAll() {
        List<Habitacion> lista = new ArrayList<>();
        String query = "SELECT * FROM habitacion;";
        try (
                Connection cn = ConexionDB.getConnection();
                PreparedStatement st = cn.prepareStatement(query);
                ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()){
                Habitacion h = new Habitacion();
                h.setIdhabitacion(rs.getInt("idhabitacion"));
                h.setNumero(rs.getString("numero"));
                h.setNumeroPiso(rs.getString("numeroPiso"));
                h.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                h.setTipo(rs.getString("tipo"));
                h.setPrecio(rs.getDouble("precio"));
                h.setEstado(rs.getString("estado"));
                lista.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar habitacion:" + e.getMessage());
        }
        return lista;
    }

    @Override
    public Habitacion searchByNumero(String numero) {
        Habitacion h = null;
        String query = "SELECT * FROM habitacion WHERE numero=?;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1,numero);
            try( ResultSet rs = st.executeQuery()) {
                if (rs.next()){
                    h = new Habitacion();
                    h.setIdhabitacion(rs.getInt("idhabitacion"));
                    h.setNumero(rs.getString("numero"));
                    h.setNumeroPiso(rs.getString("numeroPiso"));
                    h.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                    h.setTipo(rs.getString("tipo"));
                    h.setPrecio(rs.getDouble("precio"));
                    h.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar habitacion por numero:" + e.getMessage());
        }
        return h;
    }
}
