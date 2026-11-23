import java.util.Scanner;

public class Vista {
    private Scanner entrada;

    public Vista() { entrada = new Scanner(System.in); }
    public void mostrar(String mensaje) { System.out.println(mensaje); }
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try { return Integer.parseInt(leerTexto(mensaje)); }
            catch (NumberFormatException e) { mostrar("Ingrese un numero entero."); }
        }
    }

    public void mostrarCarta(Carta carta) {
        mostrar(carta == null ? "No se encontro la carta." : carta.toString());
    }

    public void mostrarCartas(Juego juego, boolean soloMano) {
        boolean hayCartas = false;
        for (int i = 0; i < juego.getCantidadCartas(); i++) {
            Carta carta = juego.getCarta(i);
            if (!soloMano || carta.getUbicacion() == juego.getTurnoActual()) {
                String lugar;
                if (carta.getUbicacion() == -1) lugar = "Mazo";
                else if (carta.getUbicacion() == 2) lugar = "Jugada";
                else lugar = "Mano de " + juego.getJugador(carta.getUbicacion()).getNombre();
                mostrarCarta(carta);
                mostrar("Ubicacion: " + lugar);
                hayCartas = true;
            }
        }
        if (!hayCartas) mostrar("No tiene cartas en la mano.");
    }

    public void cerrar() { entrada.close(); }
}
