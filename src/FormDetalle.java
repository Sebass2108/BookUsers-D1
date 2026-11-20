import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;

public class FormDetalle extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JTextArea txtInfo;
    private JButton btnGenerarPdf;

    private Usuario usuario;

    public FormDetalle(Usuario usuario) {
        this.usuario = usuario;
        setTitle("BookUsers - Detalle");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblFoto.setPreferredSize(new Dimension(Imagenes.TAM, Imagenes.TAM));
        ImageIcon icono = Imagenes.cargar(usuario.getRutaFoto(), Imagenes.TAM, Imagenes.TAM);
        if (icono != null) {
            lblFoto.setText("");
            lblFoto.setIcon(icono);
        } else {
            lblFoto.setText("Sin foto");
        }

        txtInfo.setText(String.join("\n", lineasInfo()));
        txtInfo.setEditable(false);

        btnGenerarPdf.addActionListener(e -> generarPdf());

        setSize(440, 520);
        setLocationRelativeTo(null);
    }

    private String[] lineasInfo() {
        return new String[]{
                "ID: " + usuario.getId(),
                "Username: " + usuario.getUsername(),
                "Tipo de identificación: " + usuario.getTipoIdentificacion(),
                "Número de identificación: " + usuario.getNumeroIdentificacion(),
                "Primer nombre: " + usuario.getPrimerNombre(),
                "Segundo nombre: " + usuario.getSegundoNombre(),
                "Primer apellido: " + usuario.getPrimerApellido(),
                "Segundo apellido: " + usuario.getSegundoApellido(),
                "Teléfono: " + usuario.getTelefono(),
                "Correo: " + usuario.getCorreo()
        };
    }

    private void generarPdf() {
        JFileChooser selector = new JFileChooser();
        selector.setSelectedFile(new File("usuario_" + usuario.getUsername() + ".pdf"));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File destino = selector.getSelectedFile();
        if (!destino.getName().toLowerCase().endsWith(".pdf")) {
            destino = new File(destino.getAbsolutePath() + ".pdf");
        }

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(destino));
            documento.open();
            documento.add(new Paragraph("BookUsers - Información del usuario"));
            documento.add(new Paragraph(" "));

            try {
                if (usuario.getRutaFoto() != null && !usuario.getRutaFoto().isEmpty()) {
                    Image foto = Image.getInstance(usuario.getRutaFoto());
                    foto.scaleToFit(Imagenes.TAM, Imagenes.TAM);
                    documento.add(foto);
                }
            } catch (Exception ex) {
                documento.add(new Paragraph("(No se pudo cargar la foto)"));
            }

            documento.add(new Paragraph(" "));
            for (String linea : lineasInfo()) {
                documento.add(new Paragraph(linea));
            }
            documento.close();
            JOptionPane.showMessageDialog(this, "PDF generado:\n" + destino.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el PDF: " + ex.getMessage(),
                    "BookUsers", JOptionPane.ERROR_MESSAGE);
        }
    }
}