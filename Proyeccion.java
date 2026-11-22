package cine.circular;

public class Proyeccion {
    private String titulo;
    private String director;
    private int anioEstreno;

    public Proyeccion(String titulo, String director, int anioEstreno) {
        this.titulo = titulo;
        this.director = director;
        this.anioEstreno = anioEstreno;
    }

    public String getTitulo() { return titulo; }
    public String getDirector() { return director; }
    public int getAnioEstreno() { return anioEstreno; }

    @Override
    public String toString() {
        return "Película: " + titulo + " | Director: " + director + " | Estreno: " + anioEstreno;
    }
}