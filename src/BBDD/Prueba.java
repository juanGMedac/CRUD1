package BBDD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Prueba {

    public static void main(String[] args){


        String sentencia = "INSERT INTO Personas(Nombre, Edad) VALUES ('Pablo', 18)";
        String busqueda = "SELECT * FROM Personas";
        ConexionOracle conexion = new ConexionOracle();

        try {
            conexion.getConnection();
            conexion.ejecutarInsertDeleteUpdate(sentencia);

            ResultSet resultado = conexion.ejecutarSelect(busqueda);

            while (resultado.next()) {
                String nombre = resultado.getString("Nombre");
                int edad = resultado.getInt("Edad");
                System.out.println("Nombre: " + nombre + ", Edad: " + edad);
            }

            resultado.close();
            conexion.cerrar();

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
        }

    }

}
