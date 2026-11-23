public class Pixel {
    private int red;
    private int green;
    private int blue;

    public Pixel(int red, int green, int blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public Pixel(Pixel otro) { this(otro.red, otro.green, otro.blue); }
    public int getRed() { return red; }
    public int getGreen() { return green; }
    public int getBlue() { return blue; }
    public void setRed(int red) { this.red = limitar(red); }
    public void setGreen(int green) { this.green = limitar(green); }
    public void setBlue(int blue) { this.blue = limitar(blue); }
    private int limitar(int valor) { return Math.max(0, Math.min(255, valor)); }
    public double promedio() { return (red + green + blue) / 3.0; }
}
