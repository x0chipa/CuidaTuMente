package dominio;

import java.sql.Date;
import java.sql.Time;

public class Sesion {
    private int id;
    private Date fecha;
    private Time hora;

    public Sesion() {
    }

    public Sesion(int id, Date fecha, Time hora) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Sesion{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                '}';
    }
}
