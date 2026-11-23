public class Curso extends Carta {
    private int creditos;
    private int dificultad;

    public Curso(int id, String nombre, int costo, int creditos, int dificultad) {
        super(id, nombre, costo, "Aporta puntos equivalentes a creditos por dificultad.");
        if (creditos <= 0 || dificultad <= 0) throw new IllegalArgumentException("Creditos y dificultad deben ser positivos.");
        this.creditos = creditos;
        this.dificultad = dificultad;
    }

    @Override
    public String jugarCarta(Jugador actual, Jugador rival) {
        int puntos = creditos * dificultad;
        actual.cambiarPuntos(puntos);
        return "Curso aprobado: gana " + puntos + " puntos.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Creditos: " + creditos + " | Dificultad: " + dificultad;
    }
}
