package vista;

import java.io.IOException;

import utils.Lecturas;

public class VistaCliente {

	public void menuCliente() throws IOException {
		boolean salir = false;
		do {
			System.out.println("\n=== Gestion Clientes =>>");
			System.out.println("1. Listar Clientes");
			System.out.println("2. Añadir Clientes");
			System.out.println("3. Editar Clientes ");
			System.out.println("4. Borrar Clientes");
			System.out.println("0. Salir al menu principal");
			int opcion = Lecturas.leerEnteroEnRango("Introduce una opción: ", 0, 4);
			switch (opcion) {
			case 1:
				System.out.println("= VER TODOS LOS CLIENTES =");
				
				break;
			case 2:
				System.out.println("= INSERTAR CLIENTE =");
				
				break;
			case 3:
				System.out.println("= ACTUALIZAR CLIENTE =");
				
				break;
			case 4:
				System.out.println("= BORRAR CLIENTE =");
				
				break;

			case 0:
				salir = true;
				break;
			}
		} while (!salir);
	}
}
