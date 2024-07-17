package datos;

import dominio.Instrumento;
import java.sql.*;
import java.util.*;

public class InstrumentoDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_instrumento, nombre FROM instrumento";
    private static final String SQL_SELECT_BY_ID = "SELECT id_instrumento, nombre FROM instrumento WHERE id_instrumento = ?";
    private static final String SQL_INSERT = "INSERT INTO instrumento(nombre) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE instrumento SET nombre=? WHERE id_instrumento=?";
    private static final String SQL_DELETE = "DELETE FROM instrumento WHERE id_instrumento = ?";

    public List<Instrumento> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Instrumento instrumento;
        List<Instrumento> instrumentos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idInstrumento = rs.getInt("id_instrumento");
                String nombre = rs.getString("nombre");

                instrumento = new Instrumento(idInstrumento, nombre);
                instrumentos.add(instrumento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return instrumentos;
    }

    public Instrumento encontrar(Instrumento instrumento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, instrumento.getIdInstrumento());
            rs = stmt.executeQuery();
            rs.absolute(1); // Nos posicionamos en el primer registro
            String nombre = rs.getString("nombre");

            instrumento.setNombre(nombre);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return instrumento;
    }

    public int insertar(Instrumento instrumento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, instrumento.getNombre());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Instrumento instrumento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, instrumento.getNombre());
            stmt.setInt(2, instrumento.getIdInstrumento());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Instrumento instrumento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, instrumento.getIdInstrumento());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
