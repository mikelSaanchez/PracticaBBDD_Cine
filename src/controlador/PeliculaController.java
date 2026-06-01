package controlador;

import java.util.ArrayList;

import modelo.dao.PeliculaDAO;
import modelo.dto.PeliculaDTO;

public class PeliculaController {

	PeliculaDAO dao = new PeliculaDAO();

	public ArrayList<PeliculaDTO> obtenerPeliculas() {
		return dao.obtenerPeliculas();
	}

	public boolean insertar(PeliculaDTO peliculaInsertar) {
		return dao.insertarPelicula(peliculaInsertar);

	}

	public boolean borrar(int id) {
		return dao.borrar(id);

	}

	public boolean actualizar(PeliculaDTO pelicula) {
		return dao.actualizar(pelicula);
	}

	public PeliculaDTO obtenerPorId(int id) {
		return dao.obtenerPorId(id);
	}

}
