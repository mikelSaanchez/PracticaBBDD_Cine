package vista;

import java.io.IOException;

import controlador.SesionController;
import utils.Lecturas;

public class VistaSesiones {
	
	SesionController sesionController = new SesionController();
	
	public void menuSesiones() throws IOException {
		boolean salir = false;
		do {
			System.out.println("\n<<=== Gestion Sesiones =>>");
			System.out.println("1. Listar Sesiones");
			System.out.println("2. Añadir Sesiones");
			System.out.println("3. Editar Sesiones ");
			System.out.println("4. Borrar Sesiones");
			System.out.println("0. Salir al menu principal");
			int opcion = Lecturas.leerEnteroEnRango("Introduce una opción: ", 0, 4);
			switch (opcion) {
			case 1:
				System.out.println("= VER TODAS LAS SESIONES =");
				
				break;
			case 2:
				System.out.println("= INSERTAR SESION =");
				
				break;
			case 3:
				System.out.println("= ACTUALIZAR SESION =");
				
				break;
			case 4:
				System.out.println("= BORRAR SESION =");
				
				break;

			case 0:
				salir = true;
				break;
			}
		} while (!salir);
	}

}
