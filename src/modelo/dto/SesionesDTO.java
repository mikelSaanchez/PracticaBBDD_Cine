package modelo.dto;

public class SesionesDTO {

	private int id;
	private int idPelicula;
	private int idSala;
	private String fecha;
	private String hora;
	private double precio;
	private int asientosDisponibles;

	
	private String tituloPelicula;
	private int numeroSala;

	public SesionesDTO(int id, int idPelicula, int idSala, String fecha, String hora, double precio,
			int asientosDisponibles) {
		this.id = id;
		this.idPelicula = idPelicula;
		this.idSala = idSala;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.asientosDisponibles = asientosDisponibles;
	}

	public SesionesDTO(int idPelicula, int idSala, String fecha, String hora, double precio, int asientosDisponibles) {
		this.idPelicula = idPelicula;
		this.idSala = idSala;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.asientosDisponibles = asientosDisponibles;
	}

	
	public SesionesDTO(int id, String tituloPelicula, int numeroSala, String fecha, String hora, double precio,
			int asientosDisponibles) {
		this.id = id;
		this.tituloPelicula = tituloPelicula;
		this.numeroSala = numeroSala;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.asientosDisponibles = asientosDisponibles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
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

	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}

	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

}
