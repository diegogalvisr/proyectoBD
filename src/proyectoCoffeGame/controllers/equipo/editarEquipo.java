package proyectoCoffeGame.controllers.equipo;

import javax.swing.*;

import proyectoCoffeGame.models.equipoModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class editarEquipo extends JDialog {
    private JTextField idJuegoField;
    private JTextField nombreField;

    public editarEquipo(JFrame parent, int idJuego, String nombreJuego) {
        super(parent, "Editar Juego - COFFE GAMER", true);
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

        JLabel lblIdJuego = new JLabel("ID Equipo:");
        idJuegoField = new JTextField(20);
        idJuegoField.setText(Integer.toString(idJuego));
        idJuegoField.setEnabled(false);
        panel.add(lblIdJuego, gbc);

        gbc.gridy++;
        panel.add(idJuegoField, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        nombreField = new JTextField(20);
        nombreField.setText(nombreJuego);
        gbc.gridy++;
        panel.add(lblNombre, gbc);

        gbc.gridy++;
        panel.add(nombreField, gbc);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBackground(Color.decode("#007bff"));
        btnAceptar.setFocusPainted(false);
        btnAceptar.addActionListener(e -> onAceptar(e));

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

    private void onAceptar(ActionEvent e) {
        int idJuego = Integer.parseInt(idJuegoField.getText());
        String nombreJuego = nombreField.getText();

        if (nombreJuego.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
        } else {
            equipoModel equipoMod = new equipoModel();
            equipoMod.setIdEquipo(idJuego);
            equipoMod.setNombre(nombreJuego);
            equipoController.actualizarEquipo(equipoMod);
            dispose();
        }
    }
}
