package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.dto.ClienteDTO;
import utils.ConexionBBDD;

public class ClienteDAO {

	public ArrayList<ClienteDTO> obtenerClientes() {

		ArrayList<ClienteDTO> listaClientes = new ArrayList<>();

		try {

			Connection conexion = ConexionBBDD.getConexion();

			String sql = "SELECT * FROM clientes";

			PreparedStatement ps = conexion.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");

				ClienteDTO cliente = new ClienteDTO(id, nombre, email, telefono);
				listaClientes.add(cliente);
			}
			conexion.close();
			return listaClientes;
		} catch (SQLException e) {
			System.out.println("Error en la BBDD: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	public boolean insertarCliente(ClienteDTO cliente) {
		try {
			Connection conexion = ConexionBBDD.getConexion();

			String sql = "INSERT INTO clientes (nombre, email, telefono) VALUES (?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getTelefono());

			int filasAfectadas = ps.executeUpdate();
			conexion.close();

			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.out.println("Error en la BBDD: " + e.getMessage());
			return false;
		}
	}

	public boolean actualizar(ClienteDTO cliente) {
		try {
			Connection conexion = ConexionBBDD.getConexion();
			String sql = "UPDATE clientes SET " + "nombre = CASE WHEN ? = '' THEN nombre ELSE ? END, "
					+ "email = CASE WHEN ? = '' THEN email ELSE ? END, "
					+ "telefono = CASE WHEN ? = 0 THEN telefono ELSE ? END " + "WHERE id = ?;";
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getNombre());

			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getEmail());

			ps.setString(5, cliente.getTelefono());
			ps.setString(6, cliente.getTelefono());

			ps.setInt(7, cliente.getId());

			int numFilas = ps.executeUpdate();
			conexion.close();

			return numFilas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean borrar(int id) {
		String sql = "DELETE FROM clientes WHERE id = ?";
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
