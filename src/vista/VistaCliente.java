package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controlador.ClienteController;
import modelo.dto.ClienteDTO;

import utils.Lecturas;

public class VistaCliente {

	ClienteController clienteController = new ClienteController();

	public void menuCliente() throws IOException {
		boolean salir = false;
		do {
			System.out.println("\n<<=== Gestion Clientes =>>");
			System.out.println("1. Listar Clientes");
			System.out.println("2. Añadir Clientes");
			System.out.println("3. Editar Clientes ");
			System.out.println("4. Borrar Clientes");
			System.out.println("0. Salir al menu principal");
			int opcion = Lecturas.leerEnteroEnRango("Introduce una opción: ", 0, 4);
			switch (opcion) {
			case 1:
				System.out.println("= VER TODOS LOS CLIENTES =");
				mostrarClientes();
				break;
			case 2:
				System.out.println("= INSERTAR CLIENTE =");
				insertarCliente();
				break;
			case 3:
				System.out.println("= ACTUALIZAR CLIENTE =");
				actualizarCliente();
				break;
			case 4:
				System.out.println("= BORRAR CLIENTE =");
				borrarCliente();
				break;
			case 0:
				salir = true;
				break;
			}
		} while (!salir);
	}

	public void mostrarClientes() {

		ArrayList<ClienteDTO> lista = clienteController.obtenerClientes();

		System.out.println("\n╔═══════════════════════════╗");
		System.out.println("║          CLIENTES 	    ║");
		System.out.println("╚═══════════════════════════╝");

		for (ClienteDTO cliente : lista) {
			System.out.println(cliente.getId() + " - " + cliente.getNombre() + " - " + cliente.getEmail() + " - "
					+ cliente.getTelefono());
		}
	}

	private void insertarCliente() {

		String nombre = Lecturas.leerString("Introduce el nombre de el cliente: ");
		String email = Lecturas.leerString("Introduce el email de el cliente: ");
		String telefono = Lecturas.leerString("Introduce el telefono de el cliente: ");

		String errorValidacion = clienteController.validar(email, telefono);
		if (errorValidacion != null) {
			System.out.println("Error de validación: " + errorValidacion);
			System.out.println("Cliente no registrado.");
			return;
		}

		ClienteDTO clienteInsertar = new ClienteDTO(nombre, email, telefono);

		boolean insertadoOK = clienteController.insertar(clienteInsertar);
		if (insertadoOK) {
			System.out.println("Cliente añadido correctamente");
		} else {
			System.out.println("Error al añadir el cliente");
		}
	}

	public void actualizarCliente() throws IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

		int id = Lecturas.leerEntero("Introduce el id de el cliente a modificar:");

		System.out.println("Introduce el nombre del cliente: ");
		String nombre = leer.readLine();

		System.out.println("Introduce el email de el cliente: ");
		String email = leer.readLine();

		System.out.println("Introduce el telefono de el cliente: ");
		String telefono = leer.readLine();

		String errorValidacion = clienteController.validar(email, telefono);
		if (errorValidacion != null) {
			System.out.println("Error de validación: " + errorValidacion);
			System.out.println("Cliente no registrado.");
			return;
		}

		ClienteDTO cliente = new ClienteDTO(id, nombre, email, telefono);

		boolean todoOk = clienteController.actualizar(cliente);
		if (todoOk) {
			System.out.println("Se ha modificado correctamente");
		} else {
			System.out.println("No se ha podido actualizar");
		}
	}

	private void borrarCliente() {
		int id = Lecturas.leerEntero("Introduce el id de el cliente a borrar:");

		boolean borradoOK = clienteController.borrar(id);

		if (borradoOK) {
			System.out.println("Cliente borrado correctamente");
		} else {
			System.out.println("Error al borrar el cliente");
		}
	}
}
