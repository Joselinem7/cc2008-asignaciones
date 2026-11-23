public class Comprador {
    private String nombre;
    private String email;
    private int cantidad;
    private double presupuesto;

    public Comprador(String nombre, String email, int cantidad, double presupuesto) {
        if (nombre.trim().isEmpty() || !email.contains("@") || cantidad <= 0
                || !Double.isFinite(presupuesto) || presupuesto < 0) {
            throw new IllegalArgumentException("Revise los datos del comprador.");
        }
        this.nombre = nombre;
        this.email = email;
        this.cantidad = cantidad;
        this.presupuesto = presupuesto;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public int getCantidad() { return cantidad; }
    public double getPresupuesto() { return presupuesto; }
}
