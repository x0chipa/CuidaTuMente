package web;

import datos.SesionDaoJDBC;
import dominio.Sesion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/ServletSesion")
public class ServletSesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear una nueva sesión en la base de datos
        Sesion nuevaSesion = new Sesion();
        nuevaSesion.setFecha(new Date(System.currentTimeMillis()));
        nuevaSesion.setHora(new Time(System.currentTimeMillis()));

        SesionDaoJDBC sesionDao = new SesionDaoJDBC();
        int idSesion = sesionDao.insertar(nuevaSesion);
        nuevaSesion.setId(idSesion);

        // Guardar el ID de la sesión en la sesión HTTP
        HttpSession session = request.getSession();
        session.setAttribute("sesionId", idSesion);

        // Redirigir al servlet de preguntas
        String testType = request.getParameter("testType");
        String redirectUrl = "ServletPreguntas?accion=listarPreguntas" + testType;
        response.sendRedirect(redirectUrl);
    }
}
