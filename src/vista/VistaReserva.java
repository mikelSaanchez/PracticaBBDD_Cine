package vista;

import java.io.IOException;
import java.util.ArrayList;

import controlador.ReservaController;

import modelo.dto.ReservaDTO;
import utils.Lecturas;

public class VistaReserva {

	private final ReservaController reservaController = new ReservaController();

	public void menuReservas() throws IOException {
		boolean salir = false;
		do {
			System.out.println("\n<<=== Gestion Reservas =>>");
			System.out.println("1. Listar Reservas");
			System.out.println("2. Realizar Reserva");
			System.out.println("0. Salir al menu principal");
			int opcion = Lecturas.leerEnteroEnRango("Introduce una opción: ", 0, 2);
			switch (opcion) {
			case 1:
				System.out.println("= VER TODAS LAS RESERVAS =");
				listarReservas();
				break;
			case 2:
				System.out.println("= REALIZAR RESERVA =");
				realizarReserva();
				break;
			case 0:
				salir = true;
				break;
			}
		} while (!salir);
	}

	private void listarReservas() {
		System.out.println("--- Listado de reservas ---");

		ArrayList<ReservaDTO> lista = reservaController.obtenerReservas();

		System.out.println("╔═══════════════════════════╗");
		System.out.println("║          RESERVAS  	    ║");
		System.out.println("╚═══════════════════════════╝");

		for (ReservaDTO reserva : lista) {
			System.out.println(reserva.getId() + " - " + reserva.getTituloPelicula() + " - " + reserva.getHoraSesion()
					+ " - " + reserva.getNombreCliente() + " - " + reserva.getNumEntradas() + " - "
					+ reserva.getTotal());

		}
	}

	private void realizarReserva() {
		System.out.println("--- Realizar nueva reserva ---");

		int idSesion = Lecturas.leerEntero("Id de la sesión: ");
		int idCliente = Lecturas.leerEntero("Id del cliente: ");
		int numEntradas = Lecturas.leerEntero("Número de entradas: ");

		boolean resultado = reservaController.realizarReserva(idSesion, idCliente, numEntradas);

		if (!resultado) {
			System.out.println("No se pudo completar la reserva.");
		}
	}
}
