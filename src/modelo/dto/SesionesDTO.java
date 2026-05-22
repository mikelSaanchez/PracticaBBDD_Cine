package modelo.dto;

public class SesionesDTO {

	private int id;
	private int id_pelicula;
	private int id_sala;
	private String fecha;
	private String hora;
	private double precio;
	private int asientos_disponibles;

	public SesionesDTO(int id, int id_pelicula, int id_sala, String fecha, String hora, double precio,
			int asientos_disponibles) {
		super();
		this.id = id;
		this.id_pelicula = id_pelicula;
		this.id_sala = id_sala;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.asientos_disponibles = asientos_disponibles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getAsientos_disponibles() {
		return asientos_disponibles;
	}

	public void setAsientos_disponibles(int asientos_disponibles) {
		this.asientos_disponibles = asientos_disponibles;
	}

}
