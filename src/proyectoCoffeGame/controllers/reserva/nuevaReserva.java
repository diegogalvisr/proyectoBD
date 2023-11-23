package proyectoCoffeGame.controllers.reserva;

import javax.swing.*;

import proyectoCoffeGame.models.computadorModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.DateComponentFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Properties;

public class nuevaReserva extends JDialog {
    private JTextField numSerie;
    private JTextField modelo;
    private JTextField precioH;
    private JDatePickerImpl datePicker;

    public nuevaReserva(JFrame parent) {
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

        // Componente para la fecha
        JLabel lblFechaHora = new JLabel("Fecha y Hora:");
        gbc.gridy++;
        panel.add(lblFechaHora, gbc);

        UtilDateModel dateModel = new UtilDateModel();
        Properties dateProperties = new Properties();
        dateProperties.put("text.today", "Today");
        dateProperties.put("text.month", "Month");
        dateProperties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, dateProperties);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        // Componente JSpinner para la hora
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        // Agregar los componentes al panel
        gbc.gridy++;
        panel.add(datePicker, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Hora: "), gbc);
        gbc.gridy++;
        panel.add(timeSpinner, gbc);

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
                int precioHC = Integer.parseInt(precioHS);

                computadorModel compModel = new computadorModel();
                compModel.setNumSerie(numSerieC);
                compModel.setModelo(modelC);
                compModel.setPrecioHora(precioHC);

                // Supongo que nuevoCliente se inicializa en algún lugar antes de este código
                // computadorController.insertarCompu(compModel);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Precio no válido, ingresa un número válido");
            }
        }
    }

}
