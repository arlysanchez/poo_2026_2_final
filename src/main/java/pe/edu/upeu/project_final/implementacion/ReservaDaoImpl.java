package pe.edu.upeu.project_final.implementacion;

import pe.edu.upeu.project_final.interfaces.IReservaDao;
import pe.edu.upeu.project_final.modelo.Cliente;
import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.modelo.Reserva;
import pe.edu.upeu.project_final.modelo.Usuario;
import pe.edu.upeu.project_final.utils.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDaoImpl implements IReservaDao {

    @Override
    public boolean insert(Reserva r) {
        boolean flag = false;
        String query = " INSERT INTO reserva (clienteId,habitacionId, fecha_reserva, fecha_ingreso," +
                       " fecha_salida,estado,total,usuarioId) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1,r.getCliente().getIdcliente());
            st.setInt(2,r.getHabitacion().getIdhabitacion());
            //LocalDate.toString devuelve "yyyy-MM-dd"
            st.setString(3,r.getFechaReserva().toString());
            st.setString(4,r.getFechaIngreso().toString());
            st.setString(5,r.getFechaSalida().toString());
            st.setString(6,r.getEstado());
            st.setDouble(7,r.getTotal());
            st.setInt(8,r.getUsuario().getIdusuario());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al insertar reserva:" + e.getMessage());
        }

        return flag;
    }

    @Override
    public boolean update(Reserva r) {
        return false;
    }

    @Override
    public Reserva searchById(int id) {
        return null;
    }

    @Override
    public boolean delete(Reserva r) {
        return false;
    }

    @Override
    public List<Reserva> listAll() {
        List<Reserva> lista = new ArrayList<>();
        String query = " SELECT r.*,c.nombre || ' ' ||c.apellidos AS nombre_completo, " +
                " c.dni,c.email,h.numero, " +
                " h.numeroPiso,h.numeroHabitacion,h.tipo,h.precio, " +
                " h.estado AS estado_hab,u.user " +
                " FROM reserva r,cliente c, habitacion h,usuario u " +
                " WHERE r.clienteId = c.idcliente " +
                " AND r.habitacionId = h.idhabitacion " +
                " AND r.usuarioId = u.idusuario ";
        try (
                Connection cn = ConexionDB.getConnection();
                PreparedStatement st = cn.prepareStatement(query);
                ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()){
                //instancia de objetos relacionados
                Cliente cl = new Cliente();
                cl.setIdcliente(rs.getInt("clienteId"));
                cl.setNombre(rs.getString("nombre_completo"));
                cl.setDni(rs.getString("dni"));
                cl.setEmail(rs.getString("email"));

                Habitacion h = new Habitacion();
                h.setIdhabitacion(rs.getInt("habitacionId"));
                h.setNumero(rs.getString("numero"));
                h.setPrecio(rs.getDouble("precio"));
                h.setTipo(rs.getString("tipo"));

                Usuario u = new Usuario();
                u.setIdusuario(rs.getInt("usuarioId"));
                u.setUser(rs.getString("user"));

                //crear la reserva y asignar objetos
                Reserva r = new Reserva();
                r.setIdreserva(rs.getInt("idreserva"));
                r.setFechaReserva(LocalDate.parse(rs.getString("fecha_reserva")));
                r.setFechaIngreso(LocalDate.parse(rs.getString("fecha_ingreso")));
                r.setFechaSalida(LocalDate.parse(rs.getString("fecha_salida")));
                r.setTotal(rs.getDouble("total"));
                r.setEstado(rs.getString("estado"));
                //conectamos los objetos
                r.setCliente(cl);
                r.setHabitacion(h);
                r.setUsuario(u);

                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar reserva:" + e.getMessage());
        }
        return lista;

    }

    @Override
    public boolean updateStatus(Reserva r) {
        boolean flag = false;
        String query = " UPDATE reserva SET estado = ? WHERE idreserva=?;  ";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1,r.getEstado());
            st.setInt(2,r.getIdreserva());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar reserva:" + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean validateHabitacion(Reserva r) {
        boolean flag = false;
        String query = " SELECT count(*) FROM reserva WHERE habitacionId=? " +
                " AND estado IN('R','O') " +
                " AND ( ? < fecha_salida AND ? > fecha_ingreso ) ";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1,r.getHabitacion().getIdhabitacion());
            st.setString(2,r.getFechaIngreso().toString());
            st.setString(3,r.getFechaSalida().toString());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al validar habitacion:" + e.getMessage());
        }
        return flag;
    }
}
