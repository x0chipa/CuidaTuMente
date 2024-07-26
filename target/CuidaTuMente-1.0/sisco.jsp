<%@page import="dominio.Pregunta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Relaja tu Mente</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" href="css/styleTest1.css">
        <link rel="shortcut icon" href="img/icon.png" type="image/x-icon">
    </head>
    <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
    :root{
        --light-purple-color: #009688;
        --light-color: #00968707;
        --dark-color: #000;
        --grey-color: #009687a6;
        --transition: all 300ms ease-in-out;
    }
    .flex{
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .wrapper-test{
        background: var(--light-color);
        padding: 1.5rem 4rem 3rem 4rem;
        width: 600px;
        height: 680px;
        border-top-left-radius: 1.5rem;
        border-bottom-right-radius: 1.5rem;
        position: relative;
        box-shadow: 0 4px 6px rgb(0 0 0 / 10%), 0 1px 3px rgb(0 0 0 / 10%);
    }
    
    .main {
        min-height: 100vh;
        width: 100%;
        overflow: hidden;
        transition: all 0.35s ease-in-out;
        background-color: #F3F3F3;
    }
    .quiz-title{
        text-align: center;
        font-size: 2rem;
    }
    .quiz-score{
        text-align: right;
        font-weight: 600;
        font-size: 1.2rem;
        margin-bottom: 1rem;
        border: 5px solid var(--grey-color);
        font-weight: bold;
        width: 100px;
        height: 50px;
        margin: .5rem auto 1rem auto;
        color: var(--light-purple-color);
    }
    .quiz-question{
        font-size: 1.2rem;
        text-align: center;
        line-height: 1.3;
        margin-bottom: 2rem;
    }
    .quiz-question .category{
        font-size: 0.9rem;
        font-weight: 500;
        background-color: var(--light-purple-color);
        color: var(--light-color);
        padding: .2rem .4rem;
        border-radius: .2rem;
        margin-top: 0.5rem;
        display: inline-block;
    }
    .quiz-options{
        list-style-type: none;
        margin: 1rem 0;
    }
    .quiz-options li{
        border-radius: 0.5rem;
        font-weight: 600;
        margin: .7rem 0;
        padding: .4rem 1.2rem;
        cursor: pointer;
        border: 3px solid var(--light-purple-color);
        background-color: var(--light-purple-color);
        color: var(--light-color);
        box-shadow: 0 4px 0 0 #6c4298;
        transition: var(--transition);
    }
    .quiz-options li:hover{
        background-color: var(--grey-color);
        color: var(--dark-color);
        border-color: var(--grey-color);
        box-shadow: 0 4px 0 0 #bbbbbb;
    }
    .quiz-options li:active{
        transform: scale(0.97);
    }
    /* js related */
    .selected{
        background-color: var(--grey-color)!important;
        color: var(--dark-color)!important;
        border-color: var(--grey-color)!important;
        box-shadow: 0 4px 0 0 #bbbbbb!important;
    }
    .quiz-foot button{
        border: none;
        border-radius: 0.5rem;
        outline: 0;
        font-family: 'Poppins', sans-serif;
        font-size: 1.2rem;
        font-weight: 600;
        padding: .5rem 1rem;    
        margin: 0 auto 0 auto;
        cursor: pointer;
        display: block;
        background-color: var(--grey-color);
        color: var(--dark-color);
        letter-spacing: 2px;
        transition: var(--transition);
        box-shadow: 0 4px 0 0 #bbbbbb;
    }
    .quiz-foot button:hover{
        background-color: #009687;
        box-shadow: 0 4px 0 0 #a7a7a7;
        color: var(--dark-color);
    }
    .quiz-foot button:active{
        transform: scale(0.95);
    }
    #play-again{
        display: none;
    }
    #result{
        padding: .7rem 0;
        text-align: center;
        font-weight: 600;
        font-size: 1.3rem;
    }
    #result i{
        width: 30px;
        height: 30px;
        border-radius: 50%;
        line-height: 30px;
        margin-right: .5rem;
        margin-bottom: .5rem;
        background-color: var(--light-purple-color);
        color: var(--light-color);
    }

    @media(max-width: 678px){
        .quiz-title{
            font-size: 1.6rem;
        }
        .wrapper{
            margin: 3rem 0;
            width: 90%;
            height: 90%;
            padding: 1.5rem 1rem 3rem 1rem;
            border-top-left-radius: 0;
            border-bottom-right-radius: 0;
        }
        .quiz-foot button{
            font-size: 1rem;
        }
    }

    .pregunta h6{
        color: #009688;
        margin-bottom: 10px;
        font-size: 23px;
    }








    /**/
    /*juego*/
    .container-juego header .categoria {
        background: linear-gradient(to right, #8deb58, #cef35a);
        color: #000;
        padding: 3px 10px;
        display: inline-block;
    }

    .container-juego header a {
        position: absolute;
        right: 0px;
        text-decoration: none;
        color: #fff;
    }

    .container-juego .info {
        max-width: 600px;
        width: 100%;
        margin: auto;
        text-align: center;
    }

    .container-juego .info .estadoPregunta {
        display: inline-block;
        width: 150px;
        margin: 10px auto;
        text-align: center;
        font-size: 14px;
        display: block;
    }



    .container-juego .info label {
        display: block;
        border: 2px solid #d3d3d3;
        border-radius: 10px;
        display: flex;
        justify-content: space-between;
        padding: 0 20px;
        margin-bottom: 30px;
        text-align: left;
        cursor: pointer;
    }

    .container-juego .opciones .op1 {
        animation: aparecerOp 1s forwards;
        animation-delay: 0.3s;
    }

    .container-juego .opciones .op2 {
        animation: aparecerOp 1s forwards;
        animation-delay: 0.5s;
    }

    .container-juego .opciones .op3 {
        animation: aparecerOp 1s forwards;
        animation-delay: 0.6s;
    }

    .container-juego .opciones .op4 {
        animation: aparecerOp 1s forwards;
        animation-delay: 0.8s;
    }

    .container-juego .opciones .op5 {
        animation: aparecerOp 1s forwards;
        animation-delay: 1s;
    }

    @keyframes aparecerOp {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    }

    .op1,
    .op2,
    .op3,
    .op4,
    .op5 {
        opacity: 0;
    }

    .container-juego .info label input {
        background-color: #3d8af7;
        color: #fff;
        width: 20px;
    }

    .opcionSeleccionada {
        background: linear-gradient(to right, #83A4D4, #83A4D4);
        color: #000;
    }

    .container-juego .info .boton {
        text-align: center;
    }


    </style>
    <body>
        <div class="wrapper">
            <div class="main p-3">
               <div class="text-center mb-4">
               </div>
                <div class="d-flex justify-content-center">
                    <div class="wrapper-test">
                        <div class="quiz-container">
                            <div class="quiz-head">
                                <h1 class="quiz-title">SISCO</h1>
                                <div class="text-center mb-4">
                                    <h5>Test para medir el estrés</h5>
                                </div>
                                <%
                                    // Obtener la lista de preguntas y el índice actual de la sesión
                                    List<Pregunta> listaPreguntas = (List) session.getAttribute("listarPreguntasSisco");
                                    Integer indicePregunta = (Integer) session.getAttribute("indicePregunta");

                                    // Verificar que hay preguntas disponibles y el índice es válido
                                    if (listaPreguntas != null && indicePregunta != null && indicePregunta < listaPreguntas.size()) {
                                        Pregunta preguntaActual = listaPreguntas.get(indicePregunta);
                                %>
                              
                            </div>
                            <div class="quiz-body">
                                <div class="container-juego" id="container-juego">
                                    <div class="info">
                                        <div class="pregunta mb-4">
                                            <h6 class="text-capitalize"><%= (indicePregunta + 1) + ". " + preguntaActual.getPregunta() %></h6>
                                        </div>
                                        <!-- Formulario para enviar la respuesta -->
                                        <form method="POST" action="${pageContext.request.contextPath}/ServletPreguntas">
                                            <div class="opciones">
                                                <%
                                                    if (preguntaActual.getRespuesta1() != null) {
                                                %>
                                                <label for="respuesta1" class="op1">
                                                    <p class="text-capitalize"><%= preguntaActual.getRespuesta1() %></p>
                                                    <input type="radio" name="respuesta" value="A" id="respuesta1" required>
                                                </label>
                                                <%
                                                    }
                                                    if (preguntaActual.getRespuesta2() != null) {
                                                %>
                                                <label for="respuesta2" class="op2">
                                                    <p class="text-capitalize"><%= preguntaActual.getRespuesta2() %></p>
                                                    <input type="radio" name="respuesta" value="B" id="respuesta2" required>
                                                </label>
                                                <%
                                                    }
                                                    if (preguntaActual.getRespuesta3() != null) {
                                                %>
                                                <label for="respuesta3" class="op3">
                                                    <p class="text-capitalize"><%= preguntaActual.getRespuesta3() %></p>
                                                    <input type="radio" name="respuesta" value="C" id="respuesta3" required>
                                                </label>
                                                <%
                                                    }
                                                    if (preguntaActual.getRespuesta4() != null) {
                                                %>
                                                <label for="respuesta4" class="op4">
                                                    <p class="text-capitalize"><%= preguntaActual.getRespuesta4() %></p>
                                                    <input type="radio" name="respuesta" value="D" id="respuesta4" required>
                                                </label>
                                                <%
                                                    }
                                                    if (preguntaActual.getRespuesta5() != null) {
                                                %>
                                                <label for="respuesta5" class="op5">
                                                    <p class="text-capitalize"><%= preguntaActual.getRespuesta5() %></p>
                                                    <input type="radio" name="respuesta" value="E" id="respuesta5" required>
                                                </label>
                                                <%
                                                    }
                                                %>
                                            </div>
                                            <input type="hidden" name="accion" value="siguientePregunta"/>
                                            <div class="quiz-foot text-center mt-4">
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src="js/juego.js"></script>
    </body>
</html>
