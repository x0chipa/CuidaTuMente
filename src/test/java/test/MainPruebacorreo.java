package test;

import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;

public class MainPruebacorreo {
    public static void main(String[] args) {
        final String username = "xochipa24.david@outlook.com"; // Cambia esto a tu dirección de correo de Outlook
        final String password = "rqkpihfprhhowggn"; // Cambia esto a tu contraseña de Outlook
        // final String password = "Holakhace24."; // Cambia esto a tu contraseña de Outlook

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // Servidor SMTP de Outlook
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Scanner scanner = new Scanner(System.in);
        String to, subject, body;

        while (true) {
            System.out.println("Ingrese el correo electrónico del destinatario (o 'finalizar' para salir): ");
            to = scanner.nextLine();
            if (to.equalsIgnoreCase("finalizar")) {
                break;
            }

            System.out.println("Ingrese el asunto del correo: ");
            subject = scanner.nextLine();

            System.out.println("Ingrese el cuerpo del correo: ");
            body = scanner.nextLine();

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);

                System.out.println("Correo enviado exitosamente a " + to);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }
}
