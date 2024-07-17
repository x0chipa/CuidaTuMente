package test;

import datos.PreguntaDaoJDBC;
import datos.RespuestaDaoJDBC;
import datos.ResultadoDaoJDBC;
import datos.SesionDaoJDBC;
import dominio.Pregunta;
import dominio.Respuesta;
import dominio.Resultado;
import dominio.Sesion;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DassMainTest {
    public static void main(String[] args) {
        SesionDaoJDBC sesionDao = new SesionDaoJDBC();
        PreguntaDaoJDBC preguntaDao = new PreguntaDaoJDBC();
        RespuestaDaoJDBC respuestaDao = new RespuestaDaoJDBC();
        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();

        // Crear nueva sesión
        Sesion nuevaSesion = new Sesion();
        long currentTimeMillis = System.currentTimeMillis();
        Date fecha = new Date(currentTimeMillis);
        Time hora = new Time(currentTimeMillis);
        nuevaSesion.setFecha(fecha);
        nuevaSesion.setHora(hora);
        int idSesion = sesionDao.insertar(nuevaSesion);
        nuevaSesion.setId(idSesion);
        System.out.println("Nueva sesión creada: " + nuevaSesion);

        // Listar preguntas para DASS
        List<Pregunta> preguntasDass = preguntaDao.listarPorInstrumentoId(1); // Asumiendo que 1 es el ID para DASS
        System.out.println("Preguntas DASS: ");
        for (Pregunta pregunta : preguntasDass) {
            System.out.println(pregunta);
        }

        // Responder preguntas
        List<Respuesta> respuestas = new ArrayList<>();
        for (int i = 0; i < preguntasDass.size(); i++) {
            Respuesta respuesta = new Respuesta();
            respuesta.setSesionId(idSesion);
            respuesta.setPreguntaId(preguntasDass.get(i).getIdPregunta());
            respuesta.setRespuesta((i % 4) + 1); // Simular respuestas de 1 a 4
            respuestas.add(respuesta);
        }

        // Guardar respuestas
        for (Respuesta respuesta : respuestas) {
            respuestaDao.insertar(respuesta);
            System.out.println("Respuesta guardada: " + respuesta);
        }

        // Calcular resultados
        int estresPuntuacion = 0;
        int ansiedadPuntuacion = 0;
        int depresionPuntuacion = 0;

        for (int i = 0; i < 7; i++) {
            switch (respuestas.get(i).getRespuesta()) {
                case 2:
                    estresPuntuacion++;
                    break;
                case 3:
                    estresPuntuacion += 2;
                    break;
                case 4:
                    estresPuntuacion += 3;
                    break;
            }
        }

        for (int i = 7; i < 14; i++) {
            switch (respuestas.get(i).getRespuesta()) {
                case 2:
                    ansiedadPuntuacion++;
                    break;
                case 3:
                    ansiedadPuntuacion += 2;
                    break;
                case 4:
                    ansiedadPuntuacion += 3;
                    break;
            }
        }

        for (int i = 14; i < 21; i++) {
            switch (respuestas.get(i).getRespuesta()) {
                case 2:
                    depresionPuntuacion++;
                    break;
                case 3:
                    depresionPuntuacion += 2;
                    break;
                case 4:
                    depresionPuntuacion += 3;
                    break;
            }
        }

        String nivelDeEstres = calcularNivelDeEstres(estresPuntuacion);
        String nivelDeAnsiedad = calcularNivelDeAnsiedad(ansiedadPuntuacion);
        String nivelDeDepresion = calcularNivelDeDepresion(depresionPuntuacion);

        Resultado resultado = new Resultado();
        resultado.setSesionId(idSesion);
        resultado.setNivelDeEstres(nivelDeEstres);
        resultado.setEstresPuntuacion(estresPuntuacion);
        resultado.setNivelDeAnsiedad(nivelDeAnsiedad);
        resultado.setAnsiedadPuntuacion(ansiedadPuntuacion);
        resultado.setNivelDeDepresion(nivelDeDepresion);
        resultado.setDepresionPuntuacion(depresionPuntuacion);

        resultadoDao.insertar(resultado);
        System.out.println("Resultado guardado: " + resultado);
    }

    private static String calcularNivelDeEstres(int puntuacion) {
        if (puntuacion <= 7) {
            return "normal";
        } else if (puntuacion <= 9) {
            return "leve";
        } else if (puntuacion <= 12) {
            return "moderado";
        } else if (puntuacion <= 16) {
            return "severo";
        } else {
            return "extremadamente severo";
        }
    }

    private static String calcularNivelDeAnsiedad(int puntuacion) {
        if (puntuacion <= 3) {
            return "normal";
        } else if (puntuacion <= 5) {
            return "leve";
        } else if (puntuacion <= 7) {
            return "moderado";
        } else if (puntuacion <= 9) {
            return "severo";
        } else {
            return "extremadamente severo";
        }
    }

    private static String calcularNivelDeDepresion(int puntuacion) {
        if (puntuacion <= 4) {
            return "normal";
        } else if (puntuacion <= 6) {
            return "leve";
        } else if (puntuacion <= 10) {
            return "moderado";
        } else if (puntuacion <= 13) {
            return "severo";
        } else {
            return "extremadamente severo";
        }
    }
}
