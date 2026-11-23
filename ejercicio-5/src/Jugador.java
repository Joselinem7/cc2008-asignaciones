public class Jugador {
    private String nombre;
    private int energia;
    private int puntos;

    public Jugador(String nombre) {
        if (nombre.trim().isEmpty()) throw new IllegalArgumentException("Ingrese un nombre.");
        this.nombre = nombre.trim();
        energia = 10;
        puntos = 0;
    }

    public String getNombre() { return nombre; }
    public int getEnergia() { return energia; }
    public int getPuntos() { return puntos; }
    public void cambiarEnergia(int cambio) { energia = Math.max(0, Math.min(20, energia+cambio)); }
    public void cambiarPuntos(int cambio) { puntos = Math.max(0, puntos+cambio); }

    @Override
    public String toString() { return nombre + " | Energia: " + energia + " | Puntos: " + puntos; }
}
