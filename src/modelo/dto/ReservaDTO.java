package modelo.dto;

public class ReservaDTO {

	private int id;
	private int idSesion;
	private int idCliente;
	private int numEntradas;
	private double total;

	private String nombreCliente;
	private String tituloPelicula;
	private String horaSesion;

	public ReservaDTO(int idSesion, int idCliente, int numEntradas, double total) {
		this.idSesion = idSesion;
		this.idCliente = idCliente;
		this.numEntradas = numEntradas;
		this.total = total;
	}

	public ReservaDTO(int id, String titulo, String hora, String nombre, int num_entradas, int total) {
		this.id = id;
		this.tituloPelicula=titulo;
		this.horaSesion = hora;
		this.nombreCliente = nombre;
		this.numEntradas = num_entradas;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumEntradas() {
		return numEntradas;
	}

	public void setNumEntradas(int numEntradas) {
		this.numEntradas = numEntradas;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	public String getHoraSesion() {
		return horaSesion;
	}

	public void setHoraSesion(String horaSesion) {
		this.horaSesion = horaSesion;
	}
}
