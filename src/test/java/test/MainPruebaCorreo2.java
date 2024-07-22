/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;


import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;

public class MainPruebaCorreo2 {
    public static void main(String[] args) {
        final String username = "xochipa24.david@outlook.com"; // Cambia esto a tu dirección de correo de Outlook
        final String password = "levcaknmnvnqnotz"; // Cambia esto a tu contraseña de Outlook

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

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce el correo del destinatario (o escribe 'salir' para finalizar):");
            String to = scanner.nextLine();
            if (to.equalsIgnoreCase("salir")) {
                break;
            }

            System.out.println("Introduce el asunto del correo:");
            String subject = scanner.nextLine();

            System.out.println("Introduce el cuerpo del correo:");
            String body = scanner.nextLine();

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
