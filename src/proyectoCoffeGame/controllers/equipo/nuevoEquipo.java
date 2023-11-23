package proyectoCoffeGame.controllers.equipo;

import javax.swing.*;

import proyectoCoffeGame.models.equipoModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class nuevoEquipo extends JDialog {

    public nuevoEquipo(JFrame parent) {
        super(parent, "Ingresar Datos Nuevo Equipo - COFFE GAMER", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField nombre = new JTextField(20); // Cambio: Crear una variable local en lugar de usar un campo de clase
        panel.add(lblNombre, gbc);

        gbc.gridy++;
        panel.add(nombre, gbc);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBackground(Color.decode("#007bff"));
        btnAceptar.setFocusPainted(false);
        btnAceptar.addActionListener(e -> onAceptar(e, nombre.getText())); // Pasar el JTextField como argumento al
                                                                           // método onAceptar

        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(Color.decode("#6c757d"));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 0, 0);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(btnAceptar);
        buttonPanel.add(btnCancelar);
        panel.add(buttonPanel, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    private void onAceptar(ActionEvent e, String nombreEquipo) {
        if (nombreEquipo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del equipo no puede estar vacío");
        } else {
            equipoModel nvoEqp = new equipoModel();
            nvoEqp.setNombre(nombreEquipo);
            equipoController.insertarEquipo(nvoEqp);
            dispose();
        }
    }

}
