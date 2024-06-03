import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana extends JFrame {
    private JTextArea textArea;
    private JTextField textField;
    private JButton button;

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

                switch (comando) {
                    case "introducir":
                        if (parts.length < 2) {
                            textArea.append("Uso: introducir [tipo]\n");
                            break;
                        }
                        String tipo = parts[1];
                        boolean exito = Parking.introducirParking(tipo);
                        if (exito) {
                            textArea.append("Vehículo añadido exitosamente.\n");
                        } else {
                            textArea.append("Error al añadir el vehículo.\n");
                        }
                        break;

                    case "sacar":
                        if (parts.length < 2) {
                            textArea.append("Uso: sacar [ID]\n");
                            break;
                        }
                        int id = Integer.parseInt(parts[1]);
                        boolean eliminado = Parking.sacarParking(id);
                        if (eliminado) {
                            textArea.append("Vehículo eliminado exitosamente.\n");
                        } else {
                            textArea.append("Error al eliminar el vehículo.\n");
                        }
                        break;

                    case "comprobar":
                        List<String> estado = Parking.comprobarParking();
                        textArea.append("Estado actual del parking:\n");
                        for (String vehiculo : estado) {
                            textArea.append(vehiculo + "\n");
                        }
                        break;

                    default:
                        textArea.append("Comando no reconocido. Use 'introducir', 'sacar' o 'comprobar'.\n");
                        break;
                }

                textField.setText("");
            }
        });

        mostrarAyuda();
    }

    private void mostrarAyuda() {
        textArea.setText("");
        textArea.append("Comandos disponibles:\n");
        textArea.append("1. introducir [tipo] - Añadir un vehículo al parking.\n");
        textArea.append("   Ejemplo: introducir coche\n\n");
        textArea.append("2. sacar [ID] - Eliminar un vehículo del parking.\n");
        textArea.append("   Ejemplo: sacar 1\n\n");
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
