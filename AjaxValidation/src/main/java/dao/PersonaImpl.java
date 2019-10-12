package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Persona;

public class PersonaImpl extends Conexion implements IPersona {

    private Persona Persona;

    @Override
    public void registrar(Persona persona) throws Exception {
        String sql = "insert into PERSONA (NOMPER,APEPER,DNIPER,USUPER,PASSPER,ESTPER) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDNIPER());
            ps.setString(4, persona.getUSUPER());
            ps.setString(5, persona.getPASSPER());
            ps.setString(6, persona.getESTPER());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al registrar " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        String sql = "update PERSONA set NOMPER=?,APEPER=?,DNIPER=?,USUPER=?,PASSPER=?,ESTPER=? where IDPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDNIPER());
            ps.setString(4, persona.getUSUPER());
            ps.setString(5, persona.getPASSPER());
            ps.setString(6, persona.getESTPER());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.err.println("Error al modificar" + e.getMessage());
            throw e;

        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Persona persona) throws Exception {
        String sql = "delete from PERSONA where IDPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, persona.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.err.println("Error al modificar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }

    }

    @Override
    public List<Persona> listarPer() throws Exception {
        this.conectar();
        List<Persona> listado;
        Persona per;
        String sql = "select * from PERSONA";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                per = new Persona();
                per.setIDPER(rs.getInt("IDPER"));
                per.setNOMPER(rs.getString("NOMPER"));
                per.setAPEPER(rs.getString("APEPER"));
                per.setDNIPER(rs.getString("DNIPER"));
                per.setUSUPER(rs.getString("USUPER"));
                per.setPASSPER(rs.getString("PASSPER"));
                per.setESTPER(rs.getString("ESTPER"));
                listado.add(per);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }
}
