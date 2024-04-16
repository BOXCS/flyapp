package User.SendFootage.Service;

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

public class MailFootage {

    private final Connection con;

    public MailFootage() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void sendFootage(String senderEmail, String transactionNumber, String designer, String productName, String level, String footageLink, String information) {
        // Konfigurasi properti untuk mengirim email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Host SMTP untuk Gmail
        props.put("mail.smtp.port", "587"); // Port SMTP untuk Gmail

        // Membuat sesi untuk mengirim email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("flystudio79@gmail.com", "zqoltmgplenslzcr"); // Akun email studio
            }
        });

        try {
            // Membuat objek pesan email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail)); // Alamat email pengirim
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("flystudio79@gmail.com")); // Alamat email studio
            message.setSubject("My Order Footage");

            // Mengatur isi pesan email
            String emailContent = "Hello, I want to send my footage for transaction number: " + transactionNumber + "\n\n"
                    + "I ordered " + productName + " with level " + level + " for designer " + designer + "\n\n"
                    + "This is the link for you to get my footage: " + footageLink + "\n\n"
                    + "And this is the information related to my order: " + information;
            message.setText(emailContent);

            // Mengirim pesan email
            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Email has been sent successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to send email: " + e.getMessage());
        }
    }
}
