
package proyectoCoffeGame.controllers.reserva;

import proyectoCoffeGame.config.Basededatos;
import proyectoCoffeGame.models.clienteModel;
import proyectoCoffeGame.models.computadorModel;
import proyectoCoffeGame.models.consolaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class reservaController {

    public DefaultTableModel obtenerTablaReservas() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID Reserva");
        modelo.addColumn("Cliente");
        modelo.addColumn("Equipo");
        modelo.addColumn("Fecha de Inicio");
        modelo.addColumn("Fecha de Fin");
        modelo.addColumn("Estado");

        try {
            Basededatos.conectar();
            ResultSet resultado = Basededatos.ejecutarSQL(
                    "SELECT " +
                            "r.idReserva, " +
                            "CONCAT(c.idCliente, '-', c.nombre) AS Cliente, " +
                            "CASE " +
                            "   WHEN c2.modelo IS NOT NULL THEN CONCAT('Computador: ', c2.modelo) " +
                            "   WHEN c3.modelo IS NOT NULL THEN CONCAT('Consola: ', c3.modelo) " +
                            "END AS Equipo, " +
                            "r.fechaHoraI, " +
                            "r.fechaHoraF, r.estado " +
                            "FROM reserva r " +
                            "JOIN cliente c ON c.idCliente = r.idCliente " +
                            "LEFT JOIN computador c2 ON c2.idCompu = r.idComputador " +
                            "LEFT JOIN consola c3 ON c3.idConsola = r.idConsola");

            if (resultado != null) {
                while (resultado.next()) {
                    int idReserva = resultado.getInt("idReserva");
                    String cliente = resultado.getString("Cliente");
                    String equipo = resultado.getString("Equipo");
                    String fechaInicio = resultado.getString("fechaHoraI");
                    String fechaFin = resultado.getString("fechaHoraF");
                    String estado = resultado.getString("estado");

                    // Agregamos los datos al modelo
                    Object[] fila = { idReserva, cliente, equipo, fechaInicio, fechaFin, estado };
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

    public static List<String> modeloConsola() {
        List<String> modelosConsolas = new ArrayList<>();

        try {
            Basededatos.conectar();
            String consulta = "SELECT modelo FROM consola WHERE estado = 'ACTIVO'";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                modelosConsolas.add(resultSet.getString("modelo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los modelos de consola: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

        return modelosConsolas;
    }

    public static List<String> modeloComputador() {
        List<String> modelosConsolas = new ArrayList<>();

        try {
            Basededatos.conectar();
            String consulta = "SELECT modelo FROM computador WHERE estado = 'ACTIVO'";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                modelosConsolas.add(resultSet.getString("modelo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los modelos de computador: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

        return modelosConsolas;
    }

    public static List<String> obtenerClientes() {
        List<String> clientes = new ArrayList<>();
        try {
            Basededatos.conectar();
            String consulta = "select nombre from cliente";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clientes.add(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los clientes: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
        return clientes;
    }

    public static void actualizarCliente(clienteModel clT) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE cliente SET nombre = ?, email = ? WHERE idCliente=?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, clT.getNombre());
            statement.setString(2, clT.getEmail());
            statement.setInt(3, clT.getIdCliente());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado exitosamente el Cliente: " + clT.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void actuaEstCompuEnt(computadorModel clT) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE computador SET estado = ? WHERE idCompu=?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, clT.getEstado());
            statement.setInt(2, clT.getIdCompu());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado exitosamente el estado del computador: " + clT.getIdCompu());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void actuaEstConsoEnt(consolaModel clT) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE consola SET estado = ? WHERE idConsola=?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, clT.getEstado());
            statement.setInt(2, clT.getIdConsola());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado exitosamente el estado de la consola: " + clT.getIdConsola());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void actuaEstReservaCompu(int idReserva, String fechaFinal, String estado) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE reserva SET fechaHoraF = ?, estado = ? WHERE idReserva = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, fechaFinal);
            statement.setString(2, estado);
            statement.setInt(3, idReserva);
            statement.executeUpdate();
            // BasedeDatos.desconectar(); -> Este comentario debería estar en otro lugar
            JOptionPane.showMessageDialog(null, "Se ha actualizado exitosamente la reserva con ID: " + idReserva);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static int saberIDConsola(String modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "SELECT idConsola FROM consola WHERE modelo = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idConsola");
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

    public static int saberIDComputador(String modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "SELECT idCompu FROM computador WHERE modelo = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idCompu");
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

    public static int saberValorHoraCompu(int idReserva) {
        int totalaPagar = -1;

        try {
            Basededatos.conectar();
            String consulta = "select precioHora from computador where idCompu = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idReserva);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalaPagar = resultSet.getInt("precioHora");
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al obtener el precio de hora del computador " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }

        return totalaPagar;
    }

    public static int saberIDCliente(String modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "SELECT idCliente FROM cliente WHERE nombre = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idCliente");
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al obtener el ID de la computador: " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }
        return idConsola;
    }

    public static int saberIDCompuReserva(int modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "select r.idComputador from reserva r where idReserva = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idComputador");
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al obtener el ID de la computador: " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }
        return idConsola;
    }

    public static int saberIDConsoReserva(int modelo) {
        int idConsola = -1; // Valor predeterminado en caso de no encontrar ningún resultado

        try {
            Basededatos.conectar();
            String consulta = "select r.idConsola from reserva r where idReserva = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, modelo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idConsola = resultSet.getInt("idConsola");
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al obtener el ID de la computador: " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }
        return idConsola;
    }

    public static boolean insertarReservaConsola(int idCliente, int idConsola, String fechaHoraInicio) {
        boolean insertado = false;

        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO reserva (idCliente, idConsola, fechaHoraI) VALUES (?, ?, ?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idCliente);
            statement.setInt(2, idConsola);
            statement.setString(3, fechaHoraInicio);

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                // Si la inserción en reserva fue exitosa, procedemos a actualizar el estado de
                // consola
                String consultaActualizar = "UPDATE consola SET estado = 'PRESTADO' WHERE idConsola = ?";
                PreparedStatement statementActualizar = Basededatos.conexion.prepareStatement(consultaActualizar);
                statementActualizar.setInt(1, idConsola);

                int filasActualizadas = statementActualizar.executeUpdate();

                if (filasActualizadas > 0) {
                    insertado = true;
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al insertar reserva y actualizar estado: " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }

        return insertado;
    }

    public static boolean insertarReservaComputador(int idCliente, int idConsola, String fechaHoraInicio) {
        boolean insertado = false;

        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO reserva (idCliente, idComputador, fechaHoraI) VALUES (?, ?, ?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idCliente);
            statement.setInt(2, idConsola);
            statement.setString(3, fechaHoraInicio);

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                // Si la inserción en reserva fue exitosa, procedemos a actualizar el estado de
                // consola
                String consultaActualizar = "UPDATE computador SET estado = 'PRESTADO' WHERE idCompu = ?";
                PreparedStatement statementActualizar = Basededatos.conexion.prepareStatement(consultaActualizar);
                statementActualizar.setInt(1, idConsola);

                int filasActualizadas = statementActualizar.executeUpdate();

                if (filasActualizadas > 0) {
                    insertado = true;
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción: imprimir el mensaje de error
            System.err.println("Error al insertar reserva y actualizar estado: " + e.getMessage());
            // Podrías lanzar una excepción aquí o manejar el error de alguna otra manera
        } finally {
            Basededatos.desconectar(); // Asegúrate de cerrar la conexión
        }

        return insertado;
    }

    public static void eliminarCliente(int id_libro) {
        try {
            Basededatos.conectar();
            String consulta = "DELETE FROM cliente WHERE idCliente = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, id_libro);
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            System.out.println("Se ha eliminado el cliente de la BD.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

    }
}