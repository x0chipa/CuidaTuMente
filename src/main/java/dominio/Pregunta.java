package dominio;

public class Pregunta {
    private int idPregunta;
    private int instrumentoId;
    private String pregunta;
    private String respuesta1;
    private String respuesta2;
    private String respuesta3;
    private String respuesta4;
    private String respuesta5;

    public Pregunta() {
    }

    public Pregunta(int idPregunta, int instrumentoId, String pregunta, String respuesta1, String respuesta2, String respuesta3, String respuesta4, String respuesta5) {
        this.idPregunta = idPregunta;
        this.instrumentoId = instrumentoId;
        this.pregunta = pregunta;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
        this.respuesta5 = respuesta5;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getInstrumentoId() {
        return instrumentoId;
    }

    public void setInstrumentoId(int instrumentoId) {
        this.instrumentoId = instrumentoId;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public String getRespuesta5() {
        return respuesta5;
    }

    public void setRespuesta5(String respuesta5) {
        this.respuesta5 = respuesta5;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "idPregunta=" + idPregunta +
                ", instrumentoId=" + instrumentoId +
                ", pregunta='" + pregunta + '\'' +
                ", respuesta1='" + respuesta1 + '\'' +
                ", respuesta2='" + respuesta2 + '\'' +
                ", respuesta3='" + respuesta3 + '\'' +
                ", respuesta4='" + respuesta4 + '\'' +
                ", respuesta5='" + respuesta5 + '\'' +
                '}';
    }
}
