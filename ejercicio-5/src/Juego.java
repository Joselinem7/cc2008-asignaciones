import java.util.ArrayList;
import java.util.Random;

public class Juego {
    private ArrayList<Carta> cartas;
    private Jugador[] jugadores;
    private int turnoActual;
    private int turnosJugados;

    public Juego(String nombre1, String nombre2) {
        cartas = new ArrayList<Carta>();
        jugadores = new Jugador[] {new Jugador(nombre1), new Jugador(nombre2)};
        turnoActual = 0;
        turnosJugados = 0;
        cargarCartas();
        for (int i = 0; i < 6; i++) cartas.get(i).setUbicacion(i % 2);
    }

    private void cargarCartas() {
        Random random = new Random();
        String[] departamentos = {"Computacion", "Matematica", "Ciencias"};
        String[] cursos = {"Programacion", "Calculo", "Fisica"};
        for (int i = 0; i < 18; i++) {
            int tipo = i < 3 ? i : random.nextInt(3);
            int costo = random.nextInt(5)+1;
            if (tipo == 0) {
                cartas.add(new Catedratico(i+1, "Catedratico " + (i+1), costo,
                        departamentos[random.nextInt(3)], random.nextInt(4)+1, random.nextInt(3)+1));
            } else if (tipo == 1) {
                cartas.add(new Curso(i+1, cursos[random.nextInt(3)] + " " + (i+1), costo,
                        random.nextInt(4)+1, random.nextInt(3)+1));
            } else {
                boolean feria = random.nextBoolean();
                cartas.add(new EventoCampus(i+1, (feria ? "Feria de Clubes " : "Semana de Parciales ")
                        + (i+1), costo, feria ? 4 : -3));
            }
        }
    }

    public int getCantidadCartas() { return cartas.size(); }
    public Carta getCarta(int indice) { return cartas.get(indice); }
    public int getTurnoActual() { return turnoActual; }
    public int getTurnosJugados() { return turnosJugados; }
    public Jugador getJugador(int indice) { return jugadores[indice]; }

    public Carta buscar(int id) {
        for (Carta carta : cartas) if (carta.getId() == id) return carta;
        return null;
    }

    public Carta buscar(String nombre) {
        for (Carta carta : cartas) if (carta.getNombre().equalsIgnoreCase(nombre.trim())) return carta;
        return null;
    }

    public void ordenarPorEnergia() {
        // Ordenamiento de burbuja para no requerir comparadores ni expresiones lambda.
        for (int i = 0; i < cartas.size()-1; i++) {
            for (int j = 0; j < cartas.size()-1-i; j++) {
                if (cartas.get(j).getCostoEnergia() > cartas.get(j+1).getCostoEnergia()) {
                    Carta temporal = cartas.get(j);
                    cartas.set(j, cartas.get(j+1));
                    cartas.set(j+1, temporal);
                }
            }
        }
    }

    public String jugar(int id) {
        if (haTerminado()) throw new IllegalArgumentException("La partida ya termino.");
        Carta carta = buscar(id);
        if (carta == null || carta.getUbicacion() != turnoActual) {
            throw new IllegalArgumentException("Esa carta no esta en su mano.");
        }
        Jugador actual = jugadores[turnoActual];
        if (carta.getCostoEnergia() > actual.getEnergia()) {
            throw new IllegalArgumentException("No tiene suficiente energia.");
        }
        actual.cambiarEnergia(-carta.getCostoEnergia());
        // Java elige el efecto de la subclase sin preguntar de que tipo es la carta.
        String resultado = carta.jugarCarta(actual, jugadores[1-turnoActual]);
        carta.setUbicacion(2);
        siguienteTurno();
        return resultado;
    }

    public String tomarCarta() {
        if (haTerminado()) throw new IllegalArgumentException("La partida ya termino.");
        for (Carta carta : cartas) {
            if (carta.getUbicacion() == -1) {
                carta.setUbicacion(turnoActual);
                String resultado = "Tomo: " + carta.getNombre();
                siguienteTurno();
                return resultado;
            }
        }
        throw new IllegalArgumentException("El mazo esta vacio. Puede jugar o pasar.");
    }

    public void pasarTurno() {
        if (haTerminado()) throw new IllegalArgumentException("La partida ya termino.");
        siguienteTurno();
    }

    private void siguienteTurno() {
        turnosJugados++;
        turnoActual = 1-turnoActual;
        if (!haTerminado()) jugadores[turnoActual].cambiarEnergia(2);
    }

    public boolean haTerminado() { return turnosJugados >= 20; }
    public String resultado() {
        if (jugadores[0].getPuntos() == jugadores[1].getPuntos()) return "Empate.";
        int ganador = jugadores[0].getPuntos() > jugadores[1].getPuntos() ? 0 : 1;
        return "Gana " + jugadores[ganador].getNombre() + ".";
    }
}
