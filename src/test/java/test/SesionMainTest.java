package test;

import datos.RespuestaDaoJDBC;
import datos.SesionDaoJDBC;
import dominio.Respuesta;
import dominio.Sesion;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class SesionMainTest {
    public static void main(String[] args) {
        
        
 // Crear una nueva sesión
        SesionDaoJDBC sesionDao = new SesionDaoJDBC();
        Sesion nuevaSesion = new Sesion();
        nuevaSesion.setFecha(new Date(System.currentTimeMillis()));
        nuevaSesion.setHora(new Time(System.currentTimeMillis()));
        int idSesion = sesionDao.insertar(nuevaSesion);
        nuevaSesion.setId(idSesion);
        System.out.println("Nueva sesión creada: " + nuevaSesion);

        // Crear y guardar respuestas
        RespuestaDaoJDBC respuestaDao = new RespuestaDaoJDBC();
        for (int i = 1; i <= 31; i++) {
            Respuesta respuesta = new Respuesta();
            respuesta.setSesionId(idSesion);
            respuesta.setPreguntaId(i);
            respuesta.setRespuesta((int) (Math.random() * 5) + 1); // Respuestas aleatorias entre 1 y 5
            respuestaDao.insertar(respuesta);
            System.out.println("Respuesta guardada: " + respuesta);
        }
        
        
        
//        SesionDaoJDBC sesionDao = new SesionDaoJDBC();
//
//        // Obtener la fecha y hora actual en la zona horaria de Puebla, México
//        ZonedDateTime nowPuebla = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
//        Date fechaActual = Date.valueOf(nowPuebla.toLocalDate());
//        Time horaActual = Time.valueOf(nowPuebla.toLocalTime().withNano(0));
//
//        // Insertar
//        Sesion nuevaSesion = new Sesion();
//        nuevaSesion.setFecha(fechaActual);
//        nuevaSesion.setHora(horaActual);
//        sesionDao.insertar(nuevaSesion);
//        System.out.println("Insertar: " + nuevaSesion);
//
//        // Listar
//        List<Sesion> sesiones = sesionDao.listar();
//        System.out.println("Listar:");
//        for (Sesion sesion : sesiones) {
//            System.out.println(sesion);
//        }
//
//        // Buscar por ID
//        Sesion sesionEncontrada = new Sesion(1, null, null);
//        sesionEncontrada = sesionDao.encontrar(sesionEncontrada);
//        System.out.println("Buscar por ID: " + sesionEncontrada);
//
//        // Actualizar
//        if (sesionEncontrada != null) {
//            ZonedDateTime nuevaHoraPuebla = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
//            sesionEncontrada.setFecha(Date.valueOf(nuevaHoraPuebla.toLocalDate()));
//            sesionEncontrada.setHora(Time.valueOf(nuevaHoraPuebla.toLocalTime().withNano(0)));
//            sesionDao.actualizar(sesionEncontrada);
//            System.out.println("Actualizar: " + sesionEncontrada);
//        }
//
//        // Eliminar
//        if (sesionEncontrada != null) {
//            sesionDao.eliminar(sesionEncontrada);
//            System.out.println("Eliminar: " + sesionEncontrada);
//        }
    }
}
