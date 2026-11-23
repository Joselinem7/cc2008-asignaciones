public class Planeta {
    private String codigo;
    private String nombre;
    private double distancia;
    private double temperatura;
    private double habitabilidad;

    public Planeta(String codigo, String nombre, double distancia, double temperatura, double habitabilidad) {
        if (codigo.trim().isEmpty()) throw new IllegalArgumentException("Indique un codigo.");
        this.codigo = codigo.trim();
        actualizar(nombre, distancia, temperatura, habitabilidad);
    }

    public void actualizar(String nombre, double distancia, double temperatura, double habitabilidad) {
        if (nombre.trim().isEmpty()) throw new IllegalArgumentException("Indique un nombre.");
        if (!Double.isFinite(distancia) || distancia <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor que cero.");
        }
        if (!Double.isFinite(temperatura)) throw new IllegalArgumentException("Temperatura invalida.");
        if (!Double.isFinite(habitabilidad) || habitabilidad < 0 || habitabilidad > 100) {
            throw new IllegalArgumentException("La habitabilidad debe estar entre 0 y 100.");
        }
        this.nombre = nombre.trim();
        this.distancia = distancia;
        this.temperatura = temperatura;
        this.habitabilidad = habitabilidad;
    }

    public String getCodigo() { return codigo; }
    public double getHabitabilidad() { return habitabilidad; }
    @Override
    public String toString() {
        return codigo + " - " + nombre + " | Distancia: " + distancia + " | Temperatura: "
                + temperatura + " | Habitabilidad: " + habitabilidad;
    }
}
