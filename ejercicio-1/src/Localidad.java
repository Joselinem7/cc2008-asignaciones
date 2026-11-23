public class Localidad {
    private int numero;
    private double precio;
    private int capacidad;
    private int vendidos;

    public Localidad(int numero, double precio) {
        this(numero, precio, 20);
    }

    public Localidad(int numero, double precio, int capacidad) {
        if (numero <= 0 || !Double.isFinite(precio) || precio <= 0 || capacidad <= 0) {
            throw new IllegalArgumentException("Datos de localidad invalidos.");
        }
        this.numero = numero;
        this.precio = precio;
        this.capacidad = capacidad;
        vendidos = 0;
    }

    public int getNumero() { return numero; }
    public double getPrecio() { return precio; }
    public int getVendidos() { return vendidos; }
    public int getDisponibles() { return capacidad - vendidos; }
    public double getRecaudacion() { return vendidos * precio; }

    public int vender(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva.");
        int venta = Math.min(cantidad, getDisponibles());
        vendidos += venta;
        return venta;
    }
}
