import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 3) throw new IllegalArgumentException("Faltan argumentos.");
            String filtro = args[2].toLowerCase(java.util.Locale.ROOT);
            boolean parametro = filtro.equals("brillo") || filtro.equals("umbral");
            if (args.length > (parametro ? 4 : 3)) throw new IllegalArgumentException("Sobran argumentos.");
            String filtros = "|grises|negativo|rojo|verde|azul|brillo|umbral|espejo|rotar|";
            if (filtro.isEmpty() || filtro.contains("|") || !filtros.contains("|" + filtro + "|")) {
                throw new IllegalArgumentException("Filtro desconocido.");
            }
            int valor = filtro.equals("brillo") ? 30 : 128;
            if (args.length == 4) valor = Integer.parseInt(args[3]);
            if (filtro.equals("umbral") && (valor < 0 || valor > 255)) {
                throw new IllegalArgumentException("El umbral debe estar entre 0 y 255.");
            }
            File entrada = new File(args[0]).getCanonicalFile();
            File salida = new File(args[1]).getCanonicalFile();
            if (!entrada.isFile() || !entrada.canRead()) throw new IOException("No se puede abrir la entrada.");
            if (entrada.equals(salida) || (salida.exists() && Files.isSameFile(entrada.toPath(), salida.toPath()))) {
                throw new IllegalArgumentException("La salida debe ser distinta de la entrada.");
            }
            ImageUtils.formato(salida.getPath());
            Image original = ImageUtils.load(entrada.getPath());
            Image copia = original.copia();
            System.out.println("Ancho: " + original.getWidth() + ", alto: " + original.getHeight()
                    + ", pixeles: " + (long) original.getWidth()*original.getHeight());
            ImageEditor editor = new ImageEditor(copia);
            Image resultado;
            switch (filtro) {
                case "grises": resultado = editor.grayscale(); break;
                case "negativo": resultado = editor.negative(); break;
                case "rojo": resultado = editor.keepOnlyChannel(0); break;
                case "verde": resultado = editor.keepOnlyChannel(1); break;
                case "azul": resultado = editor.keepOnlyChannel(2); break;
                case "brillo": resultado = editor.brightness(valor); break;
                case "umbral": resultado = editor.blackAndWhite(valor); break;
                case "espejo": resultado = editor.mirrorHorizontal(); break;
                default: resultado = editor.rotate90();
            }
            System.out.printf(java.util.Locale.US, "Brillo original: %.2f%nBrillo final: %.2f%n",
                    original.promedioBrillo(), resultado.promedioBrillo());
            int[] claro = resultado.buscarExtremo(true);
            int[] oscuro = resultado.buscarExtremo(false);
            System.out.println("Mas claro: fila " + claro[0] + ", columna " + claro[1]);
            System.out.println("Mas oscuro: fila " + oscuro[0] + ", columna " + oscuro[1] + " (indices desde 0)");
            ImageUtils.save(resultado, salida.getPath());
            System.out.println("Guardado: " + salida + " (" + resultado.getWidth() + " x " + resultado.getHeight() + ")");
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Uso: java -cp bin Main <entrada> <salida> <filtro> [cantidad o limite]");
            System.out.println("Filtros: grises, negativo, rojo, verde, azul, brillo [30], umbral [128], espejo, rotar.");
        }
    }
}
