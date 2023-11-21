
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

        /*
         * public DefaultTableModel busqueda(String nombreSeleccion) {
         * DefaultTableModel modelo = new DefaultTableModel();
         * 
         * // Definimos las columnas del modelo
         * modelo.addColumn("ID Libro");
         * modelo.addColumn("ISBN");
         * modelo.addColumn("Título");
         * modelo.addColumn("Autor");
         * modelo.addColumn("Editorial");
         * modelo.addColumn("Tipo de Libro");
         * modelo.addColumn("Precio");
         * modelo.addColumn("Contenido Material");
         * modelo.addColumn("Categoría");
         * modelo.addColumn("Cantidad");
         * 
         * String sql =
         * "SELECT id_libro, isbn, titulo, (SELECT nombre FROM editorial WHERE id_editorial = lb.id_editorial) as id_editorial, (SELECT nombre FROM autor WHERE id_autor = lb.id_autor) as id_autor, tipo_libro, precio, contMaterial, categoria, cantidad FROM libros lb WHERE isbn LIKE ? OR titulo LIKE ? OR id_editorial LIKE ? OR id_autor LIKE ? OR tipo_libro LIKE ? OR precio LIKE ? OR contMaterial LIKE ? OR categoria LIKE ? OR cantidad LIKE ?"
         * ;
         * 
         * try {
         * BasedeDatos.conectar();
         * PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
         * String parametro = "%" + nombreSeleccion + "%";
         * stmt.setString(1, parametro);
         * stmt.setString(2, parametro);
         * stmt.setString(3, parametro);
         * stmt.setString(4, parametro);
         * stmt.setString(5, parametro);
         * stmt.setString(6, parametro);
         * stmt.setString(7, parametro);
         * stmt.setString(8, parametro);
         * stmt.setString(9, parametro);
         * 
         * ResultSet resultado = stmt.executeQuery();
         * 
         * if (resultado != null) {
         * while (resultado.next()) {
         * int id_libro = resultado.getInt("id_libro");
         * String isbn = resultado.getString("isbn");
         * String titulo = resultado.getString("Titulo");
         * String autor = resultado.getString("id_editorial");
         * String editorial = resultado.getString("id_autor");
         * String tipo_libro = resultado.getString("tipo_libro");
         * int precio = resultado.getInt("precio");
         * String contMaterial = resultado.getString("contMaterial");
         * String categoria = resultado.getString("categoria");
         * int cantidad = resultado.getInt("CANTIDAD");
         * 
         * clienteModel clM = new clienteModel();
         * clM.setIdCliente(resultado.getInt("id_libro"));
         * librosDTO.setIdLibro(resultado.getInt("id_libro"));
         * 
         * System.out.println("ID LIBRO: " + librosDTO.getIdLibro());
         * 
         * // Agregamos los datos al modelo
         * Object[] fila = {id_libro, isbn, titulo, autor, editorial, tipo_libro,
         * precio, contMaterial, categoria, cantidad};
         * modelo.addRow(fila);
         * }
         * }
         * } catch (Exception e) {
         * e.printStackTrace();
         * System.out.println("Error consultando libros");
         * } finally {
         * BasedeDatos.desconectar(); // Cerrar la conexión
         * }
         * 
         * return modelo;
         * }
         * 
         *
         * 
         * 
         * public static void cargarAutores(JComboBox<String> comboBox) {
         * try {
         * BasedeDatos.conectar();
         * String consulta = "SELECT nombre FROM autor";
         * PreparedStatement statement =
         * BasedeDatos.conexion.prepareStatement(consulta);
         * ResultSet resultado = statement.executeQuery();
         * 
         * // Limpiar el JComboBox antes de agregar los nombres de autores
         * comboBox.removeAllItems();
         * comboBox.addItem("Seleccione");
         * while (resultado.next()) {
         * String nombreAutor = resultado.getString("nombre");
         * comboBox.addItem(nombreAutor);
         * }
         * 
         * // Cerrar los recursos
         * resultado.close();
         * statement.close();
         * } catch (Exception e) {
         * e.printStackTrace();
         * } finally {
         * BasedeDatos.desconectar(); // Cerrar la conexión
         * }
         * }
         * public static void cargarEditoriales(JComboBox<String> comboBox) {
         * try {
         * BasedeDatos.conectar();
         * String consulta = "SELECT nombre FROM editorial";
         * PreparedStatement statement =
         * BasedeDatos.conexion.prepareStatement(consulta);
         * ResultSet resultado = statement.executeQuery();
         * 
         * // Limpiar el JComboBox antes de agregar los nombres de autores
         * comboBox.removeAllItems();
         * comboBox.addItem("Seleccione");
         * while (resultado.next()) {
         * String nombreAutor = resultado.getString("nombre");
         * comboBox.addItem(nombreAutor);
         * }
         * 
         * // Cerrar los recursos
         * resultado.close();
         * statement.close();
         * } catch (Exception e) {
         * e.printStackTrace();
         * } finally {
         * BasedeDatos.desconectar(); // Cerrar la conexión
         * }
         * }
         * 
         * 
         * }
         * 
         * public static void actualizarLibro(LibrosDTO libro) {
         * try {
         * BasedeDatos.conectar();
         * String consulta =
         * "UPDATE libros SET isbn = ?, titulo = ?, id_editorial = ?, id_autor = ?, tipo_libro = ?, precio = ?, contMaterial = ?, categoria = ?, cantidad = ? WHERE id_libro=?"
         * ;
         * PreparedStatement statement =
         * BasedeDatos.conexion.prepareStatement(consulta);
         * statement.setInt(1, libro.getIsbn());
         * statement.setString(2, libro.getTitulo());
         * statement.setInt(3, libro.getIdEditorial());
         * statement.setInt(4, libro.getIdAutor());
         * statement.setString(5, libro.getTipoLibro());
         * statement.setDouble(6, libro.getPrecio());
         * statement.setString(7, libro.getContMaterial());
         * statement.setString(8, libro.getCategoria());
         * statement.setInt(9, libro.getCantidad());
         * statement.setInt(10, libro.getIdLibro());
         * statement.executeUpdate();
         * // BasedeDatos.desconectar();
         * JOptionPane.showMessageDialog(null,
         * "Se ha actualizado exitosamente el libro: " + libro.getTitulo());
         * } catch (Exception e) {
         * e.printStackTrace();
         * } finally {
         * BasedeDatos.desconectar(); // Cerrar la conexión
         * }
         * }
         */

    }
}