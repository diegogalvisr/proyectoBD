
package proyectoCoffeGame.controllers.computador;

import proyectoCoffeGame.config.Basededatos;
import proyectoCoffeGame.models.computadorModel;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class computadorController {

    public DefaultTableModel obtenerTablaComputadores() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Num Serie");
        modelo.addColumn("Modelo");
        modelo.addColumn("Estado");
        modelo.addColumn("Precio/Hora");

        try {
            Basededatos.conectar();
            ResultSet resultado = Basededatos.ejecutarSQL("SELECT * FROM computador");
            if (resultado != null) {
                while (resultado.next()) {
                    int idCompu = resultado.getInt("idCompu");
                    String numSerie = resultado.getString("numSerie");
                    String modeloC = resultado.getString("modelo");
                    String estado = resultado.getString("estado");
                    int precioH = resultado.getInt("precioHora");
                    // Agregamos los datos al modelo
                    Object[] fila = { idCompu, numSerie, modeloC, estado, precioH };
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

    public static void insertarCompu(computadorModel comput) {
        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO computador (numSerie,modelo,precioHora) VALUES (?,?,?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, comput.getNumSerie());
            statement.setString(2, comput.getModelo());
            statement.setFloat(3, comput.getPrecioHora());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el computador: " + comput.getNumSerie());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void actualizarCompu(computadorModel compM) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE computador SET numSerie = ?, modelo = ?, estado = ?, precioHora = ? WHERE idCompu = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, compM.getNumSerie());
            statement.setString(2, compM.getModelo());
            statement.setString(3, compM.getEstado());
            statement.setFloat(4, compM.getPrecioHora());
            statement.setInt(5, compM.getIdCompu());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado exitosamente el computador: " + compM.getModelo());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void eliminarComputador(int idCompu) {
        try {
            Basededatos.conectar();
            String consulta = "DELETE FROM computador WHERE idCompu = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idCompu);
            statement.executeUpdate();
            // BasedeDatos.desconectar();

            LocalDateTime fechaHoraActual = LocalDateTime.now();

            // Formatea la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = fechaHoraActual.format(formatter);
            FileWriter fileWriter = new FileWriter("logEliminacionComputador.txt", true); // El true indica que se
                                                                                          // añadirá al final del
                                                                                          // archivo
            PrintWriter writer = new PrintWriter(fileWriter);
            String textoRegistro = fechaFormateada + " - Se ha eliminado el computador ID: " + idCompu
                    + " de la Base de datos..";
            writer.println(textoRegistro);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

    }
}