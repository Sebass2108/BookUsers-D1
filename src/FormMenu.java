import javax.swing.*;
import java.util.ArrayList;

public class FormMenu extends JFrame {

    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JButton btnListado;
    private JButton btnCreditos;

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public FormMenu() {
        setTitle("BookUsers · Juan Pacheco y Jostin Suarez");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnRegistrar.addActionListener(e -> new FormRegistro(usuarios).setVisible(true));
        btnListado.addActionListener(e -> new FormListado(usuarios).setVisible(true));
        btnCreditos.addActionListener(e -> new FormCreditos().setVisible(true));

        setSize(360, 240);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMenu().setVisible(true));
    }
}
