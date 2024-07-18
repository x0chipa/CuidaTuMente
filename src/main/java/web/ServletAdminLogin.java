package web;

import datos.UsuarioDaoJDBC;
import dominio.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletAdminLogin")
public class ServletAdminLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        // Validar las credenciales contra la base de datos
        UsuarioDaoJDBC usuarioDao = new UsuarioDaoJDBC();
        List<Usuario> usuarios = usuarioDao.listar();

        boolean autenticado = false;
        for (Usuario u : usuarios) {
            if (u.getUser().equals(usuario) && u.getPassword().equals(password)) {
                autenticado = true;
                break;
            }
        }

        if (autenticado) {
            // Redirigir a ServletAdminDashboard si la autenticación es exitosa
            response.sendRedirect("ServletAdminDashboard");
        } else {
            // Enviar de vuelta al login con un mensaje de error
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("loginAdmin.jsp").forward(request, response);
        }
    }
}
