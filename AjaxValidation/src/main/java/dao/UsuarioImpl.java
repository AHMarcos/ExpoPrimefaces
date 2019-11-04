package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author Marcos Alarcon
 */
public class UsuarioImpl extends Conexion {

    public Usuario startSession(String User, String Pass) throws Exception {
//        this.conectar();
        ResultSet rs;
        Usuario user = null;
        try {
            String sql = "SELECT\n"
                    + "	IDPER,\n"
                    + "	NOMPER,\n"
                    + "	APEPER,\n"
                    + "	DNIPER,\n"
                    + "	TIPPER\n"
                    + "FROM PERSONA \n"
                    + "WHERE USUPER LIKE ? AND PASSPER LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, (Pass));
            System.out.println("USER " + User);
            System.out.println("PASS " + Pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Aqui");
                user = new Usuario();
                user.setCodigo(rs.getString("IDPER"));
                user.setNombre(rs.getString("NOMPER"));
                user.setApellido(rs.getString("APEPER"));
                user.setDNI(rs.getString("DNIPER"));
                user.setNivel(rs.getString("TIPPER"));
                user.setUsuario(User);
                user.setPassword(Pass);
//            } else {
//                System.out.println("No hay nada de regreso :'v");
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error al iniciar session");
            throw e;
        }
    }

}
