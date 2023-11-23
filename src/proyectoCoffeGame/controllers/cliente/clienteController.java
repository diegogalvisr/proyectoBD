
package proyectoCoffeGame.controllers.cliente;

import proyectoCoffeGame.config.Basededatos;
import proyectoCoffeGame.models.clienteModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class clienteController {

    public DefaultTableModel obtenerTablaClientes() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID Cliente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Email");
        modelo.addColumn("Fecha de registro");

        try {
            Basededatos.conectar();
            ResultSet resultado = Basededatos
                    .ejecutarSQL("SELECT cl.idCliente, cl.nombre, cl.email, cl.fechaReg FROM cliente cl;");
            if (resultado != null) {
                while (resultado.next()) {
                    int idCliente = resultado.getInt("idCliente");
                    String nombre = resultado.getString("nombre");
                    String email = resultado.getString("email");
                    String fechaReg = resultado.getString("fechaReg");

                    // Agregamos los datos al modelo
                    Object[] fila = { idCliente, nombre, email, fechaReg };
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