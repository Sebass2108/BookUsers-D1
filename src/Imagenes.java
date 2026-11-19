import javax.swing.ImageIcon;
import java.awt.Image;

/** Utilidad para cargar una imagen del disco y escalarla a tamaño fijo. */
public class Imagenes {

    public static final int TAM = 120;

    /** Devuelve el icono escalado, o null si la ruta está vacía o el archivo no existe. */
    public static ImageIcon cargar(String ruta, int ancho, int alto) {
        if (ruta == null || ruta.trim().isEmpty()) return null;
        ImageIcon original = new ImageIcon(ruta);
        if (original.getIconWidth() <= 0) return null;
        Image escalada = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(escalada);
    }
}