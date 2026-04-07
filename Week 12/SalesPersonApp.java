import java.sql.*;
import java.util.ArrayList;

/**
 * This program connects to the MariaDB database,
 * retrieves aggregated salesperson data, stores it
 * in objects, and processes it using Java Streams.
 */
public class SalesPersonApp {

    public static void main(String[] args) {

        // Ensure correct number of command-line arguments
        if (args.length != 3) {
            System.out.println("Usage: java SalesPersonApp <username> <password> <database>");
            return;
        }

        // Extract command-line arguments
        String user = args[0];
        String password = args[1];
        String database = args[2];

        // JDBC connection URL (points to local MariaDB instance)
        String url = "jdbc:mariadb://localhost:3306/" + database;

        // List to store SalesPerson objects created from query results
        ArrayList<SalesPerson> salesPersonList = new ArrayList<>();

        try {
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            /**
             * SQL query explanation:
             * - Joins salesman and orders tables
             * - Groups results by salesman
             * - Calculates total sales per salesman using SUM()
             */
            String query = "SELECT s.name, s.city, s.commission, " +
                           "SUM(o.purchase_amt) AS total_sales " +
                           "FROM salesman s " +
                           "JOIN orders o ON s.salesman_id = o.salesman_id " +
                           "GROUP BY s.salesman_id, s.name, s.city, s.commission";

            // Create statement and execute query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            /**
             * Process each row of the result set:
             * - Extract column values
             * - Create a SalesPerson object
             * - Add it to the ArrayList
             */
            while (rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                double commission = rs.getDouble("commission");
                double totalSales = rs.getDouble("total_sales");

                salesPersonList.add(new SalesPerson(name, city, commission, totalSales));
            }

            // =========================
            //     STREAM OPERATIONS
            // =========================

            /**
             * Print table of total earnings (total sales)
             * Using stream + forEach
             */
            System.out.println("\n=== Total Earnings Table ===");
            System.out.printf("%-15s %-15s\n", "Name", "Total Earnings");

            salesPersonList.stream()
                    .forEach(sp ->
                            System.out.printf("%-15s $%-15.2f\n",
                                    sp.name,
                                    sp.totalSales)
                    );

            /**
             * Print table of total commissions
             * Commission = totalSales * commission rate
             */
            System.out.println("\n=== Total Commission Table ===");
            System.out.printf("%-15s %-15s\n", "Name", "Total Commission");

            salesPersonList.stream()
                    .forEach(sp ->
                            System.out.printf("%-15s $%-15.2f\n",
                                    sp.name,
                                    sp.totalSales * sp.commission)
                    );

            // Close all database resources to prevent memory leaks
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            // Print stack trace if any error occurs
            e.printStackTrace();
        }
    }
}