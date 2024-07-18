package dominio;

public class Resultado {
    private int id;
    private int sesionId;
    private String nivelDeEstres;
    private int estresPuntuacion;
    private String nivelDeAnsiedad;
    private int ansiedadPuntuacion;
    private String nivelDeDepresion;
    private int depresionPuntuacion;

    public Resultado() {
    }

    // Constructor para SISCO
    public Resultado(int id, int sesionId, String nivelDeEstres, int estresPuntuacion) {
        this.id = id;
        this.sesionId = sesionId;
        this.nivelDeEstres = nivelDeEstres;
        this.estresPuntuacion = estresPuntuacion;
    }

    // Constructor para DASS
    public Resultado(int id, int sesionId, String nivelDeEstres, int estresPuntuacion, String nivelDeAnsiedad, int ansiedadPuntuacion, String nivelDeDepresion, int depresionPuntuacion) {
        this.id = id;
        this.sesionId = sesionId;
        this.nivelDeEstres = nivelDeEstres;
        this.estresPuntuacion = estresPuntuacion;
        this.nivelDeAnsiedad = nivelDeAnsiedad;
        this.ansiedadPuntuacion = ansiedadPuntuacion;
        this.nivelDeDepresion = nivelDeDepresion;
        this.depresionPuntuacion = depresionPuntuacion;
    }
    
    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSesionId() {
        return sesionId;
    }

    public void setSesionId(int sesionId) {
        this.sesionId = sesionId;
    }

    public String getNivelDeEstres() {
        return nivelDeEstres;
    }

    public void setNivelDeEstres(String nivelDeEstres) {
        this.nivelDeEstres = nivelDeEstres;
    }

    public int getEstresPuntuacion() {
        return estresPuntuacion;
    }

    public void setEstresPuntuacion(int estresPuntuacion) {
        this.estresPuntuacion = estresPuntuacion;
    }

    public String getNivelDeAnsiedad() {
        return nivelDeAnsiedad;
    }

    public void setNivelDeAnsiedad(String nivelDeAnsiedad) {
        this.nivelDeAnsiedad = nivelDeAnsiedad;
    }

    public int getAnsiedadPuntuacion() {
        return ansiedadPuntuacion;
    }

    public void setAnsiedadPuntuacion(int ansiedadPuntuacion) {
        this.ansiedadPuntuacion = ansiedadPuntuacion;
    }

    public String getNivelDeDepresion() {
        return nivelDeDepresion;
    }

    public void setNivelDeDepresion(String nivelDeDepresion) {
        this.nivelDeDepresion = nivelDeDepresion;
    }

    public int getDepresionPuntuacion() {
        return depresionPuntuacion;
    }

    public void setDepresionPuntuacion(int depresionPuntuacion) {
        this.depresionPuntuacion = depresionPuntuacion;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "id=" + id +
                ", sesionId=" + sesionId +
                ", nivelDeEstres='" + nivelDeEstres + '\'' +
                ", estresPuntuacion=" + estresPuntuacion +
                ", nivelDeAnsiedad='" + nivelDeAnsiedad + '\'' +
                ", ansiedadPuntuacion=" + ansiedadPuntuacion +
                ", nivelDeDepresion='" + nivelDeDepresion + '\'' +
                ", depresionPuntuacion=" + depresionPuntuacion +
                '}';
    }
}
