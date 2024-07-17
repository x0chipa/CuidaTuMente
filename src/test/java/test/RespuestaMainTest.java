package test;

import datos.RespuestaDaoJDBC;
import dominio.Respuesta;
import java.util.List;
import java.util.Scanner;

public class RespuestaMainTest {

    public static void main(String[] args) {
        RespuestaDaoJDBC respuestaDao = new RespuestaDaoJDBC();
        Scanner scanner = new Scanner(System.in);

//        // Insertar
//        System.out.println("Ingrese el id de la sesion existente:");
//        int sesionId = scanner.nextInt();
//
//        System.out.println("Ingrese el id de la pregunta:");
//        int preguntaId = scanner.nextInt();
//
//        System.out.println("Ingrese la respuesta:");
//        int respuestaValor = scanner.nextInt();
//
//        Respuesta nuevaRespuesta = new Respuesta();
//        nuevaRespuesta.setSesionId(sesionId);
//        nuevaRespuesta.setPreguntaId(preguntaId);
//        nuevaRespuesta.setRespuesta(respuestaValor);
//        respuestaDao.insertar(nuevaRespuesta);
//        System.out.println("Insertar: " + nuevaRespuesta);

        // Listar
        List<Respuesta> respuestas = respuestaDao.listar();
        System.out.println("Listar:");
        for (Respuesta respuesta : respuestas) {
            System.out.println(respuesta);
        }

//        // Buscar por ID de respuesta
//        System.out.println("Ingrese el id de la respuesta a buscar:");
//        int idRespuesta = scanner.nextInt();
//        Respuesta respuestaEncontrada = new Respuesta(idRespuesta, 0, 0, 0);
//        respuestaEncontrada = respuestaDao.encontrar(respuestaEncontrada);
//        System.out.println("Buscar por ID de respuesta: " + respuestaEncontrada);
//
//        // Buscar por ID de pregunta
//        System.out.println("Ingrese el id de la pregunta a buscar:");
//        preguntaId = scanner.nextInt();
//        List<Respuesta> respuestasPorPregunta = respuestaDao.encontrarPorPreguntaId(preguntaId);
//        System.out.println("Buscar por ID de pregunta:");
//        for (Respuesta respuesta : respuestasPorPregunta) {
//            System.out.println(respuesta);
//        }
        // Buscar por ID de sesion
        System.out.println("Ingrese el id de la sesion a buscar:");
        int sesionId = scanner.nextInt();
        List<Respuesta> respuestasPorSesion = respuestaDao.encontrarPorSesionId(sesionId);
        System.out.println("Buscar por ID de sesion:");
        for (Respuesta respuesta : respuestasPorSesion) {
            System.out.println(respuesta);
        }
        
        

//        // Buscar por valor de respuesta
//        System.out.println("Ingrese el valor de la respuesta a buscar:");
//        respuestaValor = scanner.nextInt();
//        List<Respuesta> respuestasPorValor = respuestaDao.encontrarPorRespuesta(respuestaValor);
//        System.out.println("Buscar por valor de respuesta:");
//        for (Respuesta respuesta : respuestasPorValor) {
//            System.out.println(respuesta);
//        }
//
//        // Actualizar
//        if (respuestaEncontrada != null) {
//            respuestaEncontrada.setRespuesta(respuestaValor + 1); // Cambia esto según sea necesario
//            respuestaDao.actualizar(respuestaEncontrada);
//            System.out.println("Actualizar: " + respuestaEncontrada);
//        }
//
//        // Eliminar
//        if (respuestaEncontrada != null) {
//            respuestaDao.eliminar(respuestaEncontrada);
//            System.out.println("Eliminar: " + respuestaEncontrada);
//        }
        scanner.close();
    }
}
