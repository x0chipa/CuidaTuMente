package web;

import datos.PreguntaDaoJDBC;
import datos.RespuestaDaoJDBC;
import datos.ResultadoDaoJDBC;
import datos.SesionDaoJDBC;
import dominio.Pregunta;
import dominio.Respuesta;
import dominio.Resultado;
import dominio.Sesion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletPreguntas")
public class ServletPreguntas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "listarPreguntasSisco":
                    this.mostrarPreguntas(request, response, 2, "listarPreguntasSisco", "sisco.jsp");
                    break;
                case "listarPreguntasDass":
                    this.mostrarPreguntas(request, response, 1, "listarPreguntasDass", "dass.jsp");
                    break;
                case "siguientePregunta":
                    this.siguientePregunta(request, response);
                    break;
                case "guardarRespuestas":
                    this.guardarRespuestas(request, response);
                    break;
                case "mostrarResultados":
                    this.mostrarResultados(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionError(request, response);
        }
    }

    private void mostrarPreguntas(HttpServletRequest request, HttpServletResponse response, int instrumentoId, String atributoPreguntas, String jsp)
            throws ServletException, IOException {
        PreguntaDaoJDBC preguntaDao = new PreguntaDaoJDBC();
        List<Pregunta> preguntas = preguntaDao.listarPorInstrumentoId(instrumentoId);

        if (preguntas != null && !preguntas.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute(atributoPreguntas, preguntas);
            session.setAttribute("indicePregunta", 0);
            session.setAttribute("atributoPreguntas", atributoPreguntas);

            // Crear una nueva sesión
            SesionDaoJDBC sesionDao = new SesionDaoJDBC();
            Sesion nuevaSesion = new Sesion();
            nuevaSesion.setFecha(new java.sql.Date(System.currentTimeMillis()));
            nuevaSesion.setHora(new java.sql.Time(System.currentTimeMillis()));
            int idSesion = sesionDao.insertar(nuevaSesion);
            nuevaSesion.setId(idSesion);
            session.setAttribute("nuevaSesion", nuevaSesion);

            request.getRequestDispatcher(jsp).forward(request, response);
        } else {
            request.setAttribute("error", "No se encontraron preguntas");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void siguientePregunta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String atributoPreguntas = (String) session.getAttribute("atributoPreguntas");
        List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute(atributoPreguntas);
        Integer indicePregunta = (Integer) session.getAttribute("indicePregunta");

        if (preguntas != null && indicePregunta != null) {
            // Obtener la respuesta seleccionada
            String respuesta = request.getParameter("respuesta");
            if (respuesta != null) {
                // Guardar la respuesta en una lista en la sesión
                List<Respuesta> respuestas = (List<Respuesta>) session.getAttribute("respuestas");
                if (respuestas == null) {
                    respuestas = new java.util.ArrayList<>();
                    session.setAttribute("respuestas", respuestas);
                }

                Respuesta nuevaRespuesta = new Respuesta();
                Sesion nuevaSesion = (Sesion) session.getAttribute("nuevaSesion");
                nuevaRespuesta.setSesionId(nuevaSesion.getId());
                nuevaRespuesta.setPreguntaId(preguntas.get(indicePregunta).getIdPregunta());
                nuevaRespuesta.setRespuesta(respuesta.charAt(0) - 'A' + 1);
                respuestas.add(nuevaRespuesta);
            }

            indicePregunta++;
            if (indicePregunta >= preguntas.size()) {
                // Guardar respuestas en la base de datos y calcular resultados
                guardarRespuestas(request, response);
                calcularResultados(request, response);

                // Redirigir a resultados.jsp con el sesionId
                int sesionId = ((Sesion) session.getAttribute("nuevaSesion")).getId();
                response.sendRedirect(request.getContextPath() + "/resultados.jsp?sesionId=" + sesionId);
                return;
            }
            session.setAttribute("indicePregunta", indicePregunta);

            // Determinar el JSP basado en el atributo de preguntas
            String jsp = atributoPreguntas.equals("listarPreguntasSisco") ? "sisco.jsp" : "dass.jsp";
            request.getRequestDispatcher(jsp).forward(request, response);
        } else {
            request.setAttribute("error", "No se encontraron preguntas");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void guardarRespuestas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Respuesta> respuestas = (List<Respuesta>) session.getAttribute("respuestas");

        if (respuestas != null) {
            RespuestaDaoJDBC respuestaDao = new RespuestaDaoJDBC();
            for (Respuesta respuesta : respuestas) {
                respuestaDao.insertar(respuesta);
            }
        }
    }

    private void calcularResultados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Respuesta> respuestas = (List<Respuesta>) session.getAttribute("respuestas");

        if (respuestas != null) {
            String atributoPreguntas = (String) session.getAttribute("atributoPreguntas");
            if ("listarPreguntasSisco".equals(atributoPreguntas)) {
                calcularResultadosSisco(request, response, respuestas);
            } else if ("listarPreguntasDass".equals(atributoPreguntas)) {
                calcularResultadosDass(request, response, respuestas);
            }
        }
    }

    private void calcularResultadosSisco(HttpServletRequest request, HttpServletResponse response, List<Respuesta> respuestas)
            throws ServletException, IOException {
        int puntuacionTotal = 0;

        for (Respuesta respuesta : respuestas) {
            switch (respuesta.getRespuesta()) {
                case 1:
                    puntuacionTotal += 1;
                    break;
                case 2:
                    puntuacionTotal += 2;
                    break;
                case 3:
                    puntuacionTotal += 3;
                    break;
                case 4:
                    puntuacionTotal += 4;
                    break;
                case 5:
                    puntuacionTotal += 5;
                    break;
            }
        }

        String nivelDeEstres;
        if (puntuacionTotal <= 70) {
            nivelDeEstres = "bajo";
        } else if (puntuacionTotal <= 110) {
            nivelDeEstres = "medio";
        } else {
            nivelDeEstres = "alto";
        }

        Resultado resultado = new Resultado();
        Sesion nuevaSesion = (Sesion) request.getSession().getAttribute("nuevaSesion");
        resultado.setSesionId(nuevaSesion.getId());
        resultado.setNivelDeEstres(nivelDeEstres);
        resultado.setEstresPuntuacion(puntuacionTotal);

        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
        resultadoDao.insertar(resultado);

        request.setAttribute("resultado", resultado);
        request.setAttribute("sesionId", nuevaSesion.getId());
    }

    private void calcularResultadosDass(HttpServletRequest request, HttpServletResponse response, List<Respuesta> respuestas)
            throws ServletException, IOException {
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
        Sesion nuevaSesion = (Sesion) request.getSession().getAttribute("nuevaSesion");
        resultado.setSesionId(nuevaSesion.getId());
        resultado.setNivelDeEstres(nivelDeEstres);
        resultado.setEstresPuntuacion(estresPuntuacion);
        resultado.setNivelDeAnsiedad(nivelDeAnsiedad);
        resultado.setAnsiedadPuntuacion(ansiedadPuntuacion);
        resultado.setNivelDeDepresion(nivelDeDepresion);
        resultado.setDepresionPuntuacion(depresionPuntuacion);

        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
        resultadoDao.insertar(resultado);

        request.setAttribute("resultado", resultado);
        request.setAttribute("sesionId", nuevaSesion.getId());
    }

    private String calcularNivelDeEstres(int puntuacion) {
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

    private String calcularNivelDeAnsiedad(int puntuacion) {
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

    private String calcularNivelDeDepresion(int puntuacion) {
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

    private void mostrarResultados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sesionId = Integer.parseInt(request.getParameter("sesionId"));
        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
        Resultado resultado = resultadoDao.encontrarPorSesionId(sesionId).get(0);

        request.setAttribute("resultado", resultado);
        request.getRequestDispatcher("resultados.jsp").forward(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    private void accionError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("error.jsp");
    }
}
