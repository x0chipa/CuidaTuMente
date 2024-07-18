<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Resultado"%>
<%@page import="utilidades.Utilidades"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Admin</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="shortcut icon" href="http://localhost/CuidaTuMente/assets/img/icono.png" type="image/x-icon">
    <style>
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .chart-container {
            max-width: 600px;
            margin: 0 auto;
        }
        .small-chart-container {
            max-width: 300px;
            margin: 0 auto;
        }
        .table-container {
            overflow-x: auto;
        }
        table {
            width: 100%;
        }
        .quiz-container {
            background: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <aside id="sidebar" class="bg-light p-3">
        <!-- Sidebar content here -->
    </aside>
    <div class="main p-3">
        <div class="container">
            <h1 class="text-center mb-4">Dashboard Admin</h1>
            <div class="text-center mb-4">
                <button id="showAll" class="btn btn-primary">Mostrar Todo</button>
                <button id="showSisco" class="btn btn-secondary">Mostrar SISCO</button>
                <button id="showDass" class="btn btn-secondary">Mostrar DASS</button>
            </div>
            <div class="text-center mb-4">
                <label for="numRecords">Mostrar últimos:</label>
                <select id="numRecords" class="form-select d-inline-block w-auto">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="100">100</option>
                </select>
                <button id="applyRecords" class="btn btn-secondary">Aplicar</button>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="quiz-container">
                        <div class="quiz-head">
                            <h2 class="quiz-title text-center mb-4">Resultados de todas las pruebas</h2>
                        </div>
                        <div class="quiz-body">
                            <%
                                List<Resultado> resultados = (List<Resultado>) request.getAttribute("resultados");
                                List<Resultado> resultadosSisco = new ArrayList<Resultado>();
                                List<Resultado> resultadosDass = new ArrayList<Resultado>();

                                if (resultados != null) {
                                    for (Resultado resultado : resultados) {
                                        if ((resultado.getNivelDeAnsiedad() == null || resultado.getNivelDeAnsiedad().equals("null"))
                                                && (resultado.getNivelDeDepresion() == null || resultado.getNivelDeDepresion().equals("null"))) {
                                            resultadosSisco.add(resultado);
                                        } else {
                                            resultadosDass.add(resultado);
                                        }
                                    }
                                }

                                Map<String, Integer> nivelesEstresSisco = new HashMap<String, Integer>();
                                Map<String, Integer> nivelesEstresDass = new HashMap<String, Integer>();
                                Map<String, Integer> nivelesAnsiedad = new HashMap<String, Integer>();
                                Map<String, Integer> nivelesDepresion = new HashMap<String, Integer>();

                                for (Resultado resultado : resultadosSisco) {
                                    nivelesEstresSisco.put(resultado.getNivelDeEstres(),
                                            nivelesEstresSisco.containsKey(resultado.getNivelDeEstres()) ? nivelesEstresSisco.get(resultado.getNivelDeEstres()) + 1 : 1);
                                }

                                for (Resultado resultado : resultadosDass) {
                                    nivelesEstresDass.put(resultado.getNivelDeEstres(),
                                            nivelesEstresDass.containsKey(resultado.getNivelDeEstres()) ? nivelesEstresDass.get(resultado.getNivelDeEstres()) + 1 : 1);
                                    nivelesAnsiedad.put(resultado.getNivelDeAnsiedad(),
                                            nivelesAnsiedad.containsKey(resultado.getNivelDeAnsiedad()) ? nivelesAnsiedad.get(resultado.getNivelDeAnsiedad()) + 1 : 1);
                                    nivelesDepresion.put(resultado.getNivelDeDepresion(),
                                            nivelesDepresion.containsKey(resultado.getNivelDeDepresion()) ? nivelesDepresion.get(resultado.getNivelDeDepresion()) + 1 : 1);
                                }

                                int totalSisco = resultadosSisco.size();
                                int totalDass = resultadosDass.size();
                            %>

                            <div id="siscoSection">
                                <h3>RESULTADOS SISCO</h3>
                                <h4>Estadísticas</h4>
                                <div class="small-chart-container">
                                    <canvas id="siscoChart"></canvas>
                                </div>
                                <p>Total: <%= totalSisco %></p>
                                <div class="table-container mb-4">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nivel de Estrés</th>
                                            <th>Puntuación Estrés</th>
                                            <th>Acciones</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <% int index = 1;
                                           int mostrar = 5; // Default value for display
                                           try {
                                               mostrar = Integer.parseInt(request.getParameter("mostrar"));
                                           } catch (Exception e) {
                                               mostrar = 5; // Default if parsing fails
                                           }
                                           for (Resultado resultado : resultadosSisco.subList(Math.max(resultadosSisco.size() - mostrar, 0), resultadosSisco.size())) { %>
                                        <tr>
                                            <td><%= index++ %></td>
                                            <td><%= resultado.getNivelDeEstres() %></td>
                                            <td><%= resultado.getEstresPuntuacion() %></td>
                                            <td><a href="ServletVerTest?sesionId=<%= resultado.getSesionId() %>" class="btn btn-sm btn-primary">Ver Test</a></td>
                                        </tr>
                                        <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div id="dassSection">
                                <h3>RESULTADOS DASS</h3>
                                <h4>Estadísticas</h4>
                                <div class="row">
                                    <div class="col-md-4 chart-container">
                                        <h5>Estrés</h5>
                                        <canvas id="dassEstresChart"></canvas>
                                    </div>
                                    <div class="col-md-4 chart-container">
                                        <h5>Ansiedad</h5>
                                        <canvas id="dassAnsiedadChart"></canvas>
                                    </div>
                                    <div class="col-md-4 chart-container">
                                        <h5>Depresión</h5>
                                        <canvas id="dassDepresionChart"></canvas>
                                    </div>
                                </div>
                                <p>Total: <%= totalDass %></p>
                                <div class="table-container">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nivel de Estrés</th>
                                            <th>Puntuación Estrés</th>
                                            <th>Nivel de Ansiedad</th>
                                            <th>Puntuación Ansiedad</th>
                                            <th>Nivel de Depresión</th>
                                            <th>Puntuación Depresión</th>
                                            <th>Acciones</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <% index = 1;
                                           for (Resultado resultado : resultadosDass.subList(Math.max(resultadosDass.size() - mostrar, 0), resultadosDass.size())) { %>
                                        <tr>
                                            <td><%= index++ %></td>
                                            <td><%= resultado.getNivelDeEstres() %></td>
                                            <td><%= resultado.getEstresPuntuacion() %></td>
                                            <td><%= resultado.getNivelDeAnsiedad() %></td>
                                            <td><%= resultado.getAnsiedadPuntuacion() %></td>
                                            <td><%= resultado.getNivelDeDepresion() %></td>
                                            <td><%= resultado.getDepresionPuntuacion() %></td>
                                            <td><a href="ServletVerTest?sesionId=<%= resultado.getSesionId() %>" class="btn btn-sm btn-primary">Ver Test</a></td>
                                        </tr>
                                        <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-center mt-4">
                <a href="index.jsp" class="btn btn-primary">Volver al inicio</a>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
crossorigin="anonymous"></script>
<script src="http://localhost/CuidaTuMente/assets/js/script.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Datos para el gráfico de SISCO
        var siscoData = {
            labels: [<% for (Map.Entry<String, Integer> entry : nivelesEstresSisco.entrySet()) {%>"<%= entry.getKey()%>", <% } %>],
            datasets: [{
                data: [<% for (Map.Entry<String, Integer> entry : nivelesEstresSisco.entrySet()) {%><%= entry.getValue()%>, <% } %>],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF']
            }]
        };
        var siscoChart = new Chart(document.getElementById('siscoChart'), {
            type: 'pie',
            data: siscoData,
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });

        // Datos para el gráfico de Estrés DASS
        var dassEstresData = {
            labels: [<% for (Map.Entry<String, Integer> entry : nivelesEstresDass.entrySet()) {%>"<%= entry.getKey()%>", <% } %>],
            datasets: [{
                data: [<% for (Map.Entry<String, Integer> entry : nivelesEstresDass.entrySet()) {%><%= entry.getValue()%>, <% } %>],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF']
            }]
        };
        var dassEstresChart = new Chart(document.getElementById('dassEstresChart'), {
            type: 'pie',
            data: dassEstresData
        });

        // Datos para el gráfico de Ansiedad DASS
        var dassAnsiedadData = {
            labels: [<% for (Map.Entry<String, Integer> entry : nivelesAnsiedad.entrySet()) {%>"<%= entry.getKey()%>", <% } %>],
            datasets: [{
                data: [<% for (Map.Entry<String, Integer> entry : nivelesAnsiedad.entrySet()) {%><%= entry.getValue()%>, <% } %>],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF']
            }]
        };
        var dassAnsiedadChart = new Chart(document.getElementById('dassAnsiedadChart'), {
            type: 'pie',
            data: dassAnsiedadData
        });

        // Datos para el gráfico de Depresión DASS
        var dassDepresionData = {
            labels: [<% for (Map.Entry<String, Integer> entry : nivelesDepresion.entrySet()) {%>"<%= entry.getKey()%>", <% } %>],
            datasets: [{
                data: [<% for (Map.Entry<String, Integer> entry : nivelesDepresion.entrySet()) {%><%= entry.getValue()%>, <% }%>],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF']
            }]
        };
        var dassDepresionChart = new Chart(document.getElementById('dassDepresionChart'), {
            type: 'pie',
            data: dassDepresionData
        });

        // Mostrar secciones según el botón seleccionado
        document.getElementById("showAll").addEventListener("click", function() {
            document.getElementById("siscoSection").style.display = "block";
            document.getElementById("dassSection").style.display = "block";
        });

        document.getElementById("showSisco").addEventListener("click", function() {
            document.getElementById("siscoSection").style.display = "block";
            document.getElementById("dassSection").style.display = "none";
        });

        document.getElementById("showDass").addEventListener("click", function() {
            document.getElementById("siscoSection").style.display = "none";
            document.getElementById("dassSection").style.display = "block";
        });

        // Aplicar filtro de registros a mostrar
        document.getElementById("applyRecords").addEventListener("click", function() {
            var numRecords = document.getElementById("numRecords").value;
            window.location.href = "tuPagina.jsp?mostrar=" + numRecords;
        });

        // Inicialmente mostrar todo
        document.getElementById("showAll").click();
    });
</script>

</body>
</html>
