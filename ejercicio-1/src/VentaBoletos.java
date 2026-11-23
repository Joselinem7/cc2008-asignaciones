import java.util.Random;

public class VentaBoletos {
    private Localidad[] localidades;
    private Comprador comprador;
    private Random random;

    public VentaBoletos() {
        localidades = new Localidad[] {
            new Localidad(1, 100), new Localidad(5, 500), new Localidad(10, 1000)
        };
        random = new Random();
    }

    public void setComprador(Comprador comprador) { this.comprador = comprador; }

    public String solicitar() {
        if (comprador == null) return "Primero registre un comprador.";
        int ticket = random.nextInt(15000) + 1;
        int a = random.nextInt(15000) + 1;
        int b = random.nextInt(15000) + 1;
        String resultado = "Ticket: " + ticket + ". Rango: " + Math.min(a, b) + " a " + Math.max(a, b) + ". ";
        if (ticket < Math.min(a, b) || ticket > Math.max(a, b)) {
            return resultado + "El ticket no fue seleccionado.";
        }
        Localidad localidad = localidades[random.nextInt(localidades.length)];
        resultado += "Localidad asignada: " + localidad.getNumero() + ". ";
        if (localidad.getDisponibles() == 0) return resultado + "Ya no tiene espacio.";
        int cantidad = Math.min(comprador.getCantidad(), localidad.getDisponibles());
        // El presupuesto se interpreta como el total disponible para esta compra.
        double total = cantidad * localidad.getPrecio();
        if (total > comprador.getPresupuesto()) {
            return resultado + "El total de $" + total + " supera su presupuesto.";
        }
        localidad.vender(cantidad);
        return resultado + comprador.getNombre() + " compro " + cantidad + " boleto(s). Total: $" + total;
    }

    public Localidad buscarLocalidad(int numero) {
        for (Localidad localidad : localidades) {
            if (localidad.getNumero() == numero) return localidad;
        }
        throw new IllegalArgumentException("Solo existen las localidades 1, 5 y 10.");
    }

    public String disponibilidad() {
        String reporte = "";
        int disponibles = 0;
        for (Localidad localidad : localidades) {
            reporte += "Localidad " + localidad.getNumero() + ": " + localidad.getVendidos()
                    + " vendidos, " + localidad.getDisponibles() + " disponibles.\n";
            disponibles += localidad.getDisponibles();
        }
        return reporte + "Disponibles en total: " + disponibles;
    }

    public double reporteCaja() {
        double total = 0;
        for (Localidad localidad : localidades) total += localidad.getRecaudacion();
        return total;
    }
}
