package pe.edu.upeu.project_final.implementacion;

import pe.edu.upeu.project_final.interfaces.IUsuarioDao;
import pe.edu.upeu.project_final.modelo.Usuario;
import pe.edu.upeu.project_final.utils.ConexionDB;

import java.sql.*;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public Usuario login(String user, String password) {
        Usuario us = null;
        String query = " SELECT * FROM usuario WHERE user = ? AND password = ?; ";
        try(Connection cn = ConexionDB.getConnection();
            PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1,user);
            st.setString(2,password);
            try(ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    us = new Usuario();
                    //Data table user
                    us.setIdusuario(rs.getInt("idusuario"));
                    us.setUser(rs.getString("user"));
                    us.setTipoUser(rs.getString("tipoUser"));
                    //Data heredada
                    us.setNombre(rs.getString("nombre"));
                    us.setApellidos(rs.getString("apellidos"));
                    us.setDni(rs.getString("dni"));
                    System.out.println("Login exitoso");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en el login"+e.getMessage());
        }
        return us;
    }

    @Override
    public boolean insert_user(Usuario u) {
        return false;
    }
}
