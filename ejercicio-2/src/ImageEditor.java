public class ImageEditor {
    private Image og;

    public ImageEditor(Image og) {
        if (og == null) throw new IllegalArgumentException("No hay imagen para editar.");
        this.og = og.copia();
    }

    public Image negative() {
        Image result = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                result.setPixel(row, col, new Pixel(255-p.getRed(), 255-p.getGreen(), 255-p.getBlue()));
            }
        }
        return result;
    }

    public Image grayscale() {
        Image result = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                int promedio = (int) og.getPixel(row, col).promedio();
                result.setPixel(row, col, new Pixel(promedio, promedio, promedio));
            }
        }
        return result;
    }

    public Image keepOnlyChannel(int channel) {
        if (channel < 0 || channel > 2) throw new IllegalArgumentException("Canal invalido.");
        Image result = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                int r = channel == 0 ? p.getRed() : 0;
                int g = channel == 1 ? p.getGreen() : 0;
                int b = channel == 2 ? p.getBlue() : 0;
                result.setPixel(row, col, new Pixel(r, g, b));
            }
        }
        return result;
    }

    public Image brightness(int amount) {
        Image result = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                // long evita desbordamientos antes de limitar el canal.
                int r = (int) Math.max(0L, Math.min(255L, (long) p.getRed() + amount));
                int g = (int) Math.max(0L, Math.min(255L, (long) p.getGreen() + amount));
                int b = (int) Math.max(0L, Math.min(255L, (long) p.getBlue() + amount));
                result.setPixel(row, col, new Pixel(r, g, b));
            }
        }
        return result;
    }

    public Image blackAndWhite(int limit) {
        if (limit < 0 || limit > 255) throw new IllegalArgumentException("El umbral debe estar entre 0 y 255.");
        Image result = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                int valor = og.getPixel(row, col).promedio() > limit ? 255 : 0;
                result.setPixel(row, col, new Pixel(valor, valor, valor));
            }
        }
        return result;
    }

    public Image mirrorHorizontal() {
        Image result = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                result.setPixel(row, og.getWidth()-1-col, og.getPixel(row, col));
            }
        }
        return result;
    }

    public Image rotate90() {
        Image result = new Image(og.getWidth(), og.getHeight());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                result.setPixel(col, og.getHeight()-1-row, og.getPixel(row, col));
            }
        }
        return result;
    }
}
