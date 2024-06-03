public class Vehiculo {

    private int espacio;
    private String ID;

    public Vehiculo(int espacio, String ID) {
        this.espacio = espacio;
        this.ID = ID;
    }

    // GETTERS
    public int getEspacio() {
        return espacio;
    }

    public String getID() {
        return ID;
    }

    // SETTERS
    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Vehiculo[espacio=" + espacio + ", ID=" + ID + "]";
    }
}
