package dominio;

public class Respuesta {
    private int idRespuestas;
    private int sesionId;
    private int preguntaId;
    private int respuesta;

    public Respuesta() {}

    public Respuesta(int idRespuestas, int sesionId, int preguntaId, int respuesta) {
        this.idRespuestas = idRespuestas;
        this.sesionId = sesionId;
        this.preguntaId = preguntaId;
        this.respuesta = respuesta;
    }
    
    public Respuesta(int sesionId, int preguntaId, int respuesta) {
        this.sesionId = sesionId;
        this.preguntaId = preguntaId;
        this.respuesta = respuesta;
    }

    // Getters y Setters

    public int getIdRespuestas() {
        return idRespuestas;
    }

    public void setIdRespuestas(int idRespuestas) {
        this.idRespuestas = idRespuestas;
    }

    public int getSesionId() {
        return sesionId;
    }

    public void setSesionId(int sesionId) {
        this.sesionId = sesionId;
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "idRespuestas=" + idRespuestas +
                ", sesionId=" + sesionId +
                ", preguntaId=" + preguntaId +
                ", respuesta=" + respuesta +
                '}';
    }
}
