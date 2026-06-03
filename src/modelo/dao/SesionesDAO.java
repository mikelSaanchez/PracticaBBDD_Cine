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

			String sql = "SELECT s.id, p.titulo, s1.numero, s.fecha, s.hora, s.precio, s.asientos_disponibles "
					+ " FROM sesiones s "
					+ " JOIN peliculas p"
					+ " ON s.id_pelicula = p.id "
					+ " JOIN salas s1 "
					+ " on s.id_sala = s1.id ";

			PreparedStatement ps = conexion.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				int numero = rs.getInt("numero");
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
		Connection conexion = null;
		try {
			conexion = ConexionBBDD.getConexion();

			conexion.setAutoCommit(false);

			// Comprobar que la sala no tiene ya otra sesión programada en la misma fecha y
			// hora
			String comprobarSesion = "SELECT COUNT(*) FROM sesiones WHERE id_sala = ? AND fecha = ? AND hora = ?";
			PreparedStatement psComprobar = conexion.prepareStatement(comprobarSesion);
			psComprobar.setInt(1, idSala);
			psComprobar.setString(2, fecha);
			psComprobar.setString(3, hora);
			ResultSet rsComprobarSesion = psComprobar.executeQuery();
			rsComprobarSesion.next();
			int sesionesExistentes = rsComprobarSesion.getInt(1);
			psComprobar.close();

			if (sesionesExistentes > 0) {
				conexion.rollback();
				throw new SQLException("La sala " + idSala + " ya tiene una sesión programada en la fecha " + fecha
						+ " a las " + hora);
			}

			// Si está libre, insertar la nueva sesión con asientos_disponibles igual al
			// aforo de la sala
			String sqlAforo = "SELECT aforo FROM salas WHERE id = ?";
			PreparedStatement psAforo = conexion.prepareStatement(sqlAforo);
			psAforo.setInt(1, idSala);
			ResultSet rsComprobarAforo = psAforo.executeQuery();

			if (!rsComprobarAforo.next()) {
				conexion.rollback();
				throw new SQLException("No existe ninguna sala con id " + idSala + ".");
			}
			int aforo = rsComprobarAforo.getInt("aforo");
			psAforo.close();

			String sqlInsertar = "INSERT INTO sesiones (id_pelicula, id_sala, fecha, hora, precio, asientos_disponibles) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement psInsertar = conexion.prepareStatement(sqlInsertar);
			psInsertar.setInt(1, idPelicula);
			psInsertar.setInt(2, idSala);
			psInsertar.setString(3, fecha);
			psInsertar.setString(4, hora);
			psInsertar.setDouble(5, precio);
			psInsertar.setInt(6, aforo);
			psInsertar.executeUpdate();
			psInsertar.close();

			conexion.commit();
			
			System.out.println("Sesion añadida correctamente. :)");
			return true;

		} catch (SQLException e) {

			System.out.println("Error al programar la sesión: " + e.getMessage());
			if (conexion != null) {
				try {
					conexion.rollback();
					System.out.println("Cambios revertidos correctamente.");
				} catch (SQLException ex) {
					System.out.println("Error en rollback: " + ex.getMessage());
				}
			}
			return false;
		} finally {

			if (conexion != null) {
				try {
					conexion.setAutoCommit(true);
					conexion.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar conexión: " + e.getMessage());
				}
			}
		}

	}
}
