import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FormMenu extends JFrame {

    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JButton btnListado;
    private JButton btnCreditos;

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public FormMenu() {
        setTitle("BookUsers · Juan Pacheco y Jostin Suarez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelPrincipal = new JPanel(new GridLayout(3, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        btnRegistrar = new JButton("Registrar usuario");
        btnListado = new JButton("Ver listado");
        btnCreditos = new JButton("Créditos");
        panelPrincipal.add(btnRegistrar);
        panelPrincipal.add(btnListado);
        panelPrincipal.add(btnCreditos);
        setContentPane(panelPrincipal);

        btnRegistrar.addActionListener(e -> new FormRegistro(usuarios).setVisible(true));
        btnListado.addActionListener(e -> new FormListado(usuarios).setVisible(true));
        btnCreditos.addActionListener(e -> new FormCreditos().setVisible(true));

        pack();
        setSize(360, 240);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMenu().setVisible(true));
    }
}