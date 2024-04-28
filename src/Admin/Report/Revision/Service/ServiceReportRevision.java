package Admin.Report.Revision.Service;

import Admin.Report.Revision.Model.ModelReportRevision;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ServiceReportRevision {

    // Method untuk mendapatkan koneksi database
    public static Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }
    
    public static ModelReportRevision getRevisionData(String transactionNumber) {
        String query = "SELECT transaction_number, designer_name, product_name, level, revision, revision_count, revision_date "
                + "FROM revision "
                + "WHERE transaction_number = ?";
        
        ModelReportRevision reportRevision = null;
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            conn = getConnection();
            p = conn.prepareStatement(query);
            
            p.setString(1, transactionNumber);
            
            r = p.executeQuery();
            
            if (r.next()) {
                String transactionNum = r.getString("transaction_number");
                String designerName = r.getString("designer_name");
                String productName = r.getString("product_name");
                String level = r.getString("level");
                String revision = r.getString("revision");
                String revisionLeft = r.getString("revision_count");
                Timestamp revisionDate = r.getTimestamp("revision_date");
                
                reportRevision = new ModelReportRevision(transactionNum, designerName, productName, level, revision, revisionLeft, revisionDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportRevision;
    }
}
