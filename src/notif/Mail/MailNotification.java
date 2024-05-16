package notif.Mail;

import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class MailNotification {

    private final Connection con;

    public MailNotification() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void sendNotification(String recipientEmail, String notificationSubject, String notificationMessage) {
        final String senderEmail = "flystudio79@gmail.com"; // Ganti dengan alamat email pengirim Anda
        final String senderPassword = "zqoltmgplenslzcr"; // Ganti dengan kata sandi email pengirim Anda

        // Konfigurasi properti untuk mengirim email melalui SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Sesuaikan dengan SMTP server Anda (contoh: smtp.gmail.com)
        props.put("mail.smtp.port", "587"); // Sesuaikan port SMTP
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(notificationSubject);
            message.setText(notificationMessage);

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Notification sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Failed to send notification to " + recipientEmail + ". Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
