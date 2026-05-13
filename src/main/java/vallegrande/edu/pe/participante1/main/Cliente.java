package vallegrande.edu.pe.model;

public class Cliente {
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String correo;
    private String telefono;
    private String pais;

    public Cliente() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}