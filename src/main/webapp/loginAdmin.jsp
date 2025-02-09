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
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/login.css">
    <!--=============== REMIXICONS ===============-->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="shortcut icon" href="img/icon.png" type="image/x-icon">
</head>
<style>
    
</style>
<body>
    <div class="wrapper">
        <!--Barra lateral"/-->
        <jsp:include page="/WEB-INF/paginas/comunes/barraLateral.jsp"/>
        <div class="main p-3">
            <div class="text-center">
                <div class="login">
                    <form action="ServletAdminLogin" method="post" class="login__form">
                        <h1 class="login__title">Administrador</h1>
                        <% if (request.getAttribute("error") != null) { %>
                            <p style="color:red;"><%= request.getAttribute("error") %></p>
                        <% } %>
                        <div class="login__content">
                            <div class="login__box">
                                <i class="ri-user-3-line login__icon"></i>
                                <div class="login__box-input">
                                    <input type="text" name="usuario" required class="login__input" id="login-admin" placeholder=" ">
                                    <label for="login-admin" class="login__label">Usuario</label>
                                </div>
                            </div>
                            <div class="login__box">
                                <i class="ri-lock-2-line login__icon"></i>
                                <div class="login__box-input">
                                    <input type="password" name="password" required class="login__input" id="login-pass" placeholder=" ">
                                    <label for="login-pass" class="login__label">Contraseņa</label>
                                    <i id="login-eye"></i>
                                </div>
                            </div>
                        </div>
                        <div class="login__check">
                            <div class="login__check-group">
                                <input type="checkbox" class="login__check-input" id="login-check">
                                <label for="login-check" class="login__check-label">Recordar Contraseņa</label>
                            </div>
                        </div>
                        <button type="submit" class="login__button">Entrar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"></script>
    <script>
        // JavaScript para mostrar y ocultar la contraseņa
        document.getElementById('login-eye').addEventListener('click', function() {
            const passwordInput = document.getElementById('login-pass');
            const eyeIcon = document.getElementById('login-eye');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                eyeIcon.classList.remove('ri-eye-off-line');
                eyeIcon.classList.add('ri-eye-line');
            } else {
                passwordInput.type = 'password';
                eyeIcon.classList.remove('ri-eye-line');
                eyeIcon.classList.add('ri-eye-off-line');
            }
        });
    </script>
    <script src="js/script.js"></script>
</body>
</html>
