package cine.circular;

public class FuncionIMAX extends Proyeccion {
    private String tipoAudio;

    public FuncionIMAX(String titulo, String director, int anioEstreno, String tipoAudio) {
        super(titulo, director, anioEstreno);
        this.tipoAudio = tipoAudio;
    }

    public String getTipoAudio() { return tipoAudio; }

    @Override
    public String toString() {
        return "[IMAX] " + super.toString() + " | Audio: " + tipoAudio;
    }
}