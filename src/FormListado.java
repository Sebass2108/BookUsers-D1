import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class FormListado extends JFrame {

    // Componentes dibujados en FormListado.form (SIN new)
    private JPanel panelPrincipal;
    private JList<Usuario> lstUsuarios;
    private JButton btnVerDetalle;
    private JButton btnActualizar;
    private JButton btnEliminar;

    private DefaultListModel<Usuario> modelo = new DefaultListModel<>();
    private ArrayList<Usuario> usuarios; // la lista compartida

    public FormListado(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        setTitle("BookUsers - Listado");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lstUsuarios.setModel(modelo);
        lstUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnVerDetalle.addActionListener(e -> verDetalle());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());

        refrescar();
        setSize(520, 380);
        setLocationRelativeTo(null);
    }

    private void refrescar() {
        modelo.clear();
        for (Usuario u : usuarios) modelo.addElement(u);
    }

    private Usuario seleccionado() {
        int indice = lstUsuarios.getSelectedIndex();
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario de la lista.",
                    "BookUsers", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return modelo.getElementAt(indice);
    }

    private void verDetalle() {
        Usuario u = seleccionado();
        ///if (u != null) new FormDetalle(u).setVisible(true);
    }

    private void actualizar() {
        Usuario u = seleccionado();
        if (u == null) return;
        FormRegistro form = new FormRegistro(usuarios, u);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) { refrescar(); }
        });
        form.setVisible(true);
    }

    private void eliminar() {
        Usuario u = seleccionado();
        if (u == null) return;
        int r = JOptionPane.showConfirmDialog(this, "¿Eliminar a " + u.getNombreCompleto() + "?",
                "BookUsers", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            usuarios.remove(u);
            refrescar();
        }
    }
}
