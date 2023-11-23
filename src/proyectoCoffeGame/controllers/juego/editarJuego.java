package proyectoCoffeGame.controllers.juego;

import javax.swing.*;
import proyectoCoffeGame.models.juegoModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class editarJuego extends JDialog {
    private JTextField idJuegoField;
    private JTextField nombreField;
    private JTextField generoField;
    private JTextField plataformaField;
    private JTextField clasificacionField;

    public editarJuego(JFrame parent, int idJuego, String nombreJuego, String generoJuego, String plataformaJuego,
            String clasificacionJuego) {
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

        JLabel lblIdJuego = new JLabel("ID Juego:");
        idJuegoField = new JTextField(20);
        idJuegoField.setText(Integer.toString(idJuego));
        idJuegoField.setEnabled(false);
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

        JLabel lblGenero = new JLabel("Género:");
        generoField = new JTextField(20);
        generoField.setText(generoJuego);
        gbc.gridy++;
        panel.add(lblGenero, gbc);

        gbc.gridy++;
        panel.add(generoField, gbc);

        JLabel lblPlataforma = new JLabel("Plataforma:");
        plataformaField = new JTextField(20);
        plataformaField.setText(plataformaJuego);
        gbc.gridy++;
        panel.add(lblPlataforma, gbc);

        gbc.gridy++;
        panel.add(plataformaField, gbc);

        JLabel lblClasificacion = new JLabel("Clasificación:");
        clasificacionField = new JTextField(20);
        clasificacionField.setText(clasificacionJuego);
        gbc.gridy++;
        panel.add(lblClasificacion, gbc);

        gbc.gridy++;
        panel.add(clasificacionField, gbc);

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
        String generoJuego = generoField.getText();
        String plataformaJuego = plataformaField.getText();
        String clasificacionJuego = clasificacionField.getText();

        if (nombreJuego.isEmpty() || generoJuego.isEmpty() || plataformaJuego.isEmpty()
                || clasificacionJuego.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
        } else {
            juegoModel juegoMod = new juegoModel();
            juegoMod.setIdJuego(idJuego);
            juegoMod.setNombre(nombreJuego);
            juegoMod.setGenero(generoJuego);
            juegoMod.setPlataforma(plataformaJuego);
            juegoMod.setClasificacion(clasificacionJuego);
            juegoController.actualizarJuego(juegoMod);
            dispose();
        }
    }
}
