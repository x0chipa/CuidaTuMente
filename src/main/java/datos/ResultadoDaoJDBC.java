package datos;

import dominio.Resultado;
import java.sql.*;
import java.util.*;

public class ResultadoDaoJDBC {

    private static final String SQL_SELECT = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados";
    private static final String SQL_SELECT_BY_ID = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE id = ?";
    private static final String SQL_SELECT_BY_SESION_ID = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE sesion_id = ?";
    private static final String SQL_SELECT_BY_NIVEL_ESTRES = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE nivel_de_estres = ?";
    private static final String SQL_SELECT_BY_PUNTUACION_ESTRES = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE estres_puntuacion = ?";
    private static final String SQL_SELECT_BY_NIVEL_ANSIEDAD = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE nivel_de_ansiedad = ?";
    private static final String SQL_SELECT_BY_PUNTUACION_ANSIEDAD = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE ansiedad_puntuacion = ?";
    private static final String SQL_SELECT_BY_NIVEL_DEPRESION = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE nivel_de_depresion = ?";
    private static final String SQL_SELECT_BY_PUNTUACION_DEPRESION = "SELECT id, sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion FROM resultados WHERE depresion_puntuacion = ?";
    private static final String SQL_INSERT = "INSERT INTO resultados(sesion_id, nivel_de_estres, estres_puntuacion, nivel_de_ansiedad, ansiedad_puntuacion, nivel_de_depresion, depresion_puntuacion) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE resultados SET sesion_id=?, nivel_de_estres=?, estres_puntuacion=?, nivel_de_ansiedad=?, ansiedad_puntuacion=?, nivel_de_depresion=?, depresion_puntuacion=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM resultados WHERE id = ?";

    public List<Resultado> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Resultado resultado;
        List<Resultado> resultados = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int sesionId = rs.getInt("sesion_id");
                String nivelDeEstres = rs.getString("nivel_de_estres");
                int estresPuntuacion = rs.getInt("estres_puntuacion");
                String nivelDeAnsiedad = rs.getString("nivel_de_ansiedad");
                int ansiedadPuntuacion = rs.getInt("ansiedad_puntuacion");
                String nivelDeDepresion = rs.getString("nivel_de_depresion");
                int depresionPuntuacion = rs.getInt("depresion_puntuacion");

                resultado = new Resultado(id, sesionId, nivelDeEstres, estresPuntuacion, nivelDeAnsiedad, ansiedadPuntuacion, nivelDeDepresion, depresionPuntuacion);
                resultados.add(resultado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultados;
    }
    
    public Resultado encontrar(Resultado resultado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, resultado.getId());
            rs = stmt.executeQuery();
            rs.absolute(1); // Nos posicionamos en el primer registro
            int sesionId = rs.getInt("sesion_id");
            String nivelDeEstres = rs.getString("nivel_de_estres");
            int estresPuntuacion = rs.getInt("estres_puntuacion");
            String nivelDeAnsiedad = rs.getString("nivel_de_ansiedad");
            int ansiedadPuntuacion = rs.getInt("ansiedad_puntuacion");
            String nivelDeDepresion = rs.getString("nivel_de_depresion");
            int depresionPuntuacion = rs.getInt("depresion_puntuacion");

            resultado.setSesionId(sesionId);
            resultado.setNivelDeEstres(nivelDeEstres);
            resultado.setEstresPuntuacion(estresPuntuacion);
            resultado.setNivelDeAnsiedad(nivelDeAnsiedad);
            resultado.setAnsiedadPuntuacion(ansiedadPuntuacion);
            resultado.setNivelDeDepresion(nivelDeDepresion);
            resultado.setDepresionPuntuacion(depresionPuntuacion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }

    public List<Resultado> encontrarPorSesionId(int sesionId) {
        return encontrarPorAtributo(SQL_SELECT_BY_SESION_ID, sesionId);
    }

    public List<Resultado> encontrarPorNivelDeEstres(String nivelDeEstres) {
        return encontrarPorAtributo(SQL_SELECT_BY_NIVEL_ESTRES, nivelDeEstres);
    }

    public List<Resultado> encontrarPorPuntuacionDeEstres(int estresPuntuacion) {
        return encontrarPorAtributo(SQL_SELECT_BY_PUNTUACION_ESTRES, estresPuntuacion);
    }

    public List<Resultado> encontrarPorNivelDeAnsiedad(String nivelDeAnsiedad) {
        return encontrarPorAtributo(SQL_SELECT_BY_NIVEL_ANSIEDAD, nivelDeAnsiedad);
    }

    public List<Resultado> encontrarPorPuntuacionDeAnsiedad(int ansiedadPuntuacion) {
        return encontrarPorAtributo(SQL_SELECT_BY_PUNTUACION_ANSIEDAD, ansiedadPuntuacion);
    }

    public List<Resultado> encontrarPorNivelDeDepresion(String nivelDeDepresion) {
        return encontrarPorAtributo(SQL_SELECT_BY_NIVEL_DEPRESION, nivelDeDepresion);
    }

    public List<Resultado> encontrarPorPuntuacionDeDepresion(int depresionPuntuacion) {
        return encontrarPorAtributo(SQL_SELECT_BY_PUNTUACION_DEPRESION, depresionPuntuacion);
    }

    private List<Resultado> encontrarPorAtributo(String sql, Object atributo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Resultado> resultados = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            if (atributo instanceof String) {
                stmt.setString(1, (String) atributo);
            } else if (atributo instanceof Integer) {
                stmt.setInt(1, (Integer) atributo);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int sesionId = rs.getInt("sesion_id");
                String nivelDeEstres = rs.getString("nivel_de_estres");
                int estresPuntuacion = rs.getInt("estres_puntuacion");
                String nivelDeAnsiedad = rs.getString("nivel_de_ansiedad");
                int ansiedadPuntuacion = rs.getInt("ansiedad_puntuacion");
                String nivelDeDepresion = rs.getString("nivel_de_depresion");
                int depresionPuntuacion = rs.getInt("depresion_puntuacion");

                Resultado resultado = new Resultado(id, sesionId, nivelDeEstres, estresPuntuacion, nivelDeAnsiedad, ansiedadPuntuacion, nivelDeDepresion, depresionPuntuacion);
                resultados.add(resultado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultados;
    }

    public int insertar(Resultado resultado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, resultado.getSesionId());
            stmt.setString(2, resultado.getNivelDeEstres());
            stmt.setInt(3, resultado.getEstresPuntuacion());
            stmt.setString(4, resultado.getNivelDeAnsiedad());
            stmt.setInt(5, resultado.getAnsiedadPuntuacion());
            stmt.setString(6, resultado.getNivelDeDepresion());
            stmt.setInt(7, resultado.getDepresionPuntuacion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Resultado resultado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, resultado.getSesionId());
            stmt.setString(2, resultado.getNivelDeEstres());
            stmt.setInt(3, resultado.getEstresPuntuacion());
            stmt.setString(4, resultado.getNivelDeAnsiedad());
            stmt.setInt(5, resultado.getAnsiedadPuntuacion());
            stmt.setString(6, resultado.getNivelDeDepresion());
            stmt.setInt(7, resultado.getDepresionPuntuacion());
            stmt.setInt(8, resultado.getId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Resultado resultado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, resultado.getId());
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
