import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CConexion {

    private static final String URL = "jdbc:postgresql://localhost:5432/ParkingDB";

    private static final String USER = "postgres";

    private static final String PASSWORD = "postgres";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void desconectar(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}