public abstract class Carta {
    private int id;
    private String nombre;
    private int costoEnergia;
    private String descripcion;
    private int ubicacion;

    public Carta(int id, String nombre, int costoEnergia, String descripcion) {
        if (id <= 0 || nombre.trim().isEmpty() || costoEnergia < 0) {
            throw new IllegalArgumentException("Datos de carta invalidos.");
        }
        this.id = id;
        this.nombre = nombre;
        this.costoEnergia = costoEnergia;
        this.descripcion = descripcion;
        ubicacion = -1;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCostoEnergia() { return costoEnergia; }
    public int getUbicacion() { return ubicacion; }
    public void setUbicacion(int ubicacion) {
        if (ubicacion < -1 || ubicacion > 2) throw new IllegalArgumentException("Ubicacion invalida.");
        this.ubicacion = ubicacion;
    }

    public abstract String jugarCarta(Jugador actual, Jugador rival);

    @Override
    public String toString() {
        return "ID " + id + " | " + nombre + " | Energia: " + costoEnergia + " | " + descripcion;
    }

    @Override
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Carta)) return false;
        return id == ((Carta) objeto).id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }
}
