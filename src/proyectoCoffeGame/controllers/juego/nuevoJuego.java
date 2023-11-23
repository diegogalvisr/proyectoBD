package proyectoCoffeGame.controllers.juego;

import javax.swing.*;

import proyectoCoffeGame.models.juegoModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class nuevoJuego extends JDialog {
    private JTextField nombre;
    private JTextField genero;
    private JTextField plataforma;
    private JTextField clasificacion;

    public nuevoJuego(JFrame parent) {
        super(parent, "Ingresar Datos Nuevo Juego - COFFE GAMER", true);
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
        nombre = new JTextField(20);
        panel.add(lblNombre, gbc);

        gbc.gridy++;
        panel.add(nombre, gbc);

        JLabel lblGenero = new JLabel("Género:");
        genero = new JTextField(20);
        gbc.gridy++;
        panel.add(lblGenero, gbc);

        gbc.gridy++;
        panel.add(genero, gbc);

        JLabel lblPlataforma = new JLabel("Plataforma:");
        plataforma = new JTextField(20);
        gbc.gridy++;
        panel.add(lblPlataforma, gbc);

        gbc.gridy++;
        panel.add(plataforma, gbc);

        JLabel lblClasificacion = new JLabel("Clasificación:");
        clasificacion = new JTextField(20);
        gbc.gridy++;
        panel.add(lblClasificacion, gbc);

        gbc.gridy++;
        panel.add(clasificacion, gbc);

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
        String nombreJuego = nombre.getText();
        String generoJuego = genero.getText();
        String plataformaJuego = plataforma.getText();
        String clasificacionJuego = clasificacion.getText();

        if (nombreJuego.isEmpty() || generoJuego.isEmpty() || plataformaJuego.isEmpty()
                || clasificacionJuego.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
        } else {
            juegoModel juegoMo = new juegoModel();
            juegoMo.setNombre(nombreJuego);
            juegoMo.setGenero(generoJuego);
            juegoMo.setPlataforma(plataformaJuego);
            juegoMo.setClasificacion(clasificacionJuego);
            juegoController.insertarJuego(juegoMo);
            dispose();
        }
    }
}
