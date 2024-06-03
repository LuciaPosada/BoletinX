import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parking {

    private int tamañoParking;

    public Parking(int tamañoParking) {
        this.tamañoParking = tamañoParking;
    }

    public static boolean introducirParking(String tipo) {
        try (Connection connection = CConexion.conectar()) {
            String sql = "INSERT INTO public.\"vehiculos\" (tipo, ocupado) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tipo);
            statement.setBoolean(2, true);
            statement.executeUpdate();
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    public static boolean sacarParking(int id) {
        try (Connection connection = CConexion.conectar()) {
            String sql = "DELETE FROM public.\"vehiculos\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> comprobarParking() {
        List<String> vehiculos = new ArrayList<>();
        try (Connection connection = CConexion.conectar()) {
            String sql = "SELECT id, tipo FROM public.\"vehiculos\" WHERE ocupado = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String tipo = resultSet.getString("tipo");
                vehiculos.add("ID: " + id + ", Tipo: " + tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
}
