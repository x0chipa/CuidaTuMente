<%@page import="dominio.Pregunta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cuida tu Mente</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/styleTest1.css">
        <link rel="shortcut icon" href="mg/icono.png" type="image/x-icon">
    </head>
    <body>
        <div class="wrapper">
            <div class="main p-3">
                <div class="text-center">
                    <h1>Test para medir estrés, ansiedsad y depresión.</h1>
                    <div class="flex">
                        <div class="wrapper-test">
                            <div class="quiz-container">
                                <div class="quiz-head">
                                    <h1 class="quiz-title">Test Dass-21</h1>
                                    <%
                                        // Obtener la lista de preguntas y el índice actual de la sesión
                                        List<Pregunta> listaPreguntas = (List) session.getAttribute("listarPreguntasDass");
                                        Integer indicePregunta = (Integer) session.getAttribute("indicePregunta");

                                        // Verificar que hay preguntas disponibles y el índice es válido
                                        if (listaPreguntas != null && indicePregunta != null && indicePregunta < listaPreguntas.size()) {
                                            Pregunta preguntaActual = listaPreguntas.get(indicePregunta);
                                    %>
                                    <div class="quiz-score flex">
                                        <%= indicePregunta + 1 %>/<span id="total-question"><%= listaPreguntas.size() %></span>
                                    </div>
                                </div>
                                <div class="quiz-body">
                                    <div class="container-juego" id="container-juego">
                                        <div class="info">
                                            <div class="pregunta">
                                                <h6><%= preguntaActual.getPregunta() %></h6>
                                            </div>
                                            <!-- Formulario para enviar la respuesta -->
                                            <form method="post" action="${pageContext.request.contextPath}/ServletPreguntas">
                                                <div class="opciones">
                                                    <%
                                                        if (preguntaActual.getRespuesta1() != null) {
                                                    %>
                                                    <label for="respuesta1" class="op1">
                                                        <p><%= preguntaActual.getRespuesta1() %></p>
                                                        <input type="radio" name="respuesta" value="A" id="respuesta1" required>
                                                    </label>
                                                    <%
                                                        }
                                                        if (preguntaActual.getRespuesta2() != null) {
                                                    %>
                                                    <label for="respuesta2" class="op2">
                                                        <p><%= preguntaActual.getRespuesta2() %></p>
                                                        <input type="radio" name="respuesta" value="B" id="respuesta2" required>
                                                    </label>
                                                    <%
                                                        }
                                                        if (preguntaActual.getRespuesta3() != null) {
                                                    %>
                                                    <label for="respuesta3" class="op3">
                                                        <p><%= preguntaActual.getRespuesta3() %></p>
                                                        <input type="radio" name="respuesta" value="C" id="respuesta3" required>
                                                    </label>
                                                    <%
                                                        }
                                                        if (preguntaActual.getRespuesta4() != null) {
                                                    %>
                                                    <label for="respuesta4" class="op4">
                                                        <p><%= preguntaActual.getRespuesta4() %></p>
                                                        <input type="radio" name="respuesta" value="D" id="respuesta4" required>
                                                    </label>
                                                    <%
                                                        }
                                                        if (preguntaActual.getRespuesta5() != null) {
                                                    %>
                                                    <label for="respuesta5" class="op5">
                                                        <p><%= preguntaActual.getRespuesta5() %></p>
                                                        <input type="radio" name="respuesta" value="E" id="respuesta5" required>
                                                    </label>
                                                    <%
                                                        }
                                                    %>
                                                </div>
                                                <input type="hidden" name="accion" value="siguientePregunta"/>
                                                <div class="quiz-foot">
                                                    <button type="submit" class="btn btn-primary">Siguiente</button>
                                                </div>
                                            </form>
                                            <%
                                                } else {
                                                    out.println("<p>No hay preguntas disponibles.</p>");
                                                }
                                            %>
                                        </div>
                                    </div>
                                    <div id="result"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src="http://localhost/CuidaTuMente/assets/js/juego2.js"></script>
        <script src="http://localhost/CuidaTuMente/assets/js/script.js"></script>
    </body>
</html>
