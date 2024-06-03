import java.util.ArrayList;
import java.util.List;

public class Parking {

    private List<Vehiculo> vehiculos;
    private int tamañoParking;
    private int espacioOcupado;

    public Parking(int tamañoParking) {
        this.tamañoParking = tamañoParking;
        this.espacioOcupado = 0;
        this.vehiculos = new ArrayList<>();
    }

    public String introducirParking(Vehiculo v) {
        if (espacioOcupado + v.getEspacio() <= tamañoParking) {
            vehiculos.add(v);
            espacioOcupado += v.getEspacio();
            return "Vehículo añadido exitosamente";
        }
        return "No hay espacio suficiente en el parking";
    }

    public String sacarParking(String ID) {
        for (Vehiculo v : vehiculos) {
            if (v.getID().equals(ID)) {
                vehiculos.remove(v);
                espacioOcupado -= v.getEspacio();
                return "Vehículo eliminado exitosamente";
            }
        }
        return "Vehículo no encontrado";
    }

    public String comprobarParking() {
        StringBuilder estado = new StringBuilder();
        for (Vehiculo v : vehiculos) {
            estado.append(v).append("\n");
        }
        estado.append("Espacio ocupado: ").append(espacioOcupado).append("/").append(tamañoParking);
        return estado.toString();
    }
}
