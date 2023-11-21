package proyectoCoffeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioBootstrap extends JDialog {
    private JTextField nombreField;
    private JTextField emailField;

    public FormularioBootstrap(JFrame parent) {
        super(parent, "Ingresar Datos", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un JPanel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JLabel lblEmail = new JLabel("Email:");
        emailField = new JTextField(20);

        inputPanel.add(lblNombre);
        inputPanel.add(nombreField);
        inputPanel.add(lblEmail);
        inputPanel.add(emailField);

        panel.add(inputPanel, BorderLayout.CENTER);

        // Crear botones
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        // Establecer estilos similares a Bootstrap para los botones
        btnAceptar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBackground(Color.decode("#007bff"));
        btnAceptar.setFocusPainted(false);
        btnAceptar.addActionListener(this::onAceptar);

        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(Color.decode("#6c757d"));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());

        // Crear un JPanel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnAceptar);
        buttonPanel.add(btnCancelar);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    private void onAceptar(ActionEvent e) {
        // Aquí puedes obtener los valores ingresados en los campos de texto
        String nombre = nombreField.getText();
        String email = emailField.getText();

        // Hacer algo con los datos ingresados (por ejemplo, mostrarlos en consola)
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);

        // Cerrar el diálogo después de procesar los datos
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            JButton btnMostrarDialogo = new JButton("Mostrar diálogo");
            btnMostrarDialogo.addActionListener(e -> {
                FormularioBootstrap dialog = new FormularioBootstrap(frame);
                dialog.setVisible(true);
            });

            JPanel panel = new JPanel();
            panel.add(btnMostrarDialogo);

            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
