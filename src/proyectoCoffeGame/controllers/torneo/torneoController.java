package proyectoCoffeGame.controllers.torneo;

import proyectoCoffeGame.config.Basededatos;
import proyectoCoffeGame.models.clienteModel;
import proyectoCoffeGame.models.torneoModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class torneoController {

    public DefaultTableModel obtenerTablaTorneos() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Juego");
        modelo.addColumn("FechaIni");
        modelo.addColumn("TotalEquipos");
        modelo.addColumn("Premio");
        modelo.addColumn("GanadorEQP");

        try {
            Basededatos.conectar();
            ResultSet resultado = Basededatos.ejecutarSQL("SELECT t.idTorneo, j.nombre AS nombreJuego, t.fechaHoraI, " +
                    "t.numParticipantes, t.premio, e.nombre AS equipoGanador " +
                    "FROM torneo t " +
                    "JOIN juego j ON j.idJuego = t.idJuego " +
                    "LEFT JOIN equipo e ON e.idEquipo = t.idEquipoGanador;");

            if (resultado != null) {
                while (resultado.next()) {
                    int idTorneo = resultado.getInt("idTorneo");
                    String nombreJuego = resultado.getString("nombreJuego");
                    String fechaInicio = resultado.getString("fechaHoraI");
                    int numParticipantes = resultado.getInt("numParticipantes");
                    int premio = resultado.getInt("premio");
                    String equipoGanador = resultado.getString("equipoGanador");

                    // Agregamos los datos al modelo
                    Object[] fila = { idTorneo, nombreJuego, fechaInicio, numParticipantes, premio, equipoGanador };
                    modelo.addRow(fila);
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

        return modelo;
    }

    public static List<String> obtenerJuegos() {
        List<String> clientes = new ArrayList<>();
        try {
            Basededatos.conectar();
            String consulta = "select nombre from juego";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clientes.add(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los juegos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
        return clientes;
    }

    public static List<String> obtenerEquipo() {
        List<String> clientes = new ArrayList<>();
        try {
            Basededatos.conectar();
            String consulta = "select nombre from equipo";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clientes.add(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los equipos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
        return clientes;
    }

    public static int saberIDJuego(String modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "SELECT idJuego FROM juego WHERE nombre = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idJuego");
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al obtener el ID de la consola: " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }

        return idConsola;
    }

    public static int saberIDEquipo(String modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "SELECT idEquipo FROM equipo WHERE nombre = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idEquipo");
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al obtener el ID del equpo: " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }

        return idConsola;
    }

    public static void insertarTorneo(int idJuego, String fechaHoraI, int numParticipantes, int premio) {
        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO torneo (idJuego, fechaHoraI, numParticipantes, premio) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros
            statement.setInt(1, idJuego);
            statement.setString(2, fechaHoraI);
            statement.setInt(3, numParticipantes);
            statement.setInt(4, premio);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Torneo insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void insertarCliente(clienteModel cliente) {
        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO cliente (nombre, email) VALUES (?, ?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getEmail());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el cliente: " + cliente.getNombre());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void actualizarTorneo(torneoModel clT) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE torneo SET idEquipoGanador = ? WHERE idTorneo=?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, clT.getIdEquipoGanador());
            statement.setInt(2, clT.getIdTorneo());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado exitosamente el torneo: " + clT.getIdTorneo());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void eliminarTorneo(int idTorneo) {
        try {
            Basededatos.conectar();
            String consulta = "DELETE FROM torneo WHERE idTorneo = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idTorneo);
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            System.out.println("Se ha eliminado el torneo de la BD.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

    }
}