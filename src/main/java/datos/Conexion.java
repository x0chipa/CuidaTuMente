package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author david
 */
public class Conexion {

    // private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testtesis?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    //private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testtesis?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testtesis?useSSL=false&useTimezone=true&serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "";

   private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USER);
        dataSource.setPassword(JDBC_PASSWORD);
        dataSource.setInitialSize(10);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(50);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
