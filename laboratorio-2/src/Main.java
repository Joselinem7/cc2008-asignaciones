import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);

    private static String texto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine();
    }

    private static int entero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = entrada.nextInt();
                entrada.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.println("Ingrese un numero entero.");
            }
        }
    }

    private static double decimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = entrada.nextDouble();
                entrada.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.println("Ingrese un numero. Use punto para los decimales.");
            }
        }
    }

    private static Nave nuevaNave() {
        return new Nave(texto("Nombre de la nave: "), texto("Codigo: "), texto("Comandante: "));
    }

    public static void main(String[] args) {
        entrada.useLocale(java.util.Locale.US);
        Nave nave = null;
        while (nave == null) {
            try { nave = nuevaNave(); }
            catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
        }
        int opcion = 0;
        try {
            while (opcion != 13) {
                System.out.println("\n1. Nueva nave\n2. Instalar modulo\n3. Consultar modulos"
                        + "\n4. Consultar un modulo\n5. Modificar modulo\n6. Retirar modulo"
                        + "\n7. Registrar planeta\n8. Consultar planetas\n9. Buscar planeta"
                        + "\n10. Modificar planeta\n11. Eliminar planeta\n12. Reporte de mision\n13. Salir");
                opcion = entero("Opcion: ");
                try {
                    int posicion;
                    String codigo;
                    switch (opcion) {
                        case 1: nave = nuevaNave(); break;
                        case 2:
                            posicion = entero("Posicion (0 a 4): ");
                            Modulo modulo = new Modulo(texto("Codigo: "), texto("Nombre: "), texto("Tipo: "),
                                    decimal("Consumo de energia: "), texto("Estado: "));
                            nave.instalarModulo(posicion, modulo);
                            System.out.println("Modulo instalado.");
                            break;
                        case 3: System.out.println(nave.consultarModulos()); break;
                        case 4: System.out.println(nave.consultarModulo(entero("Posicion (0 a 4): "))); break;
                        case 5:
                            posicion = entero("Posicion (0 a 4): ");
                            nave.modificarModulo(posicion, decimal("Nuevo consumo: "), texto("Nuevo estado: "));
                            break;
                        case 6: nave.retirarModulo(entero("Posicion (0 a 4): ")); break;
                        case 7:
                            Planeta planeta = new Planeta(texto("Codigo: "), texto("Nombre: "),
                                    decimal("Distancia desde la nave: "), decimal("Temperatura: "),
                                    decimal("Habitabilidad (0 a 100): "));
                            nave.registrarPlaneta(planeta);
                            System.out.println("Planeta registrado.");
                            break;
                        case 8: System.out.println(nave.consultarPlanetas()); break;
                        case 9:
                            Planeta encontrado = nave.buscarPlaneta(texto("Codigo: "));
                            System.out.println(encontrado == null ? "No se encontro el planeta." : encontrado);
                            break;
                        case 10:
                            codigo = texto("Codigo del planeta: ");
                            nave.modificarPlaneta(codigo, texto("Nuevo nombre: "), decimal("Nueva distancia: "),
                                    decimal("Nueva temperatura: "), decimal("Nueva habitabilidad: "));
                            break;
                        case 11: nave.eliminarPlaneta(texto("Codigo: ")); break;
                        case 12: System.out.println(nave.reporte()); break;
                        case 13: System.out.println("Mision finalizada."); break;
                        default: System.out.println("Opcion invalida.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("No se pudo realizar la operacion: " + e.getMessage());
                }
            }
        } finally {
            // El recurso se cierra tanto al salir normalmente como ante un error inesperado.
            entrada.close();
        }
    }
}
