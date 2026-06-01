package controlador;

import java.util.ArrayList;

import modelo.dao.SesionesDAO;

import modelo.dto.SesionesDTO;

public class SesionController {

	SesionesDAO dao = new SesionesDAO();

	public ArrayList<SesionesDTO> obtenerSesiones() {
		return dao.obtenerTodasSesiones();
	}

	public boolean borrar(int id) {
		return dao.borrar(id);
	}

	public boolean programarSesion(int idPelicula, int idSala, String fecha, String hora, double precio) {
		return dao.programarSesion(idPelicula, idSala, fecha, hora, precio);
	}
}
