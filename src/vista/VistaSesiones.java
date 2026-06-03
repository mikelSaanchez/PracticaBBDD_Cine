package vista;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import controlador.SesionController;
import modelo.dto.SesionesDTO;
import utils.Lecturas;

public class VistaSesiones {

	SesionController sesionController = new SesionController();
	private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	
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
		String fecha = Lecturas.leerString("Fecha (yyyy-MM-dd): ");
		if (!esFechaValida(fecha)) {
			System.out.println("ERROR. El formato de la fecha no es valido.");
			return;
		}
		String hora = Lecturas.leerString("Hora (HH:mm): ");
		if (!esHoraValida(hora)) {
			System.out.println("ERROR. El formato de la hora no es valido.");
			return;
		}
		double precio = Lecturas.leerDouble("Precio de la entrada (€): ");

		boolean resultado = sesionController.programarSesion(idPelicula, idSala, fecha, hora, precio);
		if (!resultado) {
			System.out.println("No se pudo programar la sesión.");
		}
		
	}

	public boolean esFechaValida(String fecha) {
		if (fecha == null || fecha.isBlank())
			return false;
		try {
			LocalDate.parse(fecha, formatoFecha);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
	public boolean esHoraValida(String hora) {
		if (hora == null || hora.isBlank())
			return false;
		try {
			LocalTime.parse(hora, formatoHora);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
