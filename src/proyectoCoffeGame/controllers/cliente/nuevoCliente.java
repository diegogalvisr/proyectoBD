package proyectoCoffeGame.controllers.cliente;

import javax.swing.*;

import proyectoCoffeGame.models.clienteModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class nuevoCliente extends JDialog {
    private JTextField nombreField;
    private JTextField emailField;

    public nuevoCliente(JFrame parent) {
        super(parent, "Ingresar Datos Nuevo Cliente - COFFE GAMER", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un JPanel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre Completo:");
        nombreField = new JTextField(20);

        JLabel lblEmail = new JLabel("Email:");
        emailField = new JTextField(20);

        inputPanel.add(lblNombre);
        inputPanel.add(nombreField);
        inputPanel.add(lblEmail);
        inputPanel.add(emailField);

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

        String patronEmail = ".*@.*";
        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos no llenos");
        } else if (!email.matches(patronEmail)) {
            JOptionPane.showMessageDialog(null,
                    "Email no válido, verifica que tenga el formato correcto (ejemplo@dominio.com)");
        } else {
            clienteModel nuevoCliente = new clienteModel();
            nuevoCliente.setNombre(nombre);
            nuevoCliente.setEmail(email);
            clienteController.insertarCliente(nuevoCliente);
            dispose();
        }

    }
}
