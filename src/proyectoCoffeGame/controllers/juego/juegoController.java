
package proyectoCoffeGame.controllers.juego;

import proyectoCoffeGame.config.Basededatos;
import proyectoCoffeGame.models.juegoModel;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class juegoController {

    public DefaultTableModel obtenerTablaJuegos() {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Genero");
        modelo.addColumn("Plataforma");
        modelo.addColumn("Clasificacion");

        try {
            Basededatos.conectar();
            ResultSet resultado = Basededatos.ejecutarSQL("SELECT * FROM juego");
            if (resultado != null) {
                while (resultado.next()) {
                    int idJuego = resultado.getInt("idJuego");
                    String nombre = resultado.getString("nombre");
                    String genero = resultado.getString("genero");
                    String plataforma = resultado.getString("plataforma");
                    String clasificacion = resultado.getString("clasificacion");
                    // Agregamos los datos al modelo
                    Object[] fila = { idJuego, nombre, genero, plataforma, clasificacion };
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

    public static void insertarJuego(juegoModel juegoMod) {
        try {
            Basededatos.conectar();
            String consulta = "INSERT INTO juego (nombre,genero,plataforma,clasificacion) VALUES (?,?,?,?)";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, juegoMod.getNombre());
            statement.setString(2, juegoMod.getGenero());
            statement.setString(3, juegoMod.getPlataforma());
            statement.setString(4, juegoMod.getClasificacion());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el juego: " + juegoMod.getNombre());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void actualizarJuego(juegoModel juegoM) {
        try {
            Basededatos.conectar();
            String consulta = "UPDATE juego SET nombre = ?, genero = ?, plataforma = ?, clasificacion = ? WHERE idJuego = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setString(1, juegoM.getNombre());
            statement.setString(2, juegoM.getGenero());
            statement.setString(3, juegoM.getPlataforma());
            statement.setString(4, juegoM.getClasificacion());
            statement.setInt(5, juegoM.getIdJuego());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado exitosamente el juego: " + juegoM.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Basededatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void eliminarJuego(int idJuego) {
        try {
            Basededatos.conectar();
            String consulta = "DELETE FROM juego WHERE idJuego = ?";
            PreparedStatement statement = Basededatos.conexion.prepareStatement(consulta);
            statement.setInt(1, idJuego);
            statement.executeUpdate();
            // BasedeDatos.desconectar();
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            // Formatea la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = fechaHoraActual.format(formatter);
            FileWriter fileWriter = new FileWriter("logEliConsola.txt", true); // El true indica que se añadirá al final
                                                                               // del archivo
            PrintWriter writer = new PrintWriter(fileWriter);
            String textoRegistro = fechaFormateada + " - Se ha eliminado el juego ID: " + idJuego
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