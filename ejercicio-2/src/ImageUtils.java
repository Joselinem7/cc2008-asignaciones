import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Clase completada a partir del repositorio base del curso.
public class ImageUtils {
    public static Image load(String filename) throws IOException {
        BufferedImage img = ImageIO.read(new File(filename));
        if (img == null) throw new IOException("El archivo no contiene una imagen compatible.");
        Pixel[][] pixels = new Pixel[img.getHeight()][img.getWidth()];
        for (int row = 0; row < img.getHeight(); row++) {
            for (int col = 0; col < img.getWidth(); col++) {
                // BufferedImage recibe primero x (columna), luego y (fila).
                int packed = img.getRGB(col, row);
                pixels[row][col] = new Pixel((packed >> 16) & 255, (packed >> 8) & 255, packed & 255);
            }
        }
        return new Image(pixels);
    }

    public static void save(Image image, String filename) throws IOException {
        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {
                Pixel p = image.getPixel(row, col);
                img.setRGB(col, row, (p.getRed() << 16) | (p.getGreen() << 8) | p.getBlue());
            }
        }
        String format = formato(filename);
        // Codificar antes de crear el archivo evita salidas vacias por formato invalido.
        java.io.ByteArrayOutputStream bytes = new java.io.ByteArrayOutputStream();
        if (!ImageIO.write(img, format, bytes)) throw new IOException("Formato de salida no soportado.");
        java.nio.file.Files.write(new File(filename).toPath(), bytes.toByteArray());
    }

    public static String formato(String filename) {
        String name = new File(filename).getName();
        int dot = name.lastIndexOf('.');
        if (dot < 0 || dot == name.length()-1) throw new IllegalArgumentException("Indique una extension de salida.");
        String format = name.substring(dot+1).toLowerCase(java.util.Locale.ROOT);
        if (!ImageIO.getImageWritersByFormatName(format).hasNext()) {
            throw new IllegalArgumentException("Formato de salida no soportado: " + format);
        }
        return format;
    }
}
