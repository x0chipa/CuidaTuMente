package test;

import datos.PreguntaDaoJDBC;
import dominio.Pregunta;
import java.util.List;

public class PreguntaMainTest {
    public static void main(String[] args) {
        PreguntaDaoJDBC preguntaDao = new PreguntaDaoJDBC();

//        // Insertar
//        Pregunta nuevaPregunta = new Pregunta();
//        nuevaPregunta.setInstrumentoId(1);
//        nuevaPregunta.setPregunta("Nueva Pregunta");
//        nuevaPregunta.setRespuesta1("Respuesta 1");
//        nuevaPregunta.setRespuesta2("Respuesta 2");
//        nuevaPregunta.setRespuesta3("Respuesta 3");
//        nuevaPregunta.setRespuesta4("Respuesta 4");
//        nuevaPregunta.setRespuesta5("Respuesta 5");
//        preguntaDao.insertar(nuevaPregunta);
//        System.out.println("Insertar: " + nuevaPregunta);

        // Listar
        List<Pregunta> preguntas = preguntaDao.listar();
        System.out.println("Listar:");
        for (Pregunta pregunta : preguntas) {
            System.out.println(pregunta);
        }

//        // Listar por instumento id
//        List<Pregunta> preguntas = preguntaDao.listarPorInstrumentoId(2);
//        System.out.println("Listar:");
//        for (Pregunta pregunta : preguntas) {
//            System.out.println(pregunta);
//        }

//        // Buscar por ID
//        Pregunta preguntaEncontrada = new Pregunta(1, 0, "", "", "", "", "", "");
//        preguntaEncontrada = preguntaDao.encontrar(preguntaEncontrada);
//        System.out.println("Buscar por ID: " + preguntaEncontrada);
//
//        // Actualizar
//        if (preguntaEncontrada != null) {
//            preguntaEncontrada.setPregunta("Pregunta Actualizada");
//            preguntaDao.actualizar(preguntaEncontrada);
//            System.out.println("Actualizar: " + preguntaEncontrada);
//        }
//
//        // Eliminar
//        if (preguntaEncontrada != null) {
//            preguntaDao.eliminar(preguntaEncontrada);
//            System.out.println("Eliminar: " + preguntaEncontrada);
//        }
    }
}
