import java.util.ArrayList;

public class Nave {
    private String nombre;
    private String codigo;
    private String comandante;
    private Modulo[] modulos;
    private ArrayList<Planeta> planetas;

    public Nave(String nombre, String codigo, String comandante) {
        if (nombre.trim().isEmpty() || codigo.trim().isEmpty() || comandante.trim().isEmpty()) {
            throw new IllegalArgumentException("Complete la informacion de la nave.");
        }
        this.nombre = nombre.trim();
        this.codigo = codigo.trim();
        this.comandante = comandante.trim();
        modulos = new Modulo[5];
        planetas = new ArrayList<Planeta>();
    }

    private void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= modulos.length) {
            throw new IllegalArgumentException("La posicion debe estar entre 0 y 4.");
        }
    }

    public void instalarModulo(int posicion, Modulo modulo) {
        validarPosicion(posicion);
        if (modulos[posicion] != null) throw new IllegalArgumentException("La posicion esta ocupada.");
        if (modulo == null) throw new IllegalArgumentException("Modulo invalido.");
        modulos[posicion] = modulo;
    }

    public Modulo consultarModulo(int posicion) {
        validarPosicion(posicion);
        if (modulos[posicion] == null) throw new IllegalArgumentException("La posicion esta vacia.");
        return modulos[posicion];
    }

    public String consultarModulos() {
        String texto = "";
        for (int i = 0; i < modulos.length; i++) {
            if (modulos[i] != null) texto += "Posicion " + i + ": " + modulos[i] + "\n";
        }
        return texto.isEmpty() ? "No hay modulos instalados." : texto;
    }

    public void modificarModulo(int posicion, double consumo, String estado) {
        consultarModulo(posicion).actualizar(consumo, estado);
    }

    public void retirarModulo(int posicion) {
        consultarModulo(posicion);
        modulos[posicion] = null;
    }

    public Planeta buscarPlaneta(String codigo) {
        for (Planeta planeta : planetas) {
            if (planeta.getCodigo().equalsIgnoreCase(codigo.trim())) return planeta;
        }
        return null;
    }

    public void registrarPlaneta(Planeta planeta) {
        if (planeta == null) throw new IllegalArgumentException("Planeta invalido.");
        if (buscarPlaneta(planeta.getCodigo()) != null) throw new IllegalArgumentException("El codigo ya existe.");
        planetas.add(planeta);
    }

    public String consultarPlanetas() {
        if (planetas.isEmpty()) return "No hay planetas registrados.";
        String texto = "";
        for (Planeta planeta : planetas) texto += planeta + "\n";
        return texto;
    }

    public void modificarPlaneta(String codigo, String nombre, double distancia,
            double temperatura, double habitabilidad) {
        Planeta planeta = buscarPlaneta(codigo);
        if (planeta == null) throw new IllegalArgumentException("No se encontro el planeta.");
        // El codigo es su identificador y no cambia al editar los demas datos.
        planeta.actualizar(nombre, distancia, temperatura, habitabilidad);
    }

    public void eliminarPlaneta(String codigo) {
        Planeta planeta = buscarPlaneta(codigo);
        if (planeta == null) throw new IllegalArgumentException("No se encontro el planeta.");
        planetas.remove(planeta);
    }

    public String reporte() {
        int cantidad = 0;
        Modulo mayorConsumo = null;
        for (Modulo modulo : modulos) {
            if (modulo != null) {
                cantidad++;
                if (mayorConsumo == null || modulo.getConsumo() > mayorConsumo.getConsumo()) mayorConsumo = modulo;
            }
        }
        String texto = "Nave: " + nombre + " (" + codigo + "), comandante: " + comandante
                + "\nModulos instalados: " + cantidad + "\nEspacios disponibles: " + (modulos.length-cantidad)
                + "\nMayor consumo: " + (mayorConsumo == null ? "Sin modulos" : mayorConsumo)
                + "\nPlanetas descubiertos: " + planetas.size();
        if (planetas.isEmpty()) return texto + "\nSin planetas para calcular habitabilidad.";
        Planeta mayor = planetas.get(0);
        Planeta menor = planetas.get(0);
        double suma = 0;
        for (Planeta planeta : planetas) {
            if (planeta.getHabitabilidad() > mayor.getHabitabilidad()) mayor = planeta;
            if (planeta.getHabitabilidad() < menor.getHabitabilidad()) menor = planeta;
            suma += planeta.getHabitabilidad();
        }
        return texto + "\nMayor habitabilidad: " + mayor + "\nMenor habitabilidad: " + menor
                + "\nPromedio de habitabilidad: " + suma/planetas.size();
    }
}
