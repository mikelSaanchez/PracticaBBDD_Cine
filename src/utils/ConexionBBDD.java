package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

	public static Connection getConexion() {
		String url = "jdbc:mysql://localhost:3306/cine";
		String usuario = "root";
		String password = "PracticaRoot";
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexion establecida");
			return conexion;
		} catch (SQLException e) {
			System.out.println("Error al conectar" + e.getMessage());
			e.printStackTrace();
			return null;

		}
	}
}
