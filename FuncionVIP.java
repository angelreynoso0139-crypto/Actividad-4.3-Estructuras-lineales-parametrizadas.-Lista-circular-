package cine.circular;

public class FuncionVIP extends Proyeccion {
    private int numeroAsiento;

    public FuncionVIP(String titulo, String director, int anioEstreno, int numeroAsiento) {
        super(titulo, director, anioEstreno);
        this.numeroAsiento = numeroAsiento;
    }

    public int getNumeroAsiento() { return numeroAsiento; }

    @Override
    public String toString() {
        return "[VIP] " + super.toString() + " | Asiento VIP: " + numeroAsiento;
    }
}