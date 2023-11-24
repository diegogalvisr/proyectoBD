package proyectoCoffeGame.controllers.torneo;

import javax.swing.*;
import javax.swing.event.DocumentListener;

import javax.swing.event.DocumentEvent;
import proyectoCoffeGame.models.torneoModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ganadorTorneo extends JDialog {
    private JComboBox<String> clientess;
    private int idTorneoG;

    public ganadorTorneo(JFrame parent, int idTorneo) {
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
        idTorneoG = idTorneo;

        JLabel lblCliente = new JLabel("Escoja el equipo:");
        clientess = new JComboBox<>();
        gbc.gridy++;
        panel.add(lblCliente, gbc);

        // Agregar un JTextField para filtrar los elementos del JComboBox
        JTextField textField = new JTextField(20);
        gbc.gridy++;
        panel.add(textField, gbc);

        // Lista original de clientes
        List<String> clientesList = torneoController.obtenerEquipo();

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
        String juegos = (String) clientess.getSelectedItem();

        int idJuego = torneoController.saberIDEquipo(juegos);
        // int idCliente = reservaController.saberIDCliente(clienteR);
        try {

            if (clientess.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Campos no llenos");
            } else {
                torneoModel modelTorneo = new torneoModel();
                modelTorneo.setIdEquipoGanador(idJuego);
                modelTorneo.setIdTorneo(idTorneoG);
                torneoController.actualizarTorneo(modelTorneo);
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Precio no válido, ingresa un número válido");
        }
    }

}
