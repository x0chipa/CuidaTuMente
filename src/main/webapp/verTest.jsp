<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="dominio.Pregunta"%>
<%@page import="dominio.Respuesta"%>
<%@page import="dominio.Sesion"%>
<%@page import="dominio.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles del Test</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="shortcut icon" href="http://localhost/CuidaTuMente/assets/img/icono.png" type="image/x-icon">
    <style>
        .container {
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        .table thead th {
            background: #007bff;
            color: #fff;
        }
        .btn-primary {
            background: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background: #0056b3;
        }
        .date-time-card {
            background: #007bff;
            color: #fff;
            padding: 15px;
            border-radius: 10px;
            text-align: center;
            margin-bottom: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .date-time-card p {
            margin: 0;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">Detalles del Test</h1>
    <div class="date-time-card mb-4">
        <% Sesion sesion = (Sesion) request.getAttribute("sesion"); %>
        <p><strong>Fecha:</strong> <%= sesion.getFecha() %></p>
        <p><strong>Hora:</strong> <%= sesion.getHora() %></p>
    </div>
    <div class="card mb-4">
        <div class="card-body">
            <h3 class="card-title">Preguntas y Respuestas</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Pregunta</th>
                    <th>Respuesta Elegida</th>
                </tr>
                </thead>
                <tbody>
                <% 
                    Map<Pregunta, String> preguntasRespuestas = (Map<Pregunta, String>) request.getAttribute("preguntasRespuestas");
                    int index = 1;
                    if (preguntasRespuestas != null) {
                        for (Map.Entry<Pregunta, String> entry : preguntasRespuestas.entrySet()) {
                            Pregunta pregunta = entry.getKey();
                            String respuestaTexto = entry.getValue();
                %>
                <tr>
                    <td><%= index++ %></td>
                    <td><%= pregunta.getPregunta() %></td>
                    <td><%= respuestaTexto %></td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="3">No hay preguntas ni respuestas disponibles</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <div class="card mb-4">
        <div class="card-body">
            <h3 class="card-title">Resultados</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <%-- Solo mostramos los encabezados de las columnas que tienen datos válidos --%>
                    <% boolean mostrarColumnaEstres = false;
                       boolean mostrarColumnaAnsiedad = false;
                       boolean mostrarColumnaDepresion = false;
                       List<Resultado> resultados = (List<Resultado>) request.getAttribute("resultados");
                       if (resultados != null && !resultados.isEmpty()) {
                           for (Resultado resultado : resultados) {
                               if (resultado.getEstresPuntuacion() != 0) mostrarColumnaEstres = true;
                               if (resultado.getAnsiedadPuntuacion() != 0) mostrarColumnaAnsiedad = true;
                               if (resultado.getDepresionPuntuacion() != 0) mostrarColumnaDepresion = true;
                           }
                       }
                       if (mostrarColumnaEstres) { %>
                           <th>Nivel de Estrés</th>
                           <th>Puntuación Estrés</th>
                    <% } if (mostrarColumnaAnsiedad) { %>
                           <th>Nivel de Ansiedad</th>
                           <th>Puntuación Ansiedad</th>
                    <% } if (mostrarColumnaDepresion) { %>
                           <th>Nivel de Depresión</th>
                           <th>Puntuación Depresión</th>
                    <% } %>
                </tr>
                </thead>
                <tbody>
                <% 
                    if (resultados != null && !resultados.isEmpty()) {
                        for (Resultado resultado : resultados) {
                %>
                <tr>
                    <% if (mostrarColumnaEstres) { %>
                        <td><%= resultado.getNivelDeEstres() %></td>
                        <td><%= resultado.getEstresPuntuacion() %></td>
                    <% } if (mostrarColumnaAnsiedad) { %>
                        <td><%= resultado.getNivelDeAnsiedad() %></td>
                        <td><%= resultado.getAnsiedadPuntuacion() %></td>
                    <% } if (mostrarColumnaDepresion) { %>
                        <td><%= resultado.getNivelDeDepresion() %></td>
                        <td><%= resultado.getDepresionPuntuacion() %></td>
                    <% } %>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="6">No hay resultados disponibles</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <div class="text-center">
        <a href="ServletAdminDashboard" class="btn btn-primary">Volver al Dashboard</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
crossorigin="anonymous"></script>
</body>
</html>
