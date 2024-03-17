package PaymentGateaway.Service;

import PaymentGateaway.Model.Model_PG;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServicePG {

    private final Connection con;

    public ServicePG() throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.connectToDatabase();
        con = databaseConnection.getConnection();
    }

}
