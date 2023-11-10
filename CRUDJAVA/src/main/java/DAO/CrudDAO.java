
package DAO;

import ConectDB.Conexion;
import DTO.Datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class CrudDAO implements DAOinterface{

    @Override
    public void insertar(Datos datos) {
        // Crear una instancia de la clase para establecer la conexión
        Conexion conexion = new Conexion();
            // Convierte la fecha a un tipo java.sql.Date
    java.sql.Date fechaSQL = new java.sql.Date(datos.getFecha().getTime());
    try (Connection conn = conexion.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO CRUDJP.datos (nombre, telefono, fecha) VALUES (?, ?, ?)")) {

        String nombre = datos.getNombre(); // Obtén el nombre de 'datos'
        int telefono = datos.getTelefono(); // Obtén el teléfono de 'datos'
        
        stmt.setString(1, nombre);
        stmt.setInt(2, telefono);
        stmt.setDate(3, fechaSQL);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void select(Datos datos) {
    
    }
    
    //metodo select 
    //metodo listar dao
public List<String[]> selectDatos() {
    // Crear una lista para almacenar los datos
    List<String[]> datosList = new ArrayList<>();
    //intancia de la conexion
    // Crear una instancia de la clase para establecer la conexión
        Conexion conexion = new Conexion();

    try (Connection conn = conexion.getConnection()) {
        // Definir la consulta SQL para seleccionar datos de la tabla "CRUDJAVA.datos"
        String sqlQuery = "SELECT * FROM CRUDJP.datos"; // Ajusta el nombre de la tabla según tu base de datos

        // Preparar la consulta SQL
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Ejecutar la consulta y obtener los resultados
        ResultSet rs = stmt.executeQuery();

        // Iterar a través de los resultados
        while (rs.next()) {
            // Obtener valores de las columnas en el resultado
            String id = rs.getString("id");
            String nombre = rs.getString("nombre");
            String telefono = rs.getString("telefono");
            String fecha = rs.getString("fecha");

            // Crear un array de Strings con los valores y agregarlo a la lista
            String[] fila = {id, nombre, telefono, fecha};
            datosList.add(fila);
        }
    } catch (SQLException e) {
        // Manejar excepciones SQL
        e.printStackTrace();
    }

    // Devolver la lista de datos
    return datosList;
}
    

    @Override
    public void update(Datos datos) {
          // Convierte la fecha a un tipo java.sql.Date
    java.sql.Date fechaSQL = new java.sql.Date(datos.getFecha().getTime());
     // Crear una instancia de la clase para establecer la conexión
        Conexion conexion = new Conexion();
        
    try (Connection conn = conexion.getConnection(); // Establece una conexión a la base de datos
         PreparedStatement stmt = conn.prepareStatement(
            "UPDATE CRUDJP.datos SET nombre=?, telefono=?, fecha=? WHERE id=?")) {
        // Obtiene los valores necesarios del objeto 'datos' para la actualización
        String nombre = datos.getNombre(); // Obtén el nombre de 'datos'
        int telefono = datos.getTelefono(); // Obtén el teléfono de 'datos'
        int id = datos.getId(); // Obtén el ID del registro que deseas actualizar

        // Establece los parámetros en la sentencia SQL
        stmt.setString(1, nombre); // El primer '?' se reemplaza por 'nombre'
        stmt.setInt(2, telefono); // El segundo '?' se reemplaza por 'telefono'
        stmt.setDate(3, fechaSQL); // El tercer '?' se reemplaza por 'fechaSQL'
        stmt.setInt(4, id); // El cuarto '?' se reemplaza por 'id'

        // Ejecuta la sentencia SQL para actualizar el registro en la base de datos
        stmt.executeUpdate();
    } catch (SQLException e) {
        // Maneja posibles errores de SQL e imprime la información del error
        e.printStackTrace();
    }
    }

    @Override
    public void delete(Datos datos) {

        // Crear una instancia de la clase para establecer la conexión
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM CRUDJP.datos WHERE id=?")) {
            // Obtén el ID del registro que deseas eliminar
            int id = datos.getId();
            // Establece el valor del ID para identificar el registro a eliminar
            stmt.setInt(1, id);
            // Ejecuta la sentencia SQL para eliminar el registro
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Maneja posibles errores de SQL e imprime la información del error
            e.printStackTrace();
        }
    }

}
