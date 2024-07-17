package datos;

import dominio.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_usuario, user, password FROM usuario";
    private static final String SQL_SELECT_BY_ID = "SELECT id_usuario, user, password FROM usuario WHERE id_usuario = ?";
    private static final String SQL_INSERT = "INSERT INTO usuario(user, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET user=?, password=? WHERE id_usuario=?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    public List<Usuario> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String user = rs.getString("user");
                String password = rs.getString("password");

                usuario = new Usuario(idUsuario, user, password);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }

    public Usuario encontrar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, usuario.getIdUsuario());
            rs = stmt.executeQuery();
            rs.absolute(1); // Nos posicionamos en el primer registro
            String user = rs.getString("user");
            String password = rs.getString("password");

            usuario.setUser(user);
            usuario.setPassword(password);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }

    public int insertar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUser());
            stmt.setString(2, usuario.getPassword());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getUser());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
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
