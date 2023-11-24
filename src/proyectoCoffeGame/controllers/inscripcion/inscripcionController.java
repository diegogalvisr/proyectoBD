package proyectoCoffeGame.controllers.inscripcion;

import proyectoCoffeGame.config.Basededatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class inscripcionController {

    public DefaultTableModel obtenerTablaInscripcion() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Juego Torneo");
        modelo.addColumn("Nombre Equipo");
        try {
            Basededatos.conectar();
            ResultSet resultado = Basededatos.ejecutarSQL(
                    "select i.idInscripcion ,t.nombreT  ,e.nombre as nomE  from inscripcion i join torneo t  on t.idTorneo =i.idTorneo join equipo e on e.idEquipo = i.idEquipo ");

            if (resultado != null) {
                while (resultado.next()) {
                    int idTorneo = resultado.getInt("idInscripcion");
                    String nombreJuego = resultado.getString("nombreT");
                    String fechaInicio = resultado.getString("nomE");

                    // Agregamos los datos al modelo
                    Object[] fila = { idTorneo, nombreJuego, fechaInicio };
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

    public static List<String> obtenerTorneos() {
        List<String> clientes = new ArrayList<>();
        try {
            Basededatos.conectar();
            String consulta = "select nombreT from torneo";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clientes.add(resultSet.getString("nombreT"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los torneos: " + e.getMessage(), "Error",
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

    public static int saberIDTorneo(String modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "SELECT idTorneo FROM torneo WHERE nombreT = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idTorneo");
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

    public static void insertarInscripcion(int equipo, int torneo) {
        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO inscripcion (idEquipo, idTorneo) VALUES (?, ?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros
            statement.setInt(1, equipo);
            statement.setInt(2, torneo);

            // Ejecutar la inserción
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inscripcion insertada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void eliminarInscripcion(int idTorneo) {
        try {
            Basededatos.conectar();
            String consulta = "DELETE FROM inscripcion WHERE idInscripcion = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idTorneo);
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            System.out.println("Se ha eliminado la inscripcion de la BD.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

    }
}