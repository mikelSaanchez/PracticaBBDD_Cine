package controlador;

import java.util.ArrayList;

import modelo.dao.ClienteDAO;
import modelo.dto.ClienteDTO;

public class ClienteController {
	ClienteDAO dao = new ClienteDAO();

	public ArrayList<ClienteDTO> obtenerClientes() {

		return dao.obtenerClientes();
	}

	public boolean insertar(ClienteDTO clienteInsertar) {

		return dao.insertarCliente(clienteInsertar);
	}

	public String validar(String email, String telefono) {

		if (!email.isEmpty() && !email.matches("^[^@]+@[^@]+\\.(com|es)$")) {
			return "El email '" + email + "' no tiene un formato válido.";
		}

		if (!telefono.isEmpty() && !telefono.matches("[0-9]{9}")) {
			return "El teléfono solo puede contener dígitos.";
		}
		return null;
	}

	public boolean actualizar(ClienteDTO cliente) {

		return dao.actualizar(cliente);
	}

	public boolean borrar(int id) {

		return dao.borrar(id);
	}

}
