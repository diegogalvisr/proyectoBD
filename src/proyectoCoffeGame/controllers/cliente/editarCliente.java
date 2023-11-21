package proyectoCoffeGame.controllers.cliente;

import javax.swing.*;

import proyectoCoffeGame.models.clienteModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class editarCliente extends JDialog {
    private JTextField nombreField;
    private JTextField emailField;
    private JTextField idClienteField;

    public editarCliente(JFrame parent, int idCliente, String nombre, String email) {
        super(parent, "Editar Cliente - COFFE GAMER", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un JPanel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel lblidCliente = new JLabel("Identificador:");
        idClienteField = new JTextField(20);
        idClienteField.setText(Integer.toString(idCliente)); // Establecer el nombre recibido en el campo de texto
        idClienteField.setEnabled(false);

        JLabel lblNombre = new JLabel("Nombre Completo:");
        nombreField = new JTextField(20);
        nombreField.setText(nombre); // Establecer el nombre recibido en el campo de texto

        JLabel lblEmail = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setText(email); // Establecer el email recibido en el campo de texto

        inputPanel.add(lblNombre);
        inputPanel.add(nombreField);
        inputPanel.add(lblEmail);
        inputPanel.add(emailField);
        inputPanel.add(lblidCliente);
        inputPanel.add(idClienteField);

        panel.add(inputPanel, BorderLayout.CENTER);

        // Crear botones con estilo similar a Bootstrap
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

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
        // Obtener los valores ingresados en los campos de texto
        String nombre = nombreField.getText();
        String email = emailField.getText();
        int idCliente = Integer.parseInt(idClienteField.getText());

        String patronEmail = ".*@.*";
        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos no llenos");
        } else if (!email.matches(patronEmail)) {
            JOptionPane.showMessageDialog(null,
                    "Email no válido, verifica que tenga el formato correcto (ejemplo@dominio.com)");
        } else {
            clienteModel nuevoCliente = new clienteModel();
            nuevoCliente.setIdCliente(idCliente);
            nuevoCliente.setNombre(nombre);
            nuevoCliente.setEmail(email);
            clienteController.actualizarCliente(nuevoCliente);
            dispose();
        }
    }
}
