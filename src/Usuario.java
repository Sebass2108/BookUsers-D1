import java.text.Normalizer;

public class Usuario {

    private static int contadorId = 0;

    private int id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String username;
    private String password;
    private String telefono;
    private String correo;
    private String rutaFoto;

    public Usuario(String tipoIdentificacion, String numeroIdentificacion,
                   String primerNombre, String segundoNombre,
                   String primerApellido, String segundoApellido,
                   String password, String telefono, String correo, String rutaFoto) {
        this.id = ++contadorId;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.correo = correo;
        this.rutaFoto = rutaFoto;
        this.username = generarUsername(primerNombre, primerApellido, segundoApellido);
    }

    public static String generarUsername(String primerNombre, String primerApellido, String segundoApellido) {
        String a1 = primerApellido.trim().isEmpty() ? "" : primerApellido.trim().substring(0, 1);
        String a2 = segundoApellido.trim().isEmpty() ? "" : segundoApellido.trim().substring(0, 1);
        String texto = a1 + a2 + primerNombre.trim();
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD)   // separa letra y tilde (ñ = n + ~)
                .replaceAll("\\p{M}", "")                          // quita las tildes y la virgulilla
                .toLowerCase()
                .replace(" ", "");
        return texto;
    }

    public int getId() { return id; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getPrimerNombre() { return primerNombre; }
    public String getSegundoNombre() { return segundoNombre; }
    public String getPrimerApellido() { return primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getRutaFoto() { return rutaFoto; }

    public void setTipoIdentificacion(String v) { this.tipoIdentificacion = v; }
    public void setNumeroIdentificacion(String v) { this.numeroIdentificacion = v; }
    public void setPrimerNombre(String v) { this.primerNombre = v; }
    public void setSegundoNombre(String v) { this.segundoNombre = v; }
    public void setPrimerApellido(String v) { this.primerApellido = v; }
    public void setSegundoApellido(String v) { this.segundoApellido = v; }
    public void setUsername(String v) { this.username = v; }
    public void setPassword(String v) { this.password = v; }
    public void setTelefono(String v) { this.telefono = v; }
    public void setCorreo(String v) { this.correo = v; }
    public void setRutaFoto(String v) { this.rutaFoto = v; }

    public String getNombreCompleto() {
        String s = primerNombre;
        if (!segundoNombre.trim().isEmpty()) s += " " + segundoNombre;
        s += " " + primerApellido;
        if (!segundoApellido.trim().isEmpty()) s += " " + segundoApellido;
        return s;
    }

    @Override
    public String toString() {
        return id + " | " + username + " | " + getNombreCompleto();
    }
}