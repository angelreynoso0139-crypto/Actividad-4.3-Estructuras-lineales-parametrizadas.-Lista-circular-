package cine.circular;

public class FuncionTradicional extends Proyeccion {
    private int numeroSala;

    public FuncionTradicional(String titulo, String director, int anioEstreno, int numeroSala) {
        super(titulo, director, anioEstreno);
        this.numeroSala = numeroSala;
    }

    public int getNumeroSala() { return numeroSala; }

    @Override
    public String toString() {
        return "[TRADICIONAL] " + super.toString() + " | Sala N.º: " + numeroSala;
    }
}