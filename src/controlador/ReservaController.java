package controlador;

import java.util.ArrayList;

import modelo.dao.ReservaDAO;
import modelo.dto.ReservaDTO;


public class ReservaController {

	ReservaDAO dao = new ReservaDAO();

	public ArrayList<ReservaDTO> obtenerReservas() {
		return dao.obtenerTodasReservas();
	}

	public boolean realizarReserva(int idSesion, int idCliente, int numEntradas) {
		
		return dao.realizarReserva(idSesion, idCliente, numEntradas);
	}
}
