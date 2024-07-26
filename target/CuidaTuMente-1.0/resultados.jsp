<%@page import="dominio.Resultado"%>
<%@page import="datos.ResultadoDaoJDBC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Resultados del Test - Relaja tu Mente</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/styleResultados.css">
        <link rel="shortcut icon" href="img/icon.jpeg" type="image/x-icon">
        <style>
            .text-capitalize {
                text-transform: capitalize;
            }
            .main {
                min-height: 100vh;
                width: 100%;
                overflow: hidden;
                transition: all 0.35s ease-in-out;
                background-color: #F3F3F3;
            }
            .card-body{
                background-color: #00968707;
            }
        </style>
    </head>
    <body>
        <div class="wrapper d-flex">
            <!--Barra lateral"/-->
            <jsp:include page="/WEB-INF/paginas/comunes/barraLateral.jsp"/>
            <div class="main p-3 flex-grow-1">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="text-center mb-4">
                                <h1>Resultados del Test</h1>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title text-center mb-4"></h5>
                                    <%
                                        int sesionId = Integer.parseInt(request.getParameter("sesionId"));
                                        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
                                        Resultado resultado = resultadoDao.encontrarPorSesionId(sesionId).get(0);
                                    %>
                                    <div class="quiz-score">
                                        <% if (resultado.getNivelDeEstres() != null) {%>
                                        <div class="mb-3">
                                            <h6 class="fw-bold text-capitalize">Nivel de Estrés:</h6>
                                            <p class="mb-1 text-capitalize"><%= resultado.getNivelDeEstres()%></p>
                                            <p class="text-capitalize">Puntuación Total de Estrés: <%= resultado.getEstresPuntuacion()%></p>
                                        </div>
                                        <% } %>
                                        <% if (resultado.getNivelDeAnsiedad() != null) {%>
                                        <div class="mb-3">
                                            <h6 class="fw-bold text-capitalize">Nivel de Ansiedad:</h6>
                                            <p class="mb-1 text-capitalize"><%= resultado.getNivelDeAnsiedad()%></p>
                                            <p class="text-capitalize">Puntuación Total de Ansiedad: <%= resultado.getAnsiedadPuntuacion()%></p>
                                        </div>
                                        <% } %>
                                        <% if (resultado.getNivelDeDepresion() != null) {%>
                                        <div class="mb-3">
                                            <h6 class="fw-bold text-capitalize">Nivel de Depresión:</h6>
                                            <p class="mb-1 text-capitalize"><%= resultado.getNivelDeDepresion()%></p>
                                            <p class="text-capitalize">Puntuación Total de Depresión: <%= resultado.getDepresionPuntuacion()%></p>
                                        </div>
                                        <% }%>
                                    </div>
                                    <div class="text-center mt-4">
                                        <h5>¿Deseas que te enviemos tus resultados por correo electrónico?</h5>
                                        <form action="ServletEnviarResultados" method="post">
                                            <input type="hidden" name="sesionId" value="<%= sesionId %>">
                                            <div class="mb-3">
                                                <label for="email" class="form-label">Correo Electrónico:</label>
                                                <input type="email" class="form-control" id="email" name="email" required>
                                            </div>
                                            <button type="submit" class="btn btn-success">Enviar</button>
                                            <a href="index.jsp" class="btn btn-info">No, gracias</a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center mt-4">
                                <a href="index.jsp" class="btn btn-success">Volver al inicio</a>
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
