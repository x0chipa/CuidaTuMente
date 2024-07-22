package web;

import datos.UsuarioDaoJDBC;
import dominio.Usuario;
import datos.ResultadoDaoJDBC;
import dominio.Resultado;
import utilidades.EnviarCorreo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletEnviarResultados")
public class ServletEnviarResultados extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        int sesionId = Integer.parseInt(request.getParameter("sesionId"));

        UsuarioDaoJDBC usuarioDao = new UsuarioDaoJDBC();
        Usuario usuario = usuarioDao.encontrarPorId(-1); // Asumiendo que el ID es -1

        final String username = usuario.getUser();
        final String password = usuario.getPassword();

        // Retrieve the results
        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
        List<Resultado> resultados = resultadoDao.encontrarPorSesionId(sesionId);
        Resultado resultado = resultados.get(0);

        // Construct the email body
        StringBuilder body = new StringBuilder();
        body.append("Resultados de tu prueba:\n\n");

        if (resultado.getNivelDeEstres() != null) {
            body.append("Nivel de Estrés: ").append(resultado.getNivelDeEstres()).append("\n");
            body.append("Puntuación de Estrés: ").append(resultado.getEstresPuntuacion()).append("\n\n");
        }

        if (resultado.getNivelDeAnsiedad() != null) {
            body.append("Nivel de Ansiedad: ").append(resultado.getNivelDeAnsiedad()).append("\n");
            body.append("Puntuación de Ansiedad: ").append(resultado.getAnsiedadPuntuacion()).append("\n\n");
        }

        if (resultado.getNivelDeDepresion() != null) {
            body.append("Nivel de Depresión: ").append(resultado.getNivelDeDepresion()).append("\n");
            body.append("Puntuación de Depresión: ").append(resultado.getDepresionPuntuacion()).append("\n\n");
        }

        body.append("Si necesitas ayuda, por favor llama al número: 555-123-4567");

        // Enviar el correo
        String resultadoEnvio = EnviarCorreo.mandarCorreo(username, password, email, "Resultados de tu prueba", body.toString());

        if ("Enviado correctamente".equals(resultadoEnvio)) {
            response.sendRedirect("correoEnviado.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
