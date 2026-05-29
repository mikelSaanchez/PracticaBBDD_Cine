package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.SesionesDTO;
import utils.ConexionBBDD;

public class SesionesDAO {
	public ArrayList<SesionesDTO> obtenerTodasSesiones() {

		ArrayList<SesionesDTO> listaSesiones = new ArrayList<>();

		try {

			Connection conexion = ConexionBBDD.getConexion();

			String sql = "SELECT s.id, p.titulo, s.id_sala, s.fecha, s.hora, s.precio, s.asientos_disponibles "
					+ " FROM sesiones s " + " JOIN peliculas p " + " ON s.id_pelicula = p.id ";

			PreparedStatement ps = conexion.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				int numero = rs.getInt("id_sala");
				String fecha = rs.getString("fecha");
				String hora = rs.getString("hora");
				double precio = rs.getDouble("precio");
				int asientosDisponibles = rs.getInt("asientos_disponibles");

				SesionesDTO sesiones = new SesionesDTO(id, titulo, numero, fecha, hora, precio, asientosDisponibles);
				listaSesiones.add(sesiones);
			}
			conexion.close();
			return listaSesiones;
		} catch (SQLException e) {
			System.out.println("Error en la BBDD: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	public boolean borrar(int id) {
		String sql = "DELETE FROM sesiones WHERE id = ?";
		try {
			Connection conexion = ConexionBBDD.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);

			int filasAfectadas = ps.executeUpdate();

			conexion.close();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.out.println("Error la sesion: " + e.getMessage());
			return false;
		}
	}
	public boolean programarSesion(int idPelicula, int idSala, String fecha, String hora, double precio) {
		
		
		return false;
		
	}
}
