package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.PeliculaDTO;
import utils.ConexionBBDD;

public class PeliculaDAO {

	public ArrayList<PeliculaDTO> obtenerPeliculas() {

		ArrayList<PeliculaDTO> listaPeliculas = new ArrayList<>();

		try {

			Connection conexion = ConexionBBDD.getConexion();

			String sql = "SELECT * FROM peliculas";

			PreparedStatement ps = conexion.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				int duracion = rs.getInt("duracion");
				int anio = rs.getInt("anio");

				PeliculaDTO pelicula = new PeliculaDTO(id, titulo, genero, duracion, anio);
				listaPeliculas.add(pelicula);
			}
			conexion.close();
			return listaPeliculas;
		} catch (SQLException e) {
			System.out.println("Error en la BBDD: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	public boolean insertarPelicula(PeliculaDTO pelicula) {
		try {
			Connection conexion = ConexionBBDD.getConexion();

			String sql = "INSERT INTO peliculas (titulo, genero, duracion, anio) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setString(1, pelicula.getTitulo());
			ps.setString(2, pelicula.getGenero());
			ps.setInt(3, pelicula.getDuracion());
			ps.setInt(4, pelicula.getAnio());

			int filasAfectadas = ps.executeUpdate();
			conexion.close();

			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.out.println("Error en la BBDD: " + e.getMessage());
			return false;
		}
	}

	public boolean actualizar(PeliculaDTO pelicula) {
		try {
			Connection conexion = ConexionBBDD.getConexion();
			String sql = "UPDATE peliculas SET " + "titulo = CASE WHEN ? = '' THEN titulo ELSE ? END, "
					+ "genero = CASE WHEN ? = '' THEN genero ELSE ? END, "
					+ "duracion = CASE WHEN ? = 0 THEN duracion ELSE ? END, "
					+ "anio = CASE WHEN ? = 0 THEN anio ELSE ? END " + "WHERE id = ?;";
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setString(1, pelicula.getTitulo());
			ps.setString(2, pelicula.getTitulo());

			ps.setString(3, pelicula.getGenero());
			ps.setString(4, pelicula.getGenero());

			ps.setInt(5, pelicula.getDuracion());
			ps.setInt(6, pelicula.getDuracion());

			ps.setInt(7, pelicula.getAnio());
			ps.setInt(8, pelicula.getAnio());

			ps.setInt(9, pelicula.getId());

			int numFilas = ps.executeUpdate();
			conexion.close();

			return numFilas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public PeliculaDTO obtenerPorId(int id) {
		try {
			Connection conexion = ConexionBBDD.getConexion();
			String sql = "SELECT id, titulo, genero, duracion, anio FROM peliculas WHERE id = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				int duracion = rs.getInt("duracion");
				int anio = rs.getInt("anio");
				PeliculaDTO pelicula = new PeliculaDTO(id, titulo, genero, duracion, anio);
				return pelicula;
			}
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener película: " + e.getMessage());
		}
		return null;
	}

	public boolean borrar(int id) {
		String sql = "DELETE FROM peliculas WHERE id = ?";
		try {
			Connection conexion = ConexionBBDD.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);

			int filasAfectadas = ps.executeUpdate();

			conexion.close();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.out.println("Error al borrar producto: " + e.getMessage());
			return false;
		}
	}

}
