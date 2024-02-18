package connection;

public class CekConek {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Success");
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
    
}
