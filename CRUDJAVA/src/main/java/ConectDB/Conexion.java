
package ConectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class Conexion {

    //conexion
    private Connection conexion;

    //constructor 
    public Conexion() {
        try {
            //parametros de conexion
            String url = "jdbc:postgresql://localhost:5432/CRUDJP";
            String user = "postgres";
            String pass = "12345";
            //inicializar la conexion
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conexion;
       }
    
}//llave final