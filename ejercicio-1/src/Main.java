import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        VentaBoletos venta = new VentaBoletos();
        int opcion = 0;
        while (opcion != 6) {
            System.out.println("\n1. Nuevo comprador\n2. Nueva solicitud de boletos"
                    + "\n3. Disponibilidad total\n4. Disponibilidad individual\n5. Reporte de caja\n6. Salir");
            try {
                opcion = Integer.parseInt(entrada.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre: ");
                        String nombre = entrada.nextLine();
                        System.out.print("Email: ");
                        String email = entrada.nextLine();
                        System.out.print("Cantidad de boletos: ");
                        int cantidad = Integer.parseInt(entrada.nextLine());
                        System.out.print("Presupuesto total en dolares: ");
                        double presupuesto = Double.parseDouble(entrada.nextLine());
                        venta.setComprador(new Comprador(nombre, email, cantidad, presupuesto));
                        System.out.println("Comprador registrado.");
                        break;
                    case 2: System.out.println(venta.solicitar()); break;
                    case 3: System.out.println(venta.disponibilidad()); break;
                    case 4:
                        System.out.print("Numero de localidad: ");
                        Localidad localidad = venta.buscarLocalidad(Integer.parseInt(entrada.nextLine()));
                        System.out.println("Disponibles: " + localidad.getDisponibles());
                        break;
                    case 5: System.out.printf("Total recaudado: $%.2f%n", venta.reporteCaja()); break;
                    case 6: System.out.println("Hasta luego."); break;
                    default: System.out.println("Opcion invalida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Dato invalido: " + e.getMessage());
            }
        }
        entrada.close();
    }
}
