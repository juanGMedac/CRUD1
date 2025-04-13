package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase para la conexión con una base de datos Oracle Database Autonomous
 *
 * @author ...
 */
public class ConexionOracle {

    // Bloque estático para configurar la ubicación del wallet (ruta relativa a la raíz del proyecto)
    static {
        // Ejemplo de ruta absoluta (ajusta si tu ruta difiere).
        System.setProperty("oracle.net.tns_admin",
                "C:\\Users\\juanG\\IdeaProjects\\2024_2025\\ProyectoPrimero\\EjemploOracle\\CRUD1\\lib\\Wallet_EjConexion"
        );
    }

    // Cadena de conexión para Oracle Autonomous Database.
    // El alias en tnsnames.ora debe coincidir (por ejemplo, ejconexion_high)
    private static final String URL = "jdbc:oracle:thin:@ejconexion_medium";

    // Credenciales de la base de datos (reemplaza con las credenciales reales)
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "Hugosanchez@94";

    // Atributo de instancia para almacenar la conexión
    private final Connection connection;

    // Constructor público vacío que inicializa la conexión
    public ConexionOracle() {
        this.connection = crearConexion();
    }

    // Método privado para crear la conexión a la base de datos
    private Connection crearConexion() {
        Connection cn = null;
        try {
            // Carga explícita del driver Oracle (opcional en JDBC 4.0+)
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("¡Conexión exitosa a Oracle Autonomous Database!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver Oracle JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a Oracle Autonomous Database.");
            e.printStackTrace();
        }
        return cn;
    }

    // Método para obtener la conexión (si es necesario usarla en otras partes)
    public Connection getConnection() {
        return connection;
    }

    // Método para cerrar la conexión de forma segura
    public void cerrar() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Ejecuta una consulta SELECT.
     *
     * @param consulta Consulta SELECT a ejecutar.
     * @return Resultado de la consulta.
     * @throws SQLException En caso de error con la base de datos.
     */
    public ResultSet ejecutarSelect(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(consulta);
    }

    /**
     * Ejecuta una consulta INSERT, DELETE o UPDATE.
     *
     * @param consulta Consulta a ejecutar.
     * @return Cantidad de filas afectadas.
     * @throws SQLException En caso de error con la base de datos.
     */
    public int ejecutarInsertDeleteUpdate(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(consulta);
    }
}
