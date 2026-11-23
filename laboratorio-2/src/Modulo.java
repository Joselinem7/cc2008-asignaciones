public class Modulo {
    private String codigo;
    private String nombre;
    private String tipo;
    private double consumo;
    private String estado;

    public Modulo(String codigo, String nombre, String tipo, double consumo, String estado) {
        if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Complete codigo, nombre y tipo.");
        }
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.tipo = tipo.trim();
        actualizar(consumo, estado);
    }

    public void actualizar(double consumo, String estado) {
        if (!Double.isFinite(consumo) || consumo <= 0) {
            throw new IllegalArgumentException("El consumo debe ser mayor que cero.");
        }
        if (estado.trim().isEmpty()) throw new IllegalArgumentException("Indique el estado.");
        this.consumo = consumo;
        this.estado = estado.trim();
    }

    public double getConsumo() { return consumo; }
    @Override
    public String toString() {
        return codigo + " - " + nombre + " | Tipo: " + tipo + " | Consumo: " + consumo + " | Estado: " + estado;
    }
}
