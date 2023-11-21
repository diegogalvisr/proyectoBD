package proyectoCoffeGame.controllers.computador;

import javax.swing.*;

import proyectoCoffeGame.models.computadorModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class editarComputador extends JDialog {
    private JTextField numSerie;
    private JTextField modelo;
    private JTextField precioH;
    private JTextField idCompu;

    public editarComputador(JFrame parent, int idCompuC, String numSerieC, String modeloC, float precioHC) {
        super(parent, "Editar Cliente - COFFE GAMER", true);
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

        JLabel lblIdCompu = new JLabel("ID Computador:");
        idCompu = new JTextField(20);
       idCompu.setText(Integer.toString(idCompuC));
       idCompu.setEnabled(false);
        panel.add(lblIdCompu, gbc);

        gbc.gridy++;
        panel.add(idCompu, gbc);

        JLabel lblnumSerie = new JLabel("Numero Serie:");
        numSerie = new JTextField(20);
        numSerie.setText(numSerieC);
        gbc.gridy++;
        panel.add(lblnumSerie, gbc);

        gbc.gridy++;
        panel.add(numSerie, gbc);

        JLabel lblmodelo = new JLabel("Modelo:");
        modelo = new JTextField(20);
        modelo.setText(modeloC);
        gbc.gridy++;
        panel.add(lblmodelo, gbc);

        gbc.gridy++;
        panel.add(modelo, gbc);

        JLabel lblprecioH = new JLabel("Precio Hora: ");
        precioH = new JTextField(20);
       precioH.setText(Float.toString(precioHC));
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
        int idCompuc=Integer.parseInt(idCompu.getText());
        String numSeriec=numSerie.getText();
        String modeloC=modelo.getText();
        String precioHorC=precioH.getText();
       

        String patronNumerico = "\\d+\\.?\\d*"; // Patrón para validar números

        if (numSeriec.isEmpty() || modeloC.isEmpty() || precioH.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Campos no llenos");
        } else if (!precioHorC.matches(patronNumerico)) {
            JOptionPane.showMessageDialog(null, "Precio no válido, ingresa un número válido");
        } else {
            computadorModel editarComputador=new computadorModel();
            editarComputador.setIdCompu(idCompuc);
            editarComputador.setNumSerie(numSeriec);
            editarComputador.setModelo(modeloC);
            editarComputador.setPrecioHora(Integer.parseInt(precioHorC));
            editarComputador.setEstado("ACTIVO");
            computadorController.actualizarCompu(editarComputador);
            dispose();
        }
    }
}
