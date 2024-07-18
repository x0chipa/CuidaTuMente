package datos;

import dominio.Respuesta;
import java.sql.*;
import java.util.*;

public class RespuestaDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_respuestas, sesion_id, pregunta_id, respuesta FROM respuestas";
    private static final String SQL_SELECT_BY_ID = "SELECT id_respuestas, sesion_id, pregunta_id, respuesta FROM respuestas WHERE id_respuestas = ?";
    private static final String SQL_SELECT_BY_PREGUNTA_ID = "SELECT id_respuestas, sesion_id, pregunta_id, respuesta FROM respuestas WHERE pregunta_id = ?";
    private static final String SQL_SELECT_BY_SESION_ID = "SELECT id_respuestas, sesion_id, pregunta_id, respuesta FROM respuestas WHERE sesion_id = ?";
    private static final String SQL_SELECT_BY_RESPUESTA = "SELECT id_respuestas, sesion_id, pregunta_id, respuesta FROM respuestas WHERE respuesta = ?";
    private static final String SQL_INSERT = "INSERT INTO respuestas(sesion_id, pregunta_id, respuesta) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE respuestas SET sesion_id=?, pregunta_id=?, respuesta=? WHERE id_respuestas=?";
    private static final String SQL_DELETE = "DELETE FROM respuestas WHERE id_respuestas = ?";
    

    public List<Respuesta> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Respuesta respuesta;
        List<Respuesta> respuestas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idRespuestas = rs.getInt("id_respuestas");
                int sesionId = rs.getInt("sesion_id");
                int preguntaId = rs.getInt("pregunta_id");
                int respuestaValor = rs.getInt("respuesta");

                respuesta = new Respuesta(idRespuestas, sesionId, preguntaId, respuestaValor);
                respuestas.add(respuesta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return respuestas;
    }

    public Respuesta encontrar(Respuesta respuesta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, respuesta.getIdRespuestas());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idRespuestas = rs.getInt("id_respuestas");
                int sesionId = rs.getInt("sesion_id");
                int preguntaId = rs.getInt("pregunta_id");
                int respuestaValor = rs.getInt("respuesta");

                respuesta.setIdRespuestas(idRespuestas);
                respuesta.setSesionId(sesionId);
                respuesta.setPreguntaId(preguntaId);
                respuesta.setRespuesta(respuestaValor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return respuesta;
    }

    public List<Respuesta> encontrarPorPreguntaId(int preguntaId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Respuesta> respuestas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_PREGUNTA_ID);
            stmt.setInt(1, preguntaId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idRespuestas = rs.getInt("id_respuestas");
                int sesionId = rs.getInt("sesion_id");
                int respuestaValor = rs.getInt("respuesta");

                Respuesta respuesta = new Respuesta(idRespuestas, sesionId, preguntaId, respuestaValor);
                respuestas.add(respuesta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return respuestas;
    }

    public List<Respuesta> encontrarPorSesionId(int sesionId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Respuesta> respuestas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_SESION_ID);
            stmt.setInt(1, sesionId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idRespuestas = rs.getInt("id_respuestas");
                int preguntaId = rs.getInt("pregunta_id");
                int respuestaValor = rs.getInt("respuesta");

                Respuesta respuesta = new Respuesta(idRespuestas, sesionId, preguntaId, respuestaValor);
                respuestas.add(respuesta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return respuestas;
    }

    public List<Respuesta> encontrarPorRespuesta(int respuestaValor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Respuesta> respuestas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_RESPUESTA);
            stmt.setInt(1, respuestaValor);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idRespuestas = rs.getInt("id_respuestas");
                int sesionId = rs.getInt("sesion_id");
                int preguntaId = rs.getInt("pregunta_id");

                Respuesta respuesta = new Respuesta(idRespuestas, sesionId, preguntaId, respuestaValor);
                respuestas.add(respuesta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return respuestas;
    }

    public int insertar(Respuesta respuesta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, respuesta.getSesionId());
            stmt.setInt(2, respuesta.getPreguntaId());
            stmt.setInt(3, respuesta.getRespuesta());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Respuesta respuesta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, respuesta.getSesionId());
            stmt.setInt(2, respuesta.getPreguntaId());
            stmt.setInt(3, respuesta.getRespuesta());
            stmt.setInt(4, respuesta.getIdRespuestas());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Respuesta respuesta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, respuesta.getIdRespuestas());
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
