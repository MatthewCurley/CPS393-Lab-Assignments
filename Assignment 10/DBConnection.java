import java.sql.*;
import java.util.ArrayList;

class Sales {
    int orderNumber;
    String customerName;
    String customerCity;
    String salesmanName;
    double amount;
    double commissionAmount;

    // Constructor
    public Sales(int orderNumber, String customerName, String customerCity,
                 String salesmanName, double amount, double commissionRate) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerCity = customerCity;
        this.salesmanName = salesmanName;
        this.amount = amount;
        this.commissionAmount = amount * commissionRate;
    }

    @Override
    public String toString() {
        return "Order#: " + orderNumber +
               ", Customer: " + customerName +
               ", City: " + customerCity +
               ", Salesman: " + salesmanName +
               ", Amount: " + amount +
               ", Commission: " + commissionAmount;
    }
}

public class DBConnection {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Usage: java DBConnection <username> <password> <database>");
            return;
        }

        String user = args[0];
        String password = args[1];
        String database = args[2];

        String url = "jdbc:mariadb://localhost:3306/" + database;

        ArrayList<Sales> salesList = new ArrayList<>();

        try {
            // Load MariaDB driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // SQL query with JOIN
            String query = "SELECT o.order_no, o.purchase_amt, " +
               "c.customer_name, c.city AS customer_city, " +
               "s.name AS salesman_name, s.commission " +
               "FROM orders o " +
               "JOIN customer c ON o.customer_id = c.customer_id " +
               "JOIN salesman s ON o.salesman_id = s.salesman_id";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process results
            while (rs.next()) {
                int orderNo = rs.getInt("order_no");
                double amount = rs.getDouble("purchase_amt");
                String customerName = rs.getString("customer_name");
                String customerCity = rs.getString("customer_city");
                String salesmanName = rs.getString("salesman_name");
                double commissionRate = rs.getDouble("commission");

                Sales sale = new Sales(orderNo, customerName, customerCity,
                                       salesmanName, amount, commissionRate);

                salesList.add(sale);
            }

            // Print results (for testing)
            for (Sales s : salesList) {
                System.out.println(s);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}