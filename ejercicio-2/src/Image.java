public class Image {
    private Pixel[][] pixels;

    public Image(int height, int width) {
        if (height <= 0 || width <= 0) throw new IllegalArgumentException("Dimensiones invalidas.");
        pixels = new Pixel[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) pixels[row][col] = new Pixel(0, 0, 0);
        }
    }

    public Image(Pixel[][] pixels) {
        this(pixels.length, pixels.length == 0 ? 0 : pixels[0].length);
        for (int row = 0; row < getHeight(); row++) {
            if (pixels[row] == null || pixels[row].length != getWidth()) {
                throw new IllegalArgumentException("La matriz debe ser rectangular.");
            }
            for (int col = 0; col < getWidth(); col++) setPixel(row, col, pixels[row][col]);
        }
    }

    public int getHeight() { return pixels.length; }
    public int getWidth() { return pixels[0].length; }
    private void validarPosicion(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            throw new IllegalArgumentException("Posicion fuera de la imagen.");
        }
    }

    public Pixel getPixel(int row, int col) {
        validarPosicion(row, col);
        return pixels[row][col];
    }

    public void setPixel(int row, int col, Pixel pixel) {
        validarPosicion(row, col);
        if (pixel == null) throw new IllegalArgumentException("El pixel no puede ser null.");
        pixels[row][col] = new Pixel(pixel);
    }

    public Image copia() { return new Image(pixels); }

    public double promedioBrillo() {
        double suma = 0;
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) suma += pixels[row][col].promedio();
        }
        return suma / ((long) getHeight() * getWidth());
    }

    public int[] buscarExtremo(boolean masClaro) {
        int fila = 0;
        int columna = 0;
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                double actual = pixels[row][col].promedio();
                double mejor = pixels[fila][columna].promedio();
                if ((masClaro && actual > mejor) || (!masClaro && actual < mejor)) {
                    fila = row;
                    columna = col;
                }
            }
        }
        return new int[] {fila, columna};
    }
}
