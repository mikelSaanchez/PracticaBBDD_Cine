package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.ReservaDTO;
import utils.ConexionBBDD;

public class ReservaDAO {

	public ArrayList<ReservaDTO> obtenerTodasReservas() {

		ArrayList<ReservaDTO> listaReservas = new ArrayList<>();
		try {
			Connection conexion = ConexionBBDD.getConexion();
			String sql = "SELECT r.id, p.titulo, s.hora, cl.nombre ,r.num_entradas, r.total " + "FROM reservas r "
					+ "JOIN clientes cl " + "ON r.id_cliente = cl.id " + "JOIN sesiones s " + "ON r.id_sesion = s.id "
					+ "JOIN peliculas p " + "ON s.id_pelicula = p.id ";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String hora = rs.getString("hora");
				String nombre = rs.getString("nombre");
				int num_entradas = rs.getInt("num_entradas");
				int total = rs.getInt("total");

				ReservaDTO reservas = new ReservaDTO(id, titulo, hora, nombre, num_entradas, total);
				listaReservas.add(reservas);
			}
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener reservas: " + e.getMessage());
		}
		return listaReservas;

	}

	public boolean realizarReserva(int idSesion, int idCliente, int numEntradas) {
		Connection conexion = null;
		try {
			conexion = ConexionBBDD.getConexion();

			conexion.setAutoCommit(false);
			// 1. Comprobar que la sesión existe y tiene suficientes asientos_disponibles
			// para el número de entradas solicitado

			String sqlSesion = "SELECT precio, asientos_disponibles FROM sesiones WHERE id = ?";
			PreparedStatement psSesion = conexion.prepareStatement(sqlSesion);
			psSesion.setInt(1, idSesion);
			ResultSet rsSesion = psSesion.executeQuery();

			if (!rsSesion.next()) {
				conexion.rollback();
				throw new SQLException("No existe ninguna sesión con id " + idSesion);
			}

			double precioPorEntrada = rsSesion.getDouble("precio");
			int asientosDisponibles = rsSesion.getInt("asientos_disponibles");

			if (asientosDisponibles < numEntradas) {
				conexion.rollback();
				throw new SQLException("Asientos insuficientes. Disponibles: " + asientosDisponibles);
			}
			// 3. Si hay asientos, insertar la reserva en reservas calculando el total como
			// num_entradas × precio de la sesión

			double total = precioPorEntrada * numEntradas;

			String sqlInsertar = "INSERT INTO reservas (id_sesion, id_cliente, num_entradas, total) "
					+ "VALUES (?, ?, ?, ?)";
			PreparedStatement psInsertar = conexion.prepareStatement(sqlInsertar);
			psInsertar.setInt(1, idSesion);
			psInsertar.setInt(2, idCliente);
			psInsertar.setInt(3, numEntradas);
			psInsertar.setDouble(4, total);
			psInsertar.executeUpdate();

			// 4. Reducir los asientos_disponibles de la sesión en sesiones

			String sqlActualizarAsientos = "UPDATE sesiones SET asientos_disponibles = asientos_disponibles - ? "
					+ "WHERE id = ? ";
			PreparedStatement psAsientos = conexion.prepareStatement(sqlActualizarAsientos);
			psAsientos.setInt(1, numEntradas);
			psAsientos.setInt(2, idSesion);
			int filasActualizadas = psAsientos.executeUpdate();
			psAsientos.close();

			if (filasActualizadas == 0) {
				conexion.rollback();
				throw new SQLException("No se pudieron descontar los asientos. Operación cancelada.");
			}

			psSesion.close();

			conexion.commit();

			System.out.println("Reserva añadida correctamente. :)");
			System.out.println("  Entradas: " + numEntradas);
			System.out.println("  Total: " + total);
			return true;

		} catch (SQLException e) {

			System.out.println("Error al programar la reserva: " + e.getMessage());
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
