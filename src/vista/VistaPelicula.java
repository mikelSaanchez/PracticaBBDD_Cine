package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controlador.PeliculaController;
import modelo.dto.PeliculaDTO;
import utils.Lecturas;

public class VistaPelicula {

	public void menuPelicula() throws IOException {
		boolean salir = false;
		do {
			System.out.println("\n=== Gestion Peliculas =>>");
			System.out.println("1. Listar Peliculas");
			System.out.println("2. Añadir Peliculas");
			System.out.println("3. Editar Peliculas ");
			System.out.println("4. Borrar Peliculas");
			System.out.println("0. Salir al menu principal");
			int opcion = Lecturas.leerEnteroEnRango("Introduce una opción: ", 0, 4);
			switch (opcion) {
			case 1:
				System.out.println("= VER TODAS LAS PELICULAS =");
				mostrarPeliculas();
				break;
			case 2:
				System.out.println("= INSERTAR PELICULA =");
				insertarPelicula();
				break;
			case 3:
				System.out.println("= ACTUALIZAR PELICULA =");
				actualizarPelicula();
				break;
			case 4:
				System.out.println("= BORRAR PELICULA =");
				borrarProducto();
				break;

			case 0:
				salir = true;
				break;
			}
		} while (!salir);
	}

	public void mostrarPeliculas() {
		PeliculaController peliculaController = new PeliculaController();

		ArrayList<PeliculaDTO> lista = peliculaController.obtenerPeliculas();

		System.out.println("----------------");
		System.out.println("PELICULAS");
		System.out.println("----------------");
		for (PeliculaDTO pelicula : lista) {
			System.out.println(pelicula.getId() + " - " + pelicula.getTitulo() + " - " + pelicula.getGenero() + " - "
					+ pelicula.getDuracion() + " - " + pelicula.getAnio());
		}
	}

	private void insertarPelicula() {

		String nombre = Lecturas.leerString("Introduce el titulo de la nueva Pelicula: ");
		String genero = Lecturas.leerString("Introduce el genero de la nueva Pelicula: ");
		int duracion = Lecturas.leerEntero("Introduce la duracion de la nueva Pelicula: ");
		int anio = Lecturas.leerEntero("Introduce el año de la nueva Pelicula: ");

		PeliculaDTO peliculaInsertar = new PeliculaDTO(nombre, genero, duracion, anio);

		PeliculaController peliculaController = new PeliculaController();
		boolean insertadoOK = peliculaController.insertar(peliculaInsertar);
		if (insertadoOK) {
			System.out.println("Pelicula añadido correctamente");
		} else {
			System.out.println("Error al añadir la categoria");
		}
	}
	
	
	public void actualizarPelicula() throws IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

		int id = Lecturas.leerEntero("Introduce el id de la pelicula a modificar:");

		System.out.println("Introduce el titulo de la pelicula: ");
		String titulo = leer.readLine();

		System.out.println("Introduce el genero de la pelicula: ");
		String genero = leer.readLine();

		System.out.println("Introduce la duración de la pelicula (0 no modificar): ");
		int duracion = Integer.parseInt(leer.readLine());

		System.out.println("Introduce el anio de la pelicula: ");
		int anio = Integer.parseInt(leer.readLine());

		PeliculaDTO pelicula = new PeliculaDTO(id, titulo, genero, duracion, anio);
		PeliculaController peliculaController = new PeliculaController();
		boolean todoOk = peliculaController.actualizar(pelicula);
		if (todoOk) {
			System.out.println("Se ha modificado correctamente");
		}else {
			System.out.println("No se ha podido actualizar");
		}
	}

	private void borrarProducto() {
		int id = Lecturas.leerEntero("Introduce el id de la pelicula a borrar:");
		PeliculaController peliculaController = new PeliculaController();
		boolean borradoOK = peliculaController.borrar(id);
		
		if (borradoOK) {
			System.out.println("Producto borrado correctamente");
		} else {
			System.out.println("Error al borrar el producto");
		}
	}

}
