public class Catedratico extends Carta {
    private String departamento;
    private int llamadasAtencion;
    private int tiempoAtencion;

    public Catedratico(int id, String nombre, int costo, String departamento, int llamadas, int tiempo) {
        super(id, nombre, costo, "Resta puntos al rival y aporta puntos por tiempo de atencion.");
        if (llamadas < 0 || tiempo < 0) throw new IllegalArgumentException("Los puntos no pueden ser negativos.");
        this.departamento = departamento;
        llamadasAtencion = llamadas;
        tiempoAtencion = tiempo;
    }

    @Override
    public String jugarCarta(Jugador actual, Jugador rival) {
        rival.cambiarPuntos(-llamadasAtencion);
        actual.cambiarPuntos(tiempoAtencion);
        return "Catedratico: el rival pierde " + llamadasAtencion + " puntos y usted gana " + tiempoAtencion + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Departamento: " + departamento + " | Llamadas: "
                + llamadasAtencion + " | Tiempo de atencion: " + tiempoAtencion;
    }
}
