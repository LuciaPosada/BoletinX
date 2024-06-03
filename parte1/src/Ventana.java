import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private JTextArea textArea;
    private JTextField textField;
    private JButton button;
    private Parking parking;

    public Ventana() {
        setTitle("Parking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // JTextArea
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        // JScrollPane 
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // JPanel 
        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BorderLayout());

        // JTextField 
        textField = new JTextField();
        panelSur.add(textField, BorderLayout.CENTER);

        // JButton
        button = new JButton("Enviar");
        panelSur.add(button, BorderLayout.EAST);

        add(panelSur, BorderLayout.SOUTH);

        // Instancia de Parking
        parking = new Parking(10);

        // ActionListener para el botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText().trim();
                if (input.isEmpty()) {
                    mostrarAyuda();
                    return;
                }

                String[] parts = input.split(" ");
                String comando = parts[0].toLowerCase();

                String resultado;
                switch (comando) {
                    case "introducir":
                        if (parts.length < 3) {
                            textArea.append("Uso: introducir [tipo] [ID]\n");
                            break;
                        }
                        String tipo = parts[1];
                        String id = parts[2];
                        Vehiculo vehiculo;
                        if (tipo.equalsIgnoreCase("coche")) {
                            vehiculo = new Coche(id);
                        } else if (tipo.equalsIgnoreCase("camion")) {
                            vehiculo = new Camion(id);
                        } else {
                            textArea.append("Tipo de vehículo no válido. Use 'coche' o 'camion'.\n");
                            break;
                        }
                        resultado = parking.introducirParking(vehiculo);
                        textArea.append(resultado + "\n");
                        break;

                    case "sacar":
                        if (parts.length < 2) {
                            textArea.append("Uso: sacar [ID]\n");
                            break;
                        }
                        id = parts[1];
                        resultado = parking.sacarParking(id);
                        textArea.append(resultado + "\n");
                        break;

                    case "comprobar":
                        resultado = parking.comprobarParking();
                        textArea.append(resultado + "\n");
                        break;

                    default:
                        textArea.append("Comando no reconocido. Use 'introducir', 'sacar' o 'comprobar'.\n");
                        break;
                }

                textField.setText("");
            }
        });

        // Mostrar los comandos disponibles al iniciar el programa
        mostrarAyuda();
    }

    private void mostrarAyuda() {
        textArea.setText("");
        textArea.append("Comandos disponibles:\n");
        textArea.append("1. introducir [tipo] [ID] - Añadir un vehículo al parking.\n");
        textArea.append("   Ejemplo: introducir coche ABC123\n\n");
        textArea.append("2. sacar [ID] - Eliminar un vehículo del parking.\n");
        textArea.append("   Ejemplo: sacar ABC123\n\n");
        textArea.append("3. comprobar - Ver el estado actual del parking.\n");
        textArea.append("   Ejemplo: comprobar\n\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
}
