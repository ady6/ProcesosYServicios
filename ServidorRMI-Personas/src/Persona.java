
public class Persona {
	
	private String codigoPersona;
    private int dni;
    private String nombre;
    private String paterno;
    private String materno;
    
	public Persona() {
	}

	public Persona(String codigoPersona, int dni, String nombre, String paterno, String materno) {
        this.codigoPersona = codigoPersona;
        this.dni = dni;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
    }

    public String getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(String codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    @Override
    public String toString() {
        return "Persona " + codigoPersona + ": dni=" + dni + ", Nombre=" + nombre + " " + paterno + " " + materno ;
    }
    
    
}
