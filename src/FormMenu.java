import javax.swing.*;
import java.util.ArrayList;

public class FormMenu extends JFrame {

    // Componentes dibujados en FormMenu.form (SIN new: los crea el diseñador)
    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JButton btnListado;
    private JButton btnCreditos;

    // La única lista de toda la aplicación
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public FormMenu() {
        setTitle("BookUsers · Nombre Apellido y Nombre Apellido"); // TODO: pon sus nombres
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ///btnRegistrar.addActionListener(e -> new FormRegistro(usuarios).setVisible(true));
        ///btnListado.addActionListener(e -> new FormListado(usuarios).setVisible(true));
        ///btnCreditos.addActionListener(e -> new FormCreditos().setVisible(true));

        setSize(360, 240);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMenu().setVisible(true));
    }
}
