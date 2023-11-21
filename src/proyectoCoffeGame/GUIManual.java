package proyectoCoffeGame;

import static proyectoCoffeGame.config.Basededatos.conectar;
import static proyectoCoffeGame.config.Basededatos.hayConexion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import proyectoCoffeGame.controllers.cliente.clienteController;
import proyectoCoffeGame.controllers.cliente.editarCliente;
import proyectoCoffeGame.controllers.cliente.nuevoCliente;
import proyectoCoffeGame.controllers.computador.computadorController;
import proyectoCoffeGame.controllers.computador.editarComputador;
import proyectoCoffeGame.controllers.computador.nuevoComputador;
import proyectoCoffeGame.controllers.consola.consolaController;
import proyectoCoffeGame.controllers.consola.editarConsola;
import proyectoCoffeGame.controllers.consola.nuevoConsola;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

/**
 *
 * @author JEISON LEAL, GIEGO GALVIS Y WILMER SANCHEZ
 */
public class GUIManual extends JFrame {

    private JPanel jPanelLeft;
    clienteController clienteCon = new clienteController();
    computadorController compuCon = new computadorController();
    consolaController consoCon=new consolaController();

    private JPanel jPanelIconLogo;
    private JLabel iconLogo;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuClientes;
    private JLabel btnClientes;

    private JPanel jPanelMenuInscripcion;
    private JLabel btnInscripcion;

    private JPanel jPanelMenuEquipo;
    private JLabel btnEquipo;

    private JPanel jPanelMenuTorneo;
    private JLabel btnTorneo;

    private JPanel jPanelMenuReservas;
    private JLabel btnReservas;

    private JPanel jPanelMenuJuegos;
    private JLabel btnJuegos;

    private JPanel jPanelMenuConsola;
    private JLabel btnConsola;

    private JPanel jPanelMenuComputador;
    private JLabel btnComputador;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por
        // defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        conectar();

        if (hayConexion()) {

            // Inicializamos componentes del Menu Lateral
            jPanelLeft = new JPanel();

            jPanelIconLogo = new JPanel();
            iconLogo = new JLabel();

            jPanelMenu = new JPanel();

            jPanelMenuHome = new JPanel();
            btnHome = new JLabel();

            jPanelMenuClientes = new JPanel();
            btnClientes = new JLabel();

            jPanelMenuReservas = new JPanel();
            btnReservas = new JLabel();

            jPanelMenuInscripcion = new JPanel();
            btnInscripcion = new JLabel();

            jPanelMenuEquipo = new JPanel();
            btnEquipo = new JLabel();

            jPanelMenuTorneo = new JPanel();
            btnTorneo = new JLabel();

            jPanelMenuJuegos = new JPanel();
            btnJuegos = new JLabel();

            jPanelMenuConsola = new JPanel();
            btnConsola = new JLabel();

            jPanelMenuComputador = new JPanel();
            btnComputador = new JLabel();

            // Pinta el logo de la aplicación
            pintarLogo();

            // Pinta la opción de menú del Home
            pintarMenuHome();

            // Pinta la opción de Menú de los Clientes
            pintarMenuClientes();

            // Pinta la opción de Menú de las Reservas
            pintarMenuReservas();

            // Pinta la opción de Menú de las Inscripciones
            pintarMenuInscripcion();

            // Pinta la opción de Menú de los Equipos
            pintarMenuEquipo();

            // Pinta la opción de Menú de los Torneos
            pintarMenuTorneo();

            // Pinta la opción de Menú de los Juegos
            pintarMenuJuegos();

            // Pinta la opción de Menú de las Consolas
            pintarMenuConsola();

            // Pinta la opción de Menú de los Computadores
            pintarMenuComputador();

            // Pinta y ajuste diseño del contenedor del panel izquierdo
            pintarPanelIzquierdo();

            // Inicializa los componentes del panel derecho de los contenidos
            jPanelRight = new JPanel();
            jPanelLabelTop = new JPanel();
            jPanelMain = new JPanel();

            // Pinta la barra superrior de color azul claro, del panel de contenido
            pintarLabelTop();

            // Pinta y ajusta diseño del contenedor de contenidos
            pintarPanelDerecho();

            setTitle("Coffe AND Game");
            pack();
            setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "No tengo conexion con la base de datos, lo siento.");
        }

    }

    private void pintarLogo() {
        jPanelIconLogo.add(iconLogo);
        jPanelIconLogo.setOpaque(false);
        jPanelIconLogo.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconLogo.setMaximumSize(jPanelIconLogo.getPreferredSize());
        iconLogo.setIcon(new ImageIcon(getClass().getResource("/resources/logo.jpeg")));
        jPanelLeft.add(jPanelIconLogo, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {

        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png")));
        btnHome.setText("Portada");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnHome.setFont(font);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Portada");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Portada");
    
        // Creamos los JPanel para cada card
        JPanel cardClientes = new JPanel();
        JPanel cardVentas = new JPanel();
        JPanel cardComputadoras = new JPanel();
        JPanel cardConsolas = new JPanel();
    
        // Establecemos el diseño de los paneles como GridLayout para alinear los elementos
        cardClientes.setLayout(new GridLayout(1, 1));
        cardVentas.setLayout(new GridLayout(1, 1));
        cardComputadoras.setLayout(new GridLayout(1, 1));
        cardConsolas.setLayout(new GridLayout(1, 1));
    
        // Creamos etiquetas para mostrar los totales
        JLabel labelClientes = new JLabel("Total de Clientes Activos: 50"); // Reemplaza el número con el total real
        JLabel labelVentas = new JLabel("Total de Ventas: $10000"); // Reemplaza el número con el total real
        JLabel labelComputadoras = new JLabel("Total de Computadoras: 30"); // Reemplaza el número con el total real
        JLabel labelConsolas = new JLabel("Total de Consolas: 20"); // Reemplaza el número con el total real
    
        // Agregamos las etiquetas a los paneles de las cards
        cardClientes.add(labelClientes);
        cardVentas.add(labelVentas);
        cardComputadoras.add(labelComputadoras);
        cardConsolas.add(labelConsolas);
    
        // Aplicamos estilos de Bootstrap a los JPanel
        cardClientes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cardClientes.setBackground(Color.WHITE);
        cardClientes.setPreferredSize(new Dimension(200, 100));
    
        cardVentas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cardVentas.setBackground(Color.WHITE);
        cardVentas.setPreferredSize(new Dimension(200, 100));
    
        cardComputadoras.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cardComputadoras.setBackground(Color.WHITE);
        cardComputadoras.setPreferredSize(new Dimension(200, 100));
    
        cardConsolas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cardConsolas.setBackground(Color.WHITE);
        cardConsolas.setPreferredSize(new Dimension(200, 100));
    
        // Agregamos los JPanel al jPanelMain
        jPanelMain.removeAll();
        jPanelMain.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Ajusta la disposición según necesites
        jPanelMain.add(cardClientes);
        jPanelMain.add(cardVentas);
        jPanelMain.add(cardComputadoras);
        jPanelMain.add(cardConsolas);
    
        // Repintamos y revalidamos el jPanelMain
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */

    private void pintarMenuClientes() {
        btnClientes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnClientes.setText("Clientes");
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuClientes.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuClientes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuClientes.setLayout(new BorderLayout(15, 0));
        jPanelMenuClientes.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuClientes.add(btnClientes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuClientes);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnClientes.setFont(font);

        btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Clientes");
                accionClientes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionClientes() {
        jLabelTop.setText("Clientessss");
        // Creo los botones
        JButton nuevoButton = new JButton("Nuevo");
        JButton editarButton = new JButton("Editar");
        JButton eliminarButton = new JButton("Eliminar");

        // Doy estilos a los botones
        Font btnFont = new Font("Arial", Font.BOLD, 12);
        Color btnTextColor = Color.WHITE;
        Color btnBackgroundColor = Color.decode("#007bff");
        Color btnHoverColor = Color.decode("#0056b3");

        editarButton.setFont(btnFont);
        editarButton.setForeground(btnTextColor);
        editarButton.setBackground(btnBackgroundColor);
        editarButton.setFocusPainted(false);
        editarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        eliminarButton.setFont(btnFont);
        eliminarButton.setForeground(btnTextColor);
        eliminarButton.setBackground(btnBackgroundColor);
        eliminarButton.setFocusPainted(false);
        eliminarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        nuevoButton.setFont(btnFont);
        nuevoButton.setForeground(btnTextColor);
        nuevoButton.setBackground(btnBackgroundColor);
        nuevoButton.setFocusPainted(false);
        nuevoButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout()); // Alinea los botones de manera predeterminada
        buttonsPanel.add(nuevoButton);
        buttonsPanel.add(editarButton);
        buttonsPanel.add(eliminarButton);

        // Crear un JPanel para separar los botones y la tabla
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(1, 10)); // Espacio de 1 renglón (10 píxeles aproximadamente)

        JTable table = new JTable(clienteCon.obtenerTablaClientes());

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(Color.decode("#f3eb55"));
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setBackground(Color.decode("#007bff"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(table);

        nuevoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nuevoButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                nuevoButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(MouseEvent evt) {
                nuevoCliente nvoCL = new nuevoCliente(null);
                nvoCL.setVisible(true);
                table.setModel(clienteCon.obtenerTablaClientes());

            }
        });

        eliminarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
                    Object valorColumna1 = table.getValueAt(filaSeleccionada, 0); // Obtener el valor de la columna 1

                    // Realizar alguna acción con los valores obtenidos
                    int idCliente = (int) valorColumna1;

                    int confirmacion = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar al cliente: " + idCliente + "?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {

                        clienteController.eliminarCliente(idCliente);
                        table.setModel(clienteCon.obtenerTablaClientes()); // Actualiza la tabla con los nuevos datos
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente.");
                }

            }
        });

        editarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editarButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editarButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
                    Object valorColumna1 = table.getValueAt(filaSeleccionada, 1); // Obtener el valor de la columna 1
                    Object valorColumna2 = table.getValueAt(filaSeleccionada, 2); // Obtener el valor de la columna 2
                    Object valorColumna3 = table.getValueAt(filaSeleccionada, 0); // Obtener el valor de la columna 1

                    // Realizar alguna acción con los valores obtenidos

                    String nombre = (String) valorColumna1;
                    String email = (String) valorColumna2;
                    int idCliente = (int) valorColumna3;

                    JFrame frame = new JFrame();
                    editarCliente editarCliente = new editarCliente(frame, idCliente, nombre, email);
                    editarCliente.setVisible(true);
                    table.setModel(clienteCon.obtenerTablaClientes());

                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente.");
                }

            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Agregar un margen exterior

        // Agregar botones y espacio a la disposición
        panel.add(buttonsPanel, BorderLayout.NORTH);
        panel.add(spacePanel, BorderLayout.CENTER); // Espacio entre los botones y la tabla
        panel.add(scrollPane, BorderLayout.CENTER); // Cambiar a BorderLayout.CENTER para la tabla

        // Limpiar y actualizar el JPanel principal para mostrar la tabla
        jPanelMain.removeAll();
        jPanelMain.setLayout(new BorderLayout());
        jPanelMain.add(panel, BorderLayout.CENTER); // Agregar al centro para evitar la superposición
        jPanelMain.revalidate();
        jPanelMain.repaint();
    }

    private void pintarMenuReservas() {
        btnReservas.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnReservas.setText("Reservas");
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuReservas.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuReservas.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuReservas.setLayout(new BorderLayout(15, 0));
        jPanelMenuReservas.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuReservas.add(btnReservas, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuReservas);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnReservas.setFont(font);

        btnReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Reservas");
                accionReservas();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionReservas() {

    }

    private void pintarMenuInscripcion() {
        btnInscripcion.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnInscripcion.setText("Inscripciones");
        btnInscripcion.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuInscripcion.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuInscripcion.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuInscripcion.setLayout(new BorderLayout(15, 0));
        jPanelMenuInscripcion.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuInscripcion.add(btnInscripcion, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuInscripcion);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnInscripcion.setFont(font);

        btnInscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Inscripciones");
                accionInscripcion();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionInscripcion() {

        jLabelTop.setText("Inscripciones");

        jPanelMain.removeAll();
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void pintarMenuEquipo() {
        btnEquipo.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnEquipo.setText("Equipos");
        btnEquipo.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuEquipo.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuEquipo.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuEquipo.setLayout(new BorderLayout(15, 0));
        jPanelMenuEquipo.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuEquipo.add(btnEquipo, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuEquipo);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnEquipo.setFont(font);

        btnEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Equipos");
                accionEquipo();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionEquipo() {

        jLabelTop.setText("Equipos");

        jPanelMain.removeAll();
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void pintarMenuTorneo() {
        btnTorneo.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnTorneo.setText("Torneos");
        btnTorneo.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuTorneo.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuTorneo.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuTorneo.setLayout(new BorderLayout(15, 0));
        jPanelMenuTorneo.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuTorneo.add(btnTorneo, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuTorneo);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnTorneo.setFont(font);

        btnTorneo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Torneos");
                accionTorneo();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionTorneo() {

        jLabelTop.setText("Torneos");

        jPanelMain.removeAll();
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void pintarMenuJuegos() {
        btnJuegos.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnJuegos.setText("Juegos");
        btnJuegos.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuJuegos.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuJuegos.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuJuegos.setLayout(new BorderLayout(15, 0));
        jPanelMenuJuegos.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuJuegos.add(btnJuegos, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuJuegos);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnJuegos.setFont(font);

        btnJuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Juegos");
                accionJuegos();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionJuegos() {

        jLabelTop.setText("Juegos");

        jPanelMain.removeAll();
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void pintarMenuConsola() {
        btnConsola.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnConsola.setText("Consolas");
        btnConsola.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuConsola.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuConsola.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuConsola.setLayout(new BorderLayout(15, 0));
        jPanelMenuConsola.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuConsola.add(btnConsola, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuConsola);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnConsola.setFont(font);

        btnConsola.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Consolas");
                accionConsolas();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionConsolas() {

      
        jLabelTop.setText("Consolas");
        // Creo los botones
        JButton nuevoButton = new JButton("Nuevo");
        JButton editarButton = new JButton("Editar");
        JButton eliminarButton = new JButton("Eliminar");

        // Doy estilos a los botones
        Font btnFont = new Font("Arial", Font.BOLD, 12);
        Color btnTextColor = Color.WHITE;
        Color btnBackgroundColor = Color.decode("#007bff");
        Color btnHoverColor = Color.decode("#0056b3");

        editarButton.setFont(btnFont);
        editarButton.setForeground(btnTextColor);
        editarButton.setBackground(btnBackgroundColor);
        editarButton.setFocusPainted(false);
        editarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        eliminarButton.setFont(btnFont);
        eliminarButton.setForeground(btnTextColor);
        eliminarButton.setBackground(btnBackgroundColor);
        eliminarButton.setFocusPainted(false);
        eliminarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        nuevoButton.setFont(btnFont);
        nuevoButton.setForeground(btnTextColor);
        nuevoButton.setBackground(btnBackgroundColor);
        nuevoButton.setFocusPainted(false);
        nuevoButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout()); // Alinea los botones de manera predeterminada
        buttonsPanel.add(nuevoButton);
        buttonsPanel.add(editarButton);
        buttonsPanel.add(eliminarButton);

        // Crear un JPanel para separar los botones y la tabla
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(1, 10)); // Espacio de 1 renglón (10 píxeles aproximadamente)

        JTable table = new JTable(consoCon.obtenerTablaConsolas());

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(Color.decode("#f3eb55"));
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setBackground(Color.decode("#007bff"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(table);

        nuevoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nuevoButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                nuevoButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(MouseEvent evt) {
                nuevoConsola nvoCon=new nuevoConsola(null);
                nvoCon.setVisible(true);
                table.setModel(consoCon.obtenerTablaConsolas());

            }
        });

        eliminarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
                    Object valorColumna1 = table.getValueAt(filaSeleccionada, 0); // Obtener el valor de la columna 1

                    // Realizar alguna acción con los valores obtenidos
                    int idCompu = (int) valorColumna1;

                    int confirmacion = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar al cliente: " + idCompu + "?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        consolaController.eliminarConso(idCompu);
                        table.setModel(consoCon.obtenerTablaConsolas()); // Actualiza la tabla con los nuevos datos
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente.");
                }

            }
        });

        editarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editarButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editarButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
                    Object valorColumna1 = table.getValueAt(filaSeleccionada, 0); // Obtener el valor de la columna 1
                    Object valorColumna2 = table.getValueAt(filaSeleccionada, 1); // Obtener el valor de la columna 2
                    Object valorColumna3 = table.getValueAt(filaSeleccionada, 2); // Obtener el valor de la columna 1
                    Object valorColumna5 = table.getValueAt(filaSeleccionada, 4); // Obtener el valor de la columna 1

                    // Realizar alguna acción con los valores obtenidos
                    int idComputador = (int) valorColumna1;
                    String numSerie = (String) valorColumna2;
                    String modelo = (String) valorColumna3;
                    float precioHh = (float) valorColumna5;

                    JFrame frame = new JFrame();
                    editarConsola edtCompu = new editarConsola(frame, idComputador, numSerie, modelo, precioHh);
                    edtCompu.setVisible(true);
                    table.setModel(consoCon.obtenerTablaConsolas());

                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente.");
                }

            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Agregar un margen exterior

        // Agregar botones y espacio a la disposición
        panel.add(buttonsPanel, BorderLayout.NORTH);
        panel.add(spacePanel, BorderLayout.CENTER); // Espacio entre los botones y la tabla
        panel.add(scrollPane, BorderLayout.CENTER); // Cambiar a BorderLayout.CENTER para la tabla

        // Limpiar y actualizar el JPanel principal para mostrar la tabla
        jPanelMain.removeAll();
        jPanelMain.setLayout(new BorderLayout());
        jPanelMain.add(panel, BorderLayout.CENTER); // Agregar al centro para evitar la superposición
        jPanelMain.revalidate();
        jPanelMain.repaint();

    }

    private void pintarMenuComputador() {
        btnComputador.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnComputador.setText("Computadores");
        btnComputador.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuComputador.setBackground(new java.awt.Color(51, 0, 153));
        jPanelMenuComputador.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuComputador.setLayout(new BorderLayout(15, 0));
        jPanelMenuComputador.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuComputador.add(btnComputador, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuComputador);

        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 14); // Ajusta el tamaño según tus preferencias
        btnComputador.setFont(font);

        btnComputador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Computadores");
                accionComputadores();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionComputadores() {

        jLabelTop.setText("Computadores");
        // Creo los botones
        JButton nuevoButton = new JButton("Nuevo");
        JButton editarButton = new JButton("Editar");
        JButton eliminarButton = new JButton("Eliminar");

        // Doy estilos a los botones
        Font btnFont = new Font("Arial", Font.BOLD, 12);
        Color btnTextColor = Color.WHITE;
        Color btnBackgroundColor = Color.decode("#007bff");
        Color btnHoverColor = Color.decode("#0056b3");

        editarButton.setFont(btnFont);
        editarButton.setForeground(btnTextColor);
        editarButton.setBackground(btnBackgroundColor);
        editarButton.setFocusPainted(false);
        editarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        eliminarButton.setFont(btnFont);
        eliminarButton.setForeground(btnTextColor);
        eliminarButton.setBackground(btnBackgroundColor);
        eliminarButton.setFocusPainted(false);
        eliminarButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        nuevoButton.setFont(btnFont);
        nuevoButton.setForeground(btnTextColor);
        nuevoButton.setBackground(btnBackgroundColor);
        nuevoButton.setFocusPainted(false);
        nuevoButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout()); // Alinea los botones de manera predeterminada
        buttonsPanel.add(nuevoButton);
        buttonsPanel.add(editarButton);
        buttonsPanel.add(eliminarButton);

        // Crear un JPanel para separar los botones y la tabla
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(1, 10)); // Espacio de 1 renglón (10 píxeles aproximadamente)

        JTable table = new JTable(compuCon.obtenerTablaComputadores());

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(Color.decode("#f3eb55"));
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setBackground(Color.decode("#007bff"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(table);

        nuevoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nuevoButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                nuevoButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(MouseEvent evt) {
                nuevoComputador nvoCO = new nuevoComputador(null);
                nvoCO.setVisible(true);
                table.setModel(compuCon.obtenerTablaComputadores());

            }
        });

        eliminarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
                    Object valorColumna1 = table.getValueAt(filaSeleccionada, 0); // Obtener el valor de la columna 1

                    // Realizar alguna acción con los valores obtenidos
                    int idCompu = (int) valorColumna1;

                    int confirmacion = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar al cliente: " + idCompu + "?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        computadorController.eliminarComputador(idCompu);
                        table.setModel(compuCon.obtenerTablaComputadores()); // Actualiza la tabla con los nuevos datos
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente.");
                }

            }
        });

        editarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editarButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editarButton.setBackground(btnBackgroundColor);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
                    Object valorColumna1 = table.getValueAt(filaSeleccionada, 0); // Obtener el valor de la columna 1
                    Object valorColumna2 = table.getValueAt(filaSeleccionada, 1); // Obtener el valor de la columna 2
                    Object valorColumna3 = table.getValueAt(filaSeleccionada, 2); // Obtener el valor de la columna 1
                    Object valorColumna5 = table.getValueAt(filaSeleccionada, 4); // Obtener el valor de la columna 1

                    // Realizar alguna acción con los valores obtenidos
                    int idComputador = (int) valorColumna1;
                    String numSerie = (String) valorColumna2;
                    String modelo = (String) valorColumna3;
                    float precioHh = (float) valorColumna5;

                    JFrame frame = new JFrame();
                    editarComputador edtCompu = new editarComputador(frame, idComputador, numSerie, modelo, precioHh);
                    edtCompu.setVisible(true);
                    table.setModel(compuCon.obtenerTablaComputadores());

                } else {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente.");
                }

            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Agregar un margen exterior

        // Agregar botones y espacio a la disposición
        panel.add(buttonsPanel, BorderLayout.NORTH);
        panel.add(spacePanel, BorderLayout.CENTER); // Espacio entre los botones y la tabla
        panel.add(scrollPane, BorderLayout.CENTER); // Cambiar a BorderLayout.CENTER para la tabla

        // Limpiar y actualizar el JPanel principal para mostrar la tabla
        jPanelMain.removeAll();
        jPanelMain.setLayout(new BorderLayout());
        jPanelMain.add(panel, BorderLayout.CENTER); // Agregar al centro para evitar la superposición
        jPanelMain.revalidate();
        jPanelMain.repaint();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de
        // menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel
        // izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(1020, 600)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(1020, 800)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36));
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(102, 0, 204));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize(new java.awt.Dimension(1020, 120));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}
