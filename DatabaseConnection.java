package Gs_Inventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	//Info of the connection
	private static final String URL = "jdbc:mysql://localhost:3306/store_db";
	private static final String USER = "root";
	private static final String PASS = "1234";
	
	//Start connection method
	public static Connection getConnection() throws SQLException {
		try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos: ", e);
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
