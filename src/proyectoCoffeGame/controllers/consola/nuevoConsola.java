package proyectoCoffeGame.controllers.consola;

import javax.swing.*;

import proyectoCoffeGame.models.computadorModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class nuevoConsola extends JDialog {
    private JTextField numSerie;
    private JTextField modelo;
    private JTextField precioH;

    public nuevoConsola(JFrame parent) {
        super(parent, "Ingresar Datos Nuevo Computador - COFFE GAMER", true);
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

        JLabel lblnumSerie = new JLabel("Numero Serie:");
        numSerie = new JTextField(20);
        panel.add(lblnumSerie, gbc);

        gbc.gridy++;
        panel.add(numSerie, gbc);

        JLabel lblmodelo = new JLabel("Modelo:");
        modelo = new JTextField(20);
        gbc.gridy++;
        panel.add(lblmodelo, gbc);

        gbc.gridy++;
        panel.add(modelo, gbc);

        JLabel lblprecioH = new JLabel("Precio Hora: ");
        precioH = new JTextField(20);
        gbc.gridy++;
        panel.add(lblprecioH, gbc);

        gbc.gridy++;
        panel.add(precioH, gbc);

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
        // Obtener los valores ingresados en los campos de texto
        String numSerieC = numSerie.getText();
        String modelC = modelo.getText();
        String precioHS = precioH.getText();
    
        String patronNumerico = "\\d+\\.?\\d*"; // Patrón para validar números
    
        if (numSerieC.isEmpty() || modelC.isEmpty() || precioHS.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos no llenos");
        } else if (!precioHS.matches(patronNumerico)) {
            JOptionPane.showMessageDialog(null, "Precio no válido, ingresa un número válido");
        } else {
            try {
                float precioHC = Float.parseFloat(precioHS);
                
                computadorModel compModel = new computadorModel();
                compModel.setNumSerie(numSerieC);
                compModel.setModelo(modelC);
                compModel.setPrecioHora(precioHC);
    
                // Supongo que nuevoCliente se inicializa en algún lugar antes de este código
                consolaController.insertarCompu(compModel);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Precio no válido, ingresa un número válido");
            }
        }
    }
    
    
}
