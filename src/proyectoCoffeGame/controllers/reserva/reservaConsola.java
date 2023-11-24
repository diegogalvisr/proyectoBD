package proyectoCoffeGame.controllers.reserva;

import javax.swing.*;
import javax.swing.event.DocumentListener;

import javax.swing.event.DocumentEvent;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.DateComponentFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class reservaConsola extends JDialog {
    private JComboBox<String> clientess;
    private JComboBox<String> consolass;
    private JDatePickerImpl datePicker;

    public reservaConsola(JFrame parent) {
        super(parent, "Reservar Consola - COFFE GAMER", true);
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

        JLabel lblCliente = new JLabel("Escoja cliente:");
        clientess = new JComboBox<>();
        gbc.gridy++;
        panel.add(lblCliente, gbc);

        // Agregar un JTextField para filtrar los elementos del JComboBox
        JTextField textField = new JTextField(20);
        gbc.gridy++;
        panel.add(textField, gbc);

        // Lista original de clientes
        List<String> clientesList = reservaController.obtenerClientes();

        // Agregar los clientes al JComboBox
        for (String cliente : clientesList) {
            clientess.addItem(cliente);
        }

        gbc.gridy++;
        panel.add(clientess, gbc);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterItems();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterItems();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterItems();
            }

            private void filterItems() {
                String text = textField.getText().toLowerCase();
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

                for (String cliente : clientesList) {
                    if (cliente.toLowerCase().contains(text)) {
                        model.addElement(cliente);
                    }
                }

                clientess.setModel(model);
                clientess.setPopupVisible(true); // Para mostrar la lista filtrada
            }
        });

        // Componentes relacionados con la opción Consola
        JLabel lblConsolaa = new JLabel("Escoja Consola Disponible:");
        consolass = new JComboBox<>();
        consolass.addItem("Seleccione");
        consolass.setSelectedIndex(0);
        gbc.gridy++;
        gbc.gridx = 0; // Asegurar que estén en la misma columna
        gbc.gridwidth = 2; // Ocupar dos columnas
        panel.add(lblConsolaa, gbc);

        gbc.gridy++;
        panel.add(consolass, gbc);

        // Lista original de clientes
        List<String> consolasList = reservaController.modeloConsola();

        // Agregar los clientes al JComboBox
        for (String cliente : consolasList) {
            consolass.addItem(cliente);
        }

        // Componente para la fecha
        JLabel lblFechaHora = new JLabel("Fecha y Hora:");
        gbc.gridx = 0; // Reiniciar el índice de la columna
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
        String clienteR = (String) clientess.getSelectedItem();
        Date selectedDate = (Date) datePicker.getModel().getValue();
        String consola = (String) consolass.getSelectedItem();
        int idConsola = reservaController.saberIDConsola(consola);
        int idCliente = reservaController.saberIDCliente(clienteR);

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parsedDate = selectedDate;
            String formattedDate = outputFormat.format(parsedDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            calendar.add(Calendar.DAY_OF_YEAR, 2);

            if (clienteR.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campos no llenos");
            } else {
                reservaController.insertarReservaConsola(idCliente, idConsola, formattedDate);
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Precio no válido, ingresa un número válido");
        }
    }

}
