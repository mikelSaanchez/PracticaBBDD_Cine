package modelo.dto;

public class PeliculaDTO {

	private int id;
	private String titulo;
	private String genero;
	private int duracion;
	private int anio;

	public PeliculaDTO(int id, String titulo, String genero, int duracion, int anio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.anio = anio;
	}

	public PeliculaDTO(String titulo, String genero, int duracion, int anio) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.anio = anio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

}
