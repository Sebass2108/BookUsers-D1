import javax.swing.ImageIcon;
import java.awt.Image;

public class Imagenes {

    public static final int TAM = 120;

    public static ImageIcon cargar(String ruta, int ancho, int alto) {
        if (ruta == null || ruta.trim().isEmpty()) return null;
        ImageIcon original = new ImageIcon(ruta);
        if (original.getIconWidth() <= 0) return null;
        Image escalada = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(escalada);
    }
}