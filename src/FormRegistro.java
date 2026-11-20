import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.util.ArrayList;

public class FormRegistro extends JFrame {

    private static final String[] TIPOS = {
            "11 - Registro civil de nacimiento", "12 - Tarjeta de identidad",
            "13 - Cedula de ciudadania", "21 - Tarjeta de extranjeria", "22 - Cedula de extranjeria",
            "41 - Pasaporte", "42 - Documento de identificacion extrabjero", "47 - Permiso Especial de Permanencia (PEP)",
            "91 - NUIP"
    };

    private JPanel panelPrincipal;
    private JComboBox cmbTipoId;
    private JTextField txtNumeroId;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblUsername;
    private JPasswordField txtPassword;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnCargarFoto;
    private JLabel lblFoto;
    private JButton btnGuardar;

    private ArrayList<Usuario> usuarios;
    private Usuario editando = null;
    private String rutaFoto = "";

    public FormRegistro(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        iniciar();
        setTitle("BookUsers - Registrar usuario");
    }

    public FormRegistro(ArrayList<Usuario> usuarios, Usuario editar) {
        this.usuarios = usuarios;
        this.editando = editar;
        iniciar();
        setTitle("BookUsers - Actualizar usuario");
        cargarDatos(editar);
    }

    private void iniciar() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for (String tipo : TIPOS) cmbTipoId.addItem(tipo);

        lblFoto.setPreferredSize(new Dimension(Imagenes.TAM, Imagenes.TAM));
        lblUsername.setText(" ");

        DocumentListener escucha = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarUsername();
            }
        };

        txtPrimerNombre.getDocument().addDocumentListener(escucha);
        txtSegundoNombre.getDocument().addDocumentListener(escucha);
        txtPrimerApellido.getDocument().addDocumentListener(escucha);
        txtSegundoApellido.getDocument().addDocumentListener(escucha);

        btnCargarFoto.addActionListener(e -> cargarFoto());
        btnGuardar.addActionListener(e -> guardar());

        setSize(520, 640);
        setLocationRelativeTo(null);
    }

    private void actualizarUsername() {
        lblUsername.setText(Usuario.generarUsername(
                txtPrimerNombre.getText(), txtPrimerApellido.getText(), txtSegundoApellido.getText()));
    }

    private void cargarFoto() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("imagenes (png, jpg)", "png", "jpg"));
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            rutaFoto = selector.getSelectedFile().getAbsolutePath();
            mostrarFoto();
        }
    }

    private void mostrarFoto() {
        ImageIcon icono = Imagenes.cargar(rutaFoto, Imagenes.TAM, Imagenes.TAM);
        if (icono == null) {
            lblFoto.setIcon(null);
            lblFoto.setText("Sin foto");
        } else {
            lblFoto.setText("");
            lblFoto.setIcon(icono);
        }
    }

    private String codigoTipo() {
        return ((String) cmbTipoId.getSelectedItem()).split(" - ")[0];
    }

    private void guardar() {
        String numero = txtNumeroId.getText().trim();
        String pNombre = txtPrimerNombre.getText().trim();
        String sNombre = txtSegundoNombre.getText().trim();
        String pApellido = txtPrimerApellido.getText().trim();
        String sApellido = txtSegundoApellido.getText().trim();
        String password = new String(txtPassword.getPassword()); // JPasswordField no tiene getText()
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (numero.isEmpty() || pNombre.isEmpty() || pApellido.isEmpty()
                || password.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            {
                JOptionPane.showMessageDialog(this, "Faltan campos obligatorios.", "BookUsers", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

            String codigo = codigoTipo();
            boolean permiteLetras = codigo.equals("41") || codigo.equals("42");
            if (!permiteLetras && !numero.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El número de identificación solo debe tener dígitos.",
                        "BookUsers", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!correo.contains("@")) {
                JOptionPane.showMessageDialog(this, "El correo debe contener @.", "BookUsers", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String tipo = (String) cmbTipoId.getSelectedItem();
            if (editando == null) {
                Usuario u = new Usuario(tipo, numero, pNombre, sNombre, pApellido, sApellido,
                        password, telefono, correo, rutaFoto);
                usuarios.add(u);
                JOptionPane.showMessageDialog(this, "Usuario registrado: " + u.getUsername());
                limpiar();
            } else {
                // Se modifica el MISMO objeto: conserva su id
                editando.setTipoIdentificacion(tipo);
                editando.setNumeroIdentificacion(numero);
                editando.setPrimerNombre(pNombre);
                editando.setSegundoNombre(sNombre);
                editando.setPrimerApellido(pApellido);
                editando.setSegundoApellido(sApellido);
                editando.setPassword(password);
                editando.setTelefono(telefono);
                editando.setCorreo(correo);
                editando.setRutaFoto(rutaFoto);
                editando.setUsername(Usuario.generarUsername(pNombre, pApellido, sApellido));
                JOptionPane.showMessageDialog(this, "Usuario actualizado.");
                dispose();
            }
    }

        private void cargarDatos (Usuario u){
            for (int i = 0; i < TIPOS.length; i++) {
                if (TIPOS[i].equals(u.getTipoIdentificacion())) cmbTipoId.setSelectedIndex(i);
            }
            txtNumeroId.setText(u.getNumeroIdentificacion());
            txtPrimerNombre.setText(u.getPrimerNombre());
            txtSegundoNombre.setText(u.getSegundoNombre());
            txtPrimerApellido.setText(u.getPrimerApellido());
            txtSegundoApellido.setText(u.getSegundoApellido());
            txtPassword.setText(u.getPassword());
            txtTelefono.setText(u.getTelefono());
            txtCorreo.setText(u.getCorreo());
            rutaFoto = u.getRutaFoto();
            mostrarFoto();
            actualizarUsername();
        }


        private void limpiar () {
            cmbTipoId.setSelectedIndex(0);
            txtNumeroId.setText("");
            txtPrimerNombre.setText("");
            txtSegundoNombre.setText("");
            txtPrimerApellido.setText("");
            txtSegundoApellido.setText("");
            txtPassword.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");
            rutaFoto = "";
            mostrarFoto();
            lblUsername.setText(" ");
        }


    }