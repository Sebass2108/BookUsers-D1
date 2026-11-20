import javax.swing.*;
import java.awt.Dimension;

public class FormCreditos extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblFoto1;
    private JLabel lblNombre1;
    private JLabel lblCodigo1;
    private JLabel lblCorreo1;
    private JLabel lblFoto2;
    private JLabel lblNombre2;
    private JLabel lblCodigo2;
    private JLabel lblCorreo2;

    public FormCreditos() {
        setTitle("BookUsers - Créditos");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        llenar(lblFoto1, lblNombre1, lblCodigo1, lblCorreo1,
                "Juan Pacheco", "UNP-Z2WJ-43V3", "juan.pachecogom@unipaz.edu.co", "fotos/Sebas.jpg");
        llenar(lblFoto2, lblNombre2, lblCodigo2, lblCorreo2,
                "Jostin Suarez", "Código estudiantil", "correo2@ejemplo.com", "");

        setSize(520, 320);
        setLocationRelativeTo(null);
    }

    private void llenar(JLabel foto, JLabel nombre, JLabel codigo, JLabel correo,
                        String n, String c, String e, String rutaFoto) {
        nombre.setText(n);
        codigo.setText(c);
        correo.setText(e);
        foto.setPreferredSize(new Dimension(Imagenes.TAM, Imagenes.TAM));
        ImageIcon icono = Imagenes.cargar(rutaFoto, Imagenes.TAM, Imagenes.TAM);
        if (icono != null) {
            foto.setText("");
            foto.setIcon(icono);
        } else {
            foto.setText("Sin foto");
        }
    }
}