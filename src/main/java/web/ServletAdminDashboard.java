package web;

import datos.ResultadoDaoJDBC;
import dominio.Resultado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletAdminDashboard")
public class ServletAdminDashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
        List<Resultado> resultados = resultadoDao.listar();

        request.setAttribute("resultados", resultados);
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}
