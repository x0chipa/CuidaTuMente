package dominio;

public class Instrumento {
    private int idInstrumento;
    private String nombre;

    public Instrumento() {
    }

    public Instrumento(int idInstrumento, String nombre) {
        this.idInstrumento = idInstrumento;
        this.nombre = nombre;
    }

    public int getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(int idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Instrumento{" +
                "idInstrumento=" + idInstrumento +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
