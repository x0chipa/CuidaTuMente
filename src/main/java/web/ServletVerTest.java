package web;

import datos.PreguntaDaoJDBC;
import datos.RespuestaDaoJDBC;
import datos.SesionDaoJDBC;
import datos.ResultadoDaoJDBC;
import dominio.Pregunta;
import dominio.Respuesta;
import dominio.Sesion;
import dominio.Resultado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ServletVerTest")
public class ServletVerTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sesionId = Integer.parseInt(request.getParameter("sesionId"));

        // Obtener la sesión por ID
        SesionDaoJDBC sesionDao = new SesionDaoJDBC();
        Sesion sesion = sesionDao.encontrar(new Sesion(sesionId));

        // Obtener todas las respuestas por ID de sesión
        RespuestaDaoJDBC respuestaDao = new RespuestaDaoJDBC();
        List<Respuesta> respuestas = respuestaDao.encontrarPorSesionId(sesionId);

        // Crear un mapa para almacenar preguntas y sus respuestas correspondientes
        Map<Pregunta, String> preguntasRespuestas = new HashMap<>();
        PreguntaDaoJDBC preguntaDao = new PreguntaDaoJDBC();
        for (Respuesta respuesta : respuestas) {
            Pregunta pregunta = preguntaDao.encontrarPorId(respuesta.getPreguntaId());
            String respuestaTexto;
            switch (respuesta.getRespuesta()) {
                case 1: respuestaTexto = pregunta.getRespuesta1(); break;
                case 2: respuestaTexto = pregunta.getRespuesta2(); break;
                case 3: respuestaTexto = pregunta.getRespuesta3(); break;
                case 4: respuestaTexto = pregunta.getRespuesta4(); break;
                case 5: respuestaTexto = pregunta.getRespuesta5(); break;
                default: respuestaTexto = "No respondida"; break;
            }
            preguntasRespuestas.put(pregunta, respuestaTexto);
        }

        // Obtener los resultados por ID de sesión
        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
        List<Resultado> resultados = resultadoDao.encontrarPorSesionId(sesionId);

        // Pasar los datos al JSP
        request.setAttribute("sesion", sesion);
        request.setAttribute("preguntasRespuestas", preguntasRespuestas);
        request.setAttribute("resultados", resultados);

        // Redirigir al JSP
        request.getRequestDispatcher("verTest.jsp").forward(request, response);
    }
}
