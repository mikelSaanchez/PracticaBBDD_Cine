package controlador;

import java.util.ArrayList;

import modelo.dao.PeliculaDAO;
import modelo.dto.PeliculaDTO;

public class PeliculaController {

	public ArrayList<PeliculaDTO> obtenerPeliculas() {
		PeliculaDAO dao = new PeliculaDAO();
		return dao.obtenerPeliculas();
	}

	public boolean insertar(PeliculaDTO peliculaInsertar) {
		PeliculaDAO dao = new PeliculaDAO();
		return dao.insertarPelicula(peliculaInsertar);

	}

	public boolean borrar(int id) {
		PeliculaDAO dao = new PeliculaDAO();
		return dao.borrar(id);

	}

	public boolean actualizar(PeliculaDTO pelicula) {
		PeliculaDAO dao = new PeliculaDAO();
		return dao.actualizar(pelicula);
	}
}
