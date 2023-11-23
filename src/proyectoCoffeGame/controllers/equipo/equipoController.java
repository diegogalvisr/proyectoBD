
package proyectoCoffeGame.controllers.equipo;

import proyectoCoffeGame.config.Basededatos;
import proyectoCoffeGame.models.equipoModel;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class equipoController {

    public DefaultTableModel obtenerTablaEquipos() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Equipo");

        try {
            Basededatos.conectar();
            ResultSet resultado = Basededatos.ejecutarSQL("SELECT * FROM equipo");
            if (resultado != null) {
                while (resultado.next()) {
                    int idEquipo = resultado.getInt("idEquipo");
                    String nomEquipo = resultado.getString("nombre");
                    // Agregamos los datos al modelo
                    Object[] fila = { idEquipo, nomEquipo };
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

    public Object[][] obtenerArrayEquipos(int idEquipo) {
        ArrayList<String> resultados = new ArrayList<>();

        try {
            Basededatos.conectar();
            String query = "SELECT CONCAT(de.idCliente, ' - ', c.nombre) AS 'ID Cliente - Nombre' " +
                    "FROM detalle_equipo de " +
                    "JOIN cliente c ON c.idCliente = de.idCliente " +
                    "WHERE de.idEquipo = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(query);
            statement.setInt(1, idEquipo);

            ResultSet resultado = statement.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    String idClienteNombre = resultado.getString("ID Cliente - Nombre");
                    resultados.add(idClienteNombre);
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

        // Convertir ArrayList a un array bidimensional
        Object[][] arrayResultados = new Object[resultados.size()][1];
        for (int i = 0; i < resultados.size(); i++) {
            arrayResultados[i][0] = resultados.get(i);
        }

        return arrayResultados;
    }

    public static void insertarEquipo(equipoModel equipoMod) {
        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO equipo (nombre) VALUES (?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, equipoMod.getNombre());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el equipo: " + equipoMod.getNombre());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el equipo: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void actualizarEquipo(equipoModel eqpMo) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE equipo SET nombre = ? WHERE idEquipo = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, eqpMo.getNombre());
            statement.setInt(2, eqpMo.getIdEquipo());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado exitosamente el equipo: " + eqpMo.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void eliminarEquipo(int idEquipo) {
        try {
            Basededatos.conectar();
            String consulta = "DELETE FROM equipo WHERE idEquipo = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idEquipo);
            statement.executeUpdate();
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = fechaHoraActual.format(formatter);
            FileWriter fileWriter = new FileWriter("logEliEquipo.txt", true); // El true indica que se añadirá al final
                                                                              // del archivo
            PrintWriter writer = new PrintWriter(fileWriter);
            String textoRegistro = fechaFormateada + " - Se ha eliminado la equipo ID: " + idEquipo
                    + " de la Base de datos..";
            writer.println(textoRegistro);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }

    }

    public int obtenerConteoEquipos() {
        int conteo = 0;
        try {
            Basededatos.conectar();
            String query = "SELECT COUNT(*) AS total FROM equipo";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(query);

            ResultSet resultado = statement.executeQuery();

            if (resultado != null && resultado.next()) {
                conteo = resultado.getInt("total");
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
        return conteo;
    }

}