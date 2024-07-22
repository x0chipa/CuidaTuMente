package utilidades;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnviarCorreo {

    private static final Logger LOGGER = Logger.getLogger(EnviarCorreo.class.getName());

    public static String mandarCorreo(String username, String password, String to, String subject, String body) {
        // Configurar propiedades para el servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        // Crear sesión con autenticación
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear el mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Enviar el mensaje
            Transport.send(message);

            LOGGER.log(Level.INFO, "Correo enviado exitosamente a {0}", to);
            return "Enviado correctamente";
        } catch (AddressException e) {
            LOGGER.log(Level.SEVERE, "Error en la dirección de correo: {0}", e.getMessage());
            return "Fallo: dirección de correo inválida";
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Error enviando el correo: {0}", e.getMessage());
            return "Fallo: error enviando el correo";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", e.getMessage());
            return "Fallo: error inesperado";
        }
    }
}
