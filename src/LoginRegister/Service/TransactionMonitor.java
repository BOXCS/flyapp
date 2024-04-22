package LoginRegister.Service;

import connection.DatabaseConnection;
import java.sql.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TransactionMonitor {
// Variabel instance untuk menyimpan instance Timer

    private Timer timer;
    private Connection conn;

    public TransactionMonitor(Connection conn) {
        this.conn = conn;
    }

    // Metode untuk memulai pemeriksaan transaksi secara berkala
    public void startMonitoring() {
        // Pastikan timer belum berjalan
        if (timer == null) {
            // Buat instance baru dari Timer
            timer = new Timer();
            // Menjadwalkan tugas untuk menjalankan pemeriksaan transaksi setiap 24 jam
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    checkTransactions(conn);
                }
            }, 0, 24 * 60 * 60 * 1000); // Setiap 24 jam
        }
    }

    // Metode untuk menghentikan pemeriksaan transaksi
    public void stopMonitoring() {
        // Batalkan timer jika timer tidak null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    // Metode untuk memeriksa transaksi yang telah melewati tenggat waktu
    public static void checkTransactions(Connection conn) {
        // Pastikan koneksi sudah disediakan dari luar metode
        try {
            // Query untuk mendapatkan transaksi yang mendekati atau melewati tenggat waktu
            String sql = "SELECT transaction_number, created_at, product_name, level FROM transaction "
                    + "WHERE status != 'Late'";
            PreparedStatement p = conn.prepareStatement(sql);

            ResultSet r = p.executeQuery();

            Date now = new Date();

            while (r.next()) {
                String transactionNumber = r.getString("transaction_number");
                Timestamp createdAt = r.getTimestamp("created_at");
                String productName = r.getString("product_name");
                String level = r.getString("level");

                // Hitung tenggat waktu berdasarkan produk dan level
                long deadline = calculateDeadline(createdAt, productName, level);

                // Jika sekarang telah melewati tenggat waktu
                if (now.getTime() > deadline) {
                    // Perbarui status transaksi menjadi 'Late'
                    updateTransactionStatusToLate(conn, transactionNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk menghitung tenggat waktu berdasarkan produk dan level
    public static long calculateDeadline(Timestamp createdAt, String productName, String level) {
        long deadline = createdAt.getTime();
        int daysToAdd = 0;

        // Hitung jumlah hari yang harus ditambahkan berdasarkan productName dan level
        if (productName.equals("Video Editing")) {
            if (level.equals("Basic")) {
                daysToAdd = 1;
            } else if (level.equals("Standard")) {
                daysToAdd = 2;
            } else if (level.equals("Pro")) {
                daysToAdd = 3;
            }
        } else if (productName.equals("Design Graphic")) {
            if (level.equals("Basic")) {
                daysToAdd = 1;
            } else if (level.equals("Standard")) {
                daysToAdd = 2;
            } else if (level.equals("Pro")) {
                daysToAdd = 3;
            }
        } else if (productName.equals("3D Modelling")) {
            if (level.equals("Basic")) {
                daysToAdd = 2;
            } else if (level.equals("Standard")) {
                daysToAdd = 3;
            } else if (level.equals("Pro")) {
                daysToAdd = 4;
            }
        }

        // Tambahkan jumlah hari yang ditentukan ke deadline
        deadline += daysToAdd * 24 * 60 * 60 * 1000; // Konversi hari ke milidetik

        return deadline;
    }

    // Metode untuk memperbarui status transaksi menjadi 'Late' hanya jika status saat ini 'Active'
    public static void updateTransactionStatusToLate(Connection conn, String transactionNumber) {
        try {
            // Query untuk memperbarui status transaksi menjadi 'Late' hanya jika status saat ini adalah 'Active'
            String sql = "UPDATE transaction SET status = 'Late' WHERE transaction_number = ? AND status = 'Active'";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, transactionNumber);

            // Eksekusi query
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
