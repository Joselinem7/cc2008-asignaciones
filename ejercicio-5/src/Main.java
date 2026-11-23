public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Juego juego = null;
        while (juego == null) {
            try {
                juego = new Juego(vista.leerTexto("Nombre del jugador 1: "),
                        vista.leerTexto("Nombre del jugador 2: "));
            } catch (IllegalArgumentException e) { vista.mostrar(e.getMessage()); }
        }
        Controlador controlador = new Controlador(juego, vista);
        controlador.iniciar();
    }
}
