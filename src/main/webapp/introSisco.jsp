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
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/styleIntros.css">
        <link rel="shortcut icon" href="img/icon.jpeg" type="image/x-icon">
    </head>
    <body>
        <div class="wrapper">
            <!--Barra lateral"/-->
            <jsp:include page="/WEB-INF/paginas/comunes/barraLateral.jsp"/>
            <div class="main p-3">
                <div class="text-center">
                    <header class="header">
                        <div class="header-content container">
                            <h1>Bienvenido a Relaja tu Mente</h1>
                            <p>El estrés es necesario y esencial para nuestra supervivencia; sin embargo, experimentarlo por varios días y en intensos niveles puede traer consecuencias para la salud física y mental que nadie quiere experimentar. Descubre en este test si sufres de estrés.</p>
                            <h3>No te tomará mucho tiempo</h3>
                            <p>Tardarás menos de <b>3 minutos</b> en responder este cuestionario. Por favor, lee cada pregunta con atención y selecciona la respuesta que más se ajuste a tu caso. No hay respuestas incorrectas, lo importante es que respondas con honestidad. Recuerda que tu resultado es confidencial y no será publicado ni compartido. Ten presente que este no es un cuestionario diagnóstico, es solo una herramienta que te ayudará a identificar si estás sintiéndote <b>expuesto a mucho estrés</b>.</p>                    
                            <!-- Formulario -->                        
                            <form action="${pageContext.request.contextPath}/ServletPreguntas" method="GET">
                                <input type="hidden" name="accion" value="listarPreguntasSisco" />
                                <button type="submit" class="btn-1">Iniciar Test <i class='bx bx-edit'></i><br></button>
                            </form>
                        </div>
                    </header>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"></script>
        <script src="http://localhost/CuidaTuMente/assets/js/juego.js"></script>
        <script src="http://localhost/CuidaTuMente/assets/js/script.js"></script>
    </body>
</html>
