package vista;

import java.io.IOException;
import java.util.ArrayList;

import controlador.SesionController;
import modelo.dto.SesionesDTO;
import utils.Lecturas;

public class VistaSesiones {

	SesionController sesionController = new SesionController();

	public void menuSesiones() throws IOException {
		boolean salir = false;
		do {
			System.out.println("\n<<=== Gestion Sesiones =>>");
			System.out.println("1. Listar Sesiones");
			System.out.println("2. Borrar Sesiones");
			System.out.println("3. Añadir una Sesion ");
			System.out.println("0. Salir al menu principal");
			int opcion = Lecturas.leerEnteroEnRango("Introduce una opción: ", 0, 3);
			switch (opcion) {
			case 1:
				System.out.println("= VER TODAS LAS SESIONES =");
				mostrarSesiones();
				break;
			case 2:
				System.out.println("= BORRAR SESION =");
				borrarSesion();
				break;
			case 3:
				System.out.println("= AÑADIR SESION =");
				programarSesion();
				break;
			case 0:
				salir = true;
				break;
			}
		} while (!salir);
	}

	public void mostrarSesiones() {

		ArrayList<SesionesDTO> lista = sesionController.obtenerSesiones();

		System.out.println("╔═══════════════════════════╗");
		System.out.println("║          SESIONES  	    ║");
		System.out.println("╚═══════════════════════════╝");

		for (SesionesDTO sesiones : lista) {
			System.out.println(sesiones.getId() + " - " + sesiones.getTituloPelicula() + " - "
					+ sesiones.getNumeroSala() + " - " + sesiones.getFecha() + " - " + sesiones.getHora() + " - "
					+ sesiones.getPrecio() + " - " + sesiones.getAsientosDisponibles());
		}
	}

	private void borrarSesion() {
		int id = Lecturas.leerEntero("Introduce el id de e la sesion a borrar:");

		boolean borradoOK = sesionController.borrar(id);

		if (borradoOK) {
			System.out.println("Sesion borrada correctamente");
		} else {
			System.out.println("Error al borrar la sesion");
		}
	}

	private void programarSesion() {
		System.out.println("\n--- Programar nueva sesión ---");

		int idPelicula = Lecturas.leerEntero("Id de la película: ");
		int idSala = Lecturas.leerEntero("Id de la sala: ");
		String fecha = Lecturas.leerString("Fecha (YYYY-MM-DD): ");
		String hora = Lecturas.leerString("Hora (HH:MM:SS): ");
		double precio = Lecturas.leerDouble("Precio de la entrada (€): ");

		boolean resultado = sesionController.programarSesion(idPelicula, idSala, fecha, hora, precio);
		if (!resultado) {
			System.out.println("No se pudo programar la sesión.");
		}
		
	}

}
