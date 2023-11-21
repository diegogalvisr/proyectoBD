package proyectoCoffeGame.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Basededatos {

    public static Connection conexion = null;

    public static boolean hayConexion() {
        return (conexion != null);
    }

    public static void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcafeinternet", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            String logMessage = "Ocurrió un error: " + ex.getMessage()
                    + " \nPonte en contacto con el administrador del sistema."; // Puedes personalizar el mensaje según
                                                                                // tus necesidades
            // Mostrar el mensaje de registro en un JOptionPane
            JOptionPane.showMessageDialog(null, logMessage, "Error Conexion a la BD - Bibliosoft",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public static ResultSet ejecutarSQL(String consultaSQL) throws Exception {
        if (conexion != null) {
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(consultaSQL);
            return resultado;
        } else {
            return null;
        }
    }

    public static boolean ejecutarActualizacionSQL(String comandoSQL) {

        boolean ok;
        if (hayConexion()) {
            PreparedStatement sql;
            try {
                sql = conexion.prepareStatement(comandoSQL);

                ok = sql.executeUpdate() != 0;
                // importante cerrar la conexion
                sql.close();
                sql = null;
                return ok;
            } catch (SQLException ex) {
                Logger.getLogger(Basededatos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }

    public static void desconectar() {
        try {
            if (conexion != null) {
                conexion.close();
                conexion = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Basededatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
