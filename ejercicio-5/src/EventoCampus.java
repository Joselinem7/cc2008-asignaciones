public class EventoCampus extends Carta {
    private int cambioEnergia;

    public EventoCampus(int id, String nombre, int costo, int cambioEnergia) {
        super(id, nombre, costo, "Cambia la energia de ambos jugadores.");
        this.cambioEnergia = cambioEnergia;
    }

    @Override
    public String jugarCarta(Jugador actual, Jugador rival) {
        actual.cambiarEnergia(cambioEnergia);
        rival.cambiarEnergia(cambioEnergia);
        return "Evento campus: la energia de ambos cambia en " + cambioEnergia + ".";
    }

    @Override
    public String toString() { return super.toString() + " | Cambio de energia: " + cambioEnergia; }
}
