package User.JobApply.Service;

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

public class ServiceApplyDesigner {

    private final Connection con;

    public ServiceApplyDesigner() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void sendJobApply(String name, String senderEmail, String instagram, String content, String portfolio, String aboutUs, String aboutYourself) {
        // Konfigurasi properti untuk mengirim email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Host SMTP untuk Gmail
        props.put("mail.smtp.port", "587"); // Port SMTP untuk Gmail
        props.put("mail.smtp.ssl.trust", "*");

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
            message.setSubject("Job Application");

            // Mengatur isi pesan email
            String emailContent = "Hello my name is " + name + "\n\n"
                    + "I have an active email for you to send me the update " + senderEmail + "\n\n"
                    + "My Instagram is " + instagram + "\n\n"
                    + "I have speciality on " + content + "\n\n"
                    + "Here you can see my work on " + portfolio + "\n\n"
                    + "I know this studio from " + aboutUs + "\n\n"
                    + aboutYourself + "\n\n"
                    + "Thank you from " + name;
            message.setText(emailContent);

            // Mengirim pesan email
            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Email has been sent successfully!");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Failed to send email: " + e.getMessage());
        }
    }

}
