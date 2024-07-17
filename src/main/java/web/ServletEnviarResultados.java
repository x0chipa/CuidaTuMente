package web;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletEnviarResultados")
public class ServletEnviarResultados extends HttpServlet {

    private final String username = "xochipa24.david@outlook.com"; // Cambia esto a tu dirección de correo de Outlook
    private final String password = "azformnxsmhkbnir"; // Cambia esto a tu contraseña de Outlook

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        String nivelEstres = (String) session.getAttribute("nivelEstres");
        String nivelAnsiedad = (String) session.getAttribute("nivelAnsiedad");
        String nivelDepresion = (String) session.getAttribute("nivelDepresion");

        StringBuilder mensaje = new StringBuilder("Resultados de tu test:\n\n");
        if (nivelEstres != null) {
            mensaje.append("Tu nivel de estrés es: ").append(nivelEstres).append("\n");
        }
        if (nivelAnsiedad != null) {
            mensaje.append("Tu nivel de ansiedad es: ").append(nivelAnsiedad).append("\n");
        }
        if (nivelDepresion != null) {
            mensaje.append("Tu nivel de depresión es: ").append(nivelDepresion).append("\n");
        }

        try {
            sendEmail(email, "Resultados de tu test", mensaje.toString());
            System.out.println("Correo enviado exitosamente a " + email);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void sendEmail(String to, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // Servidor SMTP de Outlook
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
