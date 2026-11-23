public class Controlador {
    private Juego juego;
    private Vista vista;

    public Controlador(Juego juego, Vista vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrar("UVG Card Battle: 2 jugadores, 10 turnos por jugador, 3 cartas iniciales."
                + "\nCada accion de juego termina el turno. Consultar, buscar y ordenar no consumen turno."
                + "\nEnergia inicial: 10; al volver a jugar: +2; maximo: 20. Gana quien tenga mas puntos.");
        boolean salir = false;
        while (!salir && !juego.haTerminado()) {
            vista.mostrar("\nTurno " + (juego.getTurnosJugados()+1) + " de 20. Juega: "
                    + juego.getJugador(juego.getTurnoActual()).getNombre());
            vista.mostrar(juego.getJugador(0).toString());
            vista.mostrar(juego.getJugador(1).toString());
            vista.mostrar("1. Listar catalogo\n2. Buscar carta\n3. Ordenar por energia"
                    + "\n4. Ver mi mano\n5. Jugar carta\n6. Tomar carta\n7. Pasar turno\n8. Salir");
            try {
                int opcion = vista.leerEntero("Opcion: ");
                switch (opcion) {
                    case 1: vista.mostrarCartas(juego, false); break;
                    case 2:
                        int tipo = vista.leerEntero("1. Buscar por ID, 2. Buscar por nombre: ");
                        if (tipo == 1) vista.mostrarCarta(juego.buscar(vista.leerEntero("ID: ")));
                        else if (tipo == 2) vista.mostrarCarta(juego.buscar(vista.leerTexto("Nombre completo: ")));
                        else vista.mostrar("Tipo de busqueda invalido.");
                        break;
                    case 3: juego.ordenarPorEnergia(); vista.mostrar("Catalogo ordenado."); break;
                    case 4: vista.mostrarCartas(juego, true); break;
                    case 5: vista.mostrar(juego.jugar(vista.leerEntero("ID de la carta: "))); break;
                    case 6: vista.mostrar(juego.tomarCarta()); break;
                    case 7: juego.pasarTurno(); break;
                    case 8: salir = true; break;
                    default: vista.mostrar("Opcion invalida.");
                }
            } catch (IllegalArgumentException e) { vista.mostrar(e.getMessage()); }
        }
        if (juego.haTerminado()) {
            vista.mostrar(juego.getJugador(0).toString());
            vista.mostrar(juego.getJugador(1).toString());
            vista.mostrar(juego.resultado());
        } else vista.mostrar("Partida interrumpida.");
        vista.cerrar();
    }
}
