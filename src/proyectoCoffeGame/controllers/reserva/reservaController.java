
package proyectoCoffeGame.controllers.reserva;

import proyectoCoffeGame.config.Basededatos;
import proyectoCoffeGame.models.clienteModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                            "r.fechaHoraF " +
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

                    // Agregamos los datos al modelo
                    Object[] fila = { idReserva, cliente, equipo, fechaInicio, fechaFin };
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