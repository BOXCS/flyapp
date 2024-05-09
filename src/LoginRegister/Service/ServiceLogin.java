package LoginRegister.Service;

import Admin.AddDesigner.Service.ModelDesignerLogin;
import LoginRegister.Model.ModelLogin;
import LoginRegister.Model.ModelUser;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceLogin {

    private final Connection con;
    private final ServiceForgetPassword forgetPassword;

    public ServiceLogin() {
        con = (Connection) DatabaseConnection.getInstance().getConnection();
        forgetPassword = new ServiceForgetPassword();
    }

    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = loginUser(login);

        if (data != null) {
            return data; // Return the regular user data
        } else {
            data = loginAdmin(login);
            if (data == null) {
                data = loginDesigner(login);
            }
            return data;
        }
    }

    private ModelUser loginUser(ModelLogin login) throws SQLException {
        ModelUser data = null;

        try (PreparedStatement p = con.prepareStatement("SELECT userID, username, email FROM `user` WHERE BINARY email=? AND BINARY `password`=? AND `status`='Verified' LIMIT 1")) {
            p.setString(1, login.getEmail());
            p.setString(2, login.getPassword());

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    int userID = r.getInt("userID");
                    String username = r.getString("username");
                    String email = r.getString("email");
                    data = new ModelUser(userID, username, email, "user");
                }
            }
        }

        return data;
    }

    private ModelUser loginAdmin(ModelLogin login) throws SQLException {
        ModelUser data = null;

        try (PreparedStatement p = con.prepareStatement("SELECT adminID, username, email FROM `admin` WHERE BINARY email=? AND BINARY `password`=? LIMIT 1")) {
            p.setString(1, login.getEmail());
            p.setString(2, login.getPassword());

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    int adminID = r.getInt("adminID");
                    String username = r.getString("username");
                    String email = r.getString("email");
                    data = new ModelUser(adminID, username, email, "admin");
                }
            }
        }

        return data;
    }

    private ModelUser loginDesigner(ModelLogin login) throws SQLException {
        ModelUser data = null;

        try (PreparedStatement p = con.prepareStatement("SELECT designer_id, username, email FROM `designer` WHERE BINARY email=? AND BINARY `password`=? LIMIT 1")) {
            p.setString(1, login.getEmail());
            p.setString(2, login.getPassword());

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    int designer_id = r.getInt("designer_id");
                    String username = r.getString("username");
                    String email = r.getString("email");
                    data = new ModelUser(designer_id, username, email, "designer");
                }
            }
        }

        return data;
    }

    public boolean isUserAdmin(ModelUser user) throws SQLException {
        // Mengecek apakah pengguna adalah admin dengan query SQL
        String query = "SELECT COUNT(*) FROM admin WHERE email = ? AND password = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Jika count lebih dari 0, berarti pengguna adalah admin
                }
            }
        }

        return false; // Jika tidak ada hasil dari query, pengguna bukan admin
    }

    public boolean isUserDesigner(ModelUser user) throws SQLException {
        // Mengecek apakah pengguna adalah admin dengan query SQL
        String query = "SELECT COUNT(*) FROM designer WHERE email = ? AND password = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Jika count lebih dari 0, berarti pengguna adalah admin
                }
            }
        }

        return false; // Jika tidak ada hasil dari query, pengguna bukan admin
    }

    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into `user` (username, email, password, verify_code) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        String code = generateVerifyCode();
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, code);
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.next();
        int userID = r.getInt(1);
        r.close();
        p.close();
        user.setUserID(userID);
        user.setVerifyCode(code);
    }
    
    public void insertDesigner(ModelDesignerLogin designer) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into `designer` (username, email, password, instagram, typeContent, Status) values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, designer.getUserName());
        p.setString(2, designer.getEmail());
        p.setString(3, designer.getPassword());
        p.setString(4, designer.getInstagram());
        p.setString(5, designer.getTypeContent());
        p.setString(6, designer.getStatus());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.next();
        int designer_id = r.getInt(1);
        r.close();
        p.close();
        designer.setDesignerID(designer_id);
    }

    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }

    private boolean checkDuplicateCode(String code) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select userID from `user` where verify_code=? limit 1");
        p.setString(1, code);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        try (PreparedStatement p = con.prepareStatement("select userID from `user` where username=? and `status`='Verified' limit 1")) {
            p.setString(1, user);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }
    
    public boolean checkDuplicateDesigner(String designer) throws SQLException {
        boolean duplicate = false;
        try (PreparedStatement p = con.prepareStatement("select designer_id from `designer` where username=? limit 1")) {
            p.setString(1, designer);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select userID from `user` where email=? and `status`='Verified' limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public void doneVerify(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement("update `user` set verify_code='', `status`='Verified' where userID=? limit 1");
        p.setInt(1, userID);
        p.execute();
        p.close();
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        boolean verify = false;
        PreparedStatement p = con.prepareStatement("select userID from `user` where userID=? and verify_code=? limit 1");
        p.setInt(1, userID);
        p.setString(2, code);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            verify = true;
        }
        r.close();
        p.close();
        return verify;
    }

    public boolean sendResetPasswordEmail(String email) throws SQLException {
        PreparedStatement p = con.prepareStatement("select Email, Password from `user` where Email=? and `Status`='Verified' limit 1");
        p.setString(1, email);
        ResultSet r = p.executeQuery();
        boolean validEmail = r.next();
        String userPassword = r.getString("password");
        r.close();
        p.close();

        if (validEmail) {
            forgetPassword.sendPassword(email, userPassword);
            return true;
        } else {
            return false;
        }
    }

    public String getPasswordFromDatabase(String email) throws SQLException {
        // Query untuk mengambil password dari database berdasarkan alamat email yang diberikan
        String query = "SELECT password FROM user WHERE email = ? AND status = 'Verified' LIMIT 1";

        // Variabel untuk menyimpan password dari database
        String passwordFromDatabase = null;

        // Membuat PreparedStatement dan mengeksekusi query
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, email); // Mengatur nilai parameter
            ResultSet resultSet = preparedStatement.executeQuery(); // Mengeksekusi query

            // Jika ada hasil dari query, ambil password
            if (resultSet.next()) {
                passwordFromDatabase = resultSet.getString("password");
            }
        }

        // Kembalikan password dari database
        return passwordFromDatabase;
    }
    
    public boolean isPasswordValid(String password) {
        // Password should have a minimum length of 8 characters
        // and should contain at least one digit and one special character.
        String passwordRegex = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


}
