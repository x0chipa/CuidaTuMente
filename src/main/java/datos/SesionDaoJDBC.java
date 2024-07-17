package datos;

import dominio.Sesion;
import java.sql.*;
import java.util.*;

public class SesionDaoJDBC {

    private static final String SQL_SELECT = "SELECT id, fecha, hora FROM sesion";
    private static final String SQL_SELECT_BY_ID = "SELECT id, fecha, hora FROM sesion WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE sesion SET fecha=?, hora=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM sesion WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO sesion(id, fecha, hora) VALUES(?, ?, ?)";
    private static final String SQL_SELECT_MAX_ID = "SELECT MAX(id) FROM sesion";

    public List<Sesion> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Sesion sesion;
        List<Sesion> sesiones = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                java.sql.Date fecha = rs.getDate("fecha");
                java.sql.Time hora = rs.getTime("hora");

                sesion = new Sesion(id, fecha, hora);
                sesiones.add(sesion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return sesiones;
    }

    public Sesion encontrar(Sesion sesion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, sesion.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                java.sql.Date fecha = rs.getDate("fecha");
                java.sql.Time hora = rs.getTime("hora");

                sesion = new Sesion(id, fecha, hora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return sesion;
    }

    public int insertar(Sesion sesion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = generarIdUnico(conn);
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, id);
            stmt.setDate(2, sesion.getFecha());
            stmt.setTime(3, sesion.getHora());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return id;
    }

    private int generarIdUnico(Connection conn) {
        int id = 0;
        try {
            conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_MAX_ID);
            if (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
        }
        return id;
    }

    public int actualizar(Sesion sesion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setDate(1, sesion.getFecha());
            stmt.setTime(2, sesion.getHora());
            stmt.setInt(3, sesion.getId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Sesion sesion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, sesion.getId());
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
