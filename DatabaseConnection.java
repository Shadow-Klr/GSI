package GSI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//Info of the connection
	private static final String URL = "jdbc:mysql://localhost:3306/store_db";
	private static final String USER = "root";
	private static final String PASS = "1234";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	//Start connection method
	public static Connection getConnection() throws SQLException {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC de MySQL no encontrado", e);
        }
    }
	
	//Close connection method
	public static void closeconnection(Connection conn) throws SQLException {
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (SQLException e) {
			throw new SQLException("Error closing the connection: ", e);
		}
	}
}
