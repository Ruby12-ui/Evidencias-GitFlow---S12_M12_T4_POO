package vallegrande.edu.pe.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    public static Connection getConexion() {
        try {
            // Carga manual del driver para evitar "No suitable driver found"
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Puerto 3307 segun tu configuracion
            String url = "jdbc:mysql://localhost:3307/usuarios1?serverTimezone=UTC";
            String user = "root";
            String pass = "Ruby120";

            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa");
            return con;
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}