package cine_BBDD;

import java.io.IOException;

import utils.Lecturas;
import vista.VistaCliente;
import vista.VistaPelicula;
import vista.VistaSesiones;

public class Principal {

	public static void main(String[] args) throws IOException {
		boolean salir = false;
		do {
		    System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       GESTIÓN DE CINE        ║");
            System.out.println("╚══════════════════════════════╝");
			System.out.println("1. Gestión de películas");
			System.out.println("2. Gestión de clientes");
			System.out.println("3. Gestión de sesiones");
			System.out.println("0. Salir");

			int opcion = Lecturas.leerEnteroEnRango("Introduce una opcion: ", 0, 3);

			switch (opcion) {
			case 1:
				VistaPelicula vp = new VistaPelicula();
				vp.menuPelicula();
				break;
			case 2:
				VistaCliente vc = new VistaCliente();
				vc.menuCliente();
				break;
			case 3:
				VistaSesiones vs = new VistaSesiones();
				vs.menuSesiones();
				break;
			case 0:
				System.out.println("Saliendo del programa. Hasta pronto!");
				salir = true;
				break;
			}

		} while (!salir);

	}

}
