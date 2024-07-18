package datos;

import dominio.Pregunta;
import dominio.Respuesta;
import java.sql.*;
import java.util.*;

public class PreguntaDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_pregunta, Instrumento_id, pregunta, respuesta_1, respuesta_2, respuesta_3, respuesta_4, respuesta_5 FROM preguntas";
    private static final String SQL_SELECT_BY_ID = "SELECT id_pregunta, Instrumento_id, pregunta, respuesta_1, respuesta_2, respuesta_3, respuesta_4, respuesta_5 FROM preguntas WHERE id_pregunta = ?";
    private static final String SQL_SELECT_BY_INSTRUMENTO_ID = "SELECT id_pregunta, Instrumento_id, pregunta, respuesta_1, respuesta_2, respuesta_3, respuesta_4, respuesta_5 FROM preguntas WHERE Instrumento_id = ?";
    private static final String SQL_INSERT = "INSERT INTO preguntas(Instrumento_id, pregunta, respuesta_1, respuesta_2, respuesta_3, respuesta_4, respuesta_5) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE preguntas SET Instrumento_id=?, pregunta=?, respuesta_1=?, respuesta_2=?, respuesta_3=?, respuesta_4=?, respuesta_5=? WHERE id_pregunta=?";
    private static final String SQL_DELETE = "DELETE FROM preguntas WHERE id_pregunta = ?";
    private static final String SQL_SELECT_BY_RESPUESTAS = "SELECT * FROM pregunta WHERE id_pregunta = ?";
     private static final String SQL_SELECT_BY_ID_2 = "SELECT id_pregunta, Instrumento_id, pregunta, respuesta_1, respuesta_2, respuesta_3, respuesta_4, respuesta_5 FROM preguntas WHERE id_pregunta = ?";

    public List<Pregunta> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pregunta pregunta;
        List<Pregunta> preguntas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPregunta = rs.getInt("id_pregunta");
                int instrumentoId = rs.getInt("Instrumento_id");
                String textoPregunta = rs.getString("pregunta");
                String respuesta1 = rs.getString("respuesta_1");
                String respuesta2 = rs.getString("respuesta_2");
                String respuesta3 = rs.getString("respuesta_3");
                String respuesta4 = rs.getString("respuesta_4");
                String respuesta5 = rs.getString("respuesta_5");

                pregunta = new Pregunta(idPregunta, instrumentoId, textoPregunta, respuesta1, respuesta2, respuesta3, respuesta4, respuesta5);
                preguntas.add(pregunta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return preguntas;
    }

    public List<Pregunta> listarPorInstrumentoId(int instrumentoId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pregunta> preguntas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_INSTRUMENTO_ID);
            stmt.setInt(1, instrumentoId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPregunta = rs.getInt("id_pregunta");
                int instrumentoIdDb = rs.getInt("Instrumento_id");
                String textoPregunta = rs.getString("pregunta");
                String respuesta1 = rs.getString("respuesta_1");
                String respuesta2 = rs.getString("respuesta_2");
                String respuesta3 = rs.getString("respuesta_3");
                String respuesta4 = rs.getString("respuesta_4");
                String respuesta5 = rs.getString("respuesta_5");

                Pregunta pregunta = new Pregunta(idPregunta, instrumentoIdDb, textoPregunta, respuesta1, respuesta2, respuesta3, respuesta4, respuesta5);
                preguntas.add(pregunta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return preguntas;
    }
    
    

    public Pregunta encontrar(Pregunta pregunta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, pregunta.getIdPregunta());
            rs = stmt.executeQuery();
            rs.absolute(1); // Nos posicionamos en el primer registro
            int instrumentoId = rs.getInt("Instrumento_id");
            String textoPregunta = rs.getString("pregunta");
            String respuesta1 = rs.getString("respuesta_1");
            String respuesta2 = rs.getString("respuesta_2");
            String respuesta3 = rs.getString("respuesta_3");
            String respuesta4 = rs.getString("respuesta_4");
            String respuesta5 = rs.getString("respuesta_5");

            pregunta.setInstrumentoId(instrumentoId);
            pregunta.setPregunta(textoPregunta);
            pregunta.setRespuesta1(respuesta1);
            pregunta.setRespuesta2(respuesta2);
            pregunta.setRespuesta3(respuesta3);
            pregunta.setRespuesta4(respuesta4);
            pregunta.setRespuesta5(respuesta5);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return pregunta;
    }
    
     public Pregunta encontrarPorId(int idPregunta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pregunta pregunta = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_2);
            stmt.setInt(1, idPregunta);
            rs = stmt.executeQuery();
            if (rs.next()) {
                pregunta = new Pregunta();
                pregunta.setIdPregunta(rs.getInt("id_pregunta"));
                pregunta.setInstrumentoId(rs.getInt("Instrumento_id"));
                pregunta.setPregunta(rs.getString("pregunta"));
                pregunta.setRespuesta1(rs.getString("respuesta_1"));
                pregunta.setRespuesta2(rs.getString("respuesta_2"));
                pregunta.setRespuesta3(rs.getString("respuesta_3"));
                pregunta.setRespuesta4(rs.getString("respuesta_4"));
                pregunta.setRespuesta5(rs.getString("respuesta_5"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return pregunta;
    }

    public int insertar(Pregunta pregunta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, pregunta.getInstrumentoId());
            stmt.setString(2, pregunta.getPregunta());
            stmt.setString(3, pregunta.getRespuesta1());
            stmt.setString(4, pregunta.getRespuesta2());
            stmt.setString(5, pregunta.getRespuesta3());
            stmt.setString(6, pregunta.getRespuesta4());
            stmt.setString(7, pregunta.getRespuesta5());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Pregunta pregunta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, pregunta.getInstrumentoId());
            stmt.setString(2, pregunta.getPregunta());
            stmt.setString(3, pregunta.getRespuesta1());
            stmt.setString(4, pregunta.getRespuesta2());
            stmt.setString(5, pregunta.getRespuesta3());
            stmt.setString(6, pregunta.getRespuesta4());
            stmt.setString(7, pregunta.getRespuesta5());
            stmt.setInt(8, pregunta.getIdPregunta());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Pregunta pregunta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pregunta.getIdPregunta());
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
