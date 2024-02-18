package LoginRegister.Service;

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

public class ServiceForgetPassword {

    private final Connection con;

    public ServiceForgetPassword() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void sendPassword(String recipientEmail, String password) {
        final String senderEmail = "flystudio79@gmail.com"; // Ganti dengan alamat email Anda
        final String senderPassword = "zqoltmgplenslzcr"; // Ganti dengan kata sandi email Anda

        // Konfigurasi properti untuk mengirim email melalui SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Sesuaikan dengan SMTP server Anda (contoh: smtp.gmail.com)
        props.put("mail.smtp.port", "587"); // Sesuaikan port SMTP

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your Account Password");
            message.setText("""
                            Dear User,
                            
                            Your account password is : """ + password + "\n\n"
                    + "Best regards,\n"
                    + "Your Application Team");

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Password sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendPasswordEmail(String recipientEmail, String password) {
        sendPassword(recipientEmail, password);
        return true;
    }

    public String getPasswordByEmail(String recipientEmail) {
        String queryUser = "SELECT password FROM user WHERE email = ? AND status = 'Verified' LIMIT 1";
        String queryAdmin = "SELECT password FROM admin WHERE email = ? LIMIT 1";
        String passwordFromDatabase = null;

        try {
            // Cek di tabel user
            passwordFromDatabase = getPasswordFromTable(queryUser, recipientEmail);

            // Jika tidak ada di tabel user, cek di tabel admin
            if (passwordFromDatabase == null) {
                passwordFromDatabase = getPasswordFromTable(queryAdmin, recipientEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passwordFromDatabase;
    }

    private String getPasswordFromTable(String query, String email) throws SQLException {
        String passwordFromDatabase = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                passwordFromDatabase = resultSet.getString("password");
            }
        }

        return passwordFromDatabase;
    }
}
