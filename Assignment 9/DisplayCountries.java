import java.sql.*;
import java.util.Scanner;

public class DisplayCountries {

    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/nation";
        String user = "Username";      // enter MariaDB username
        String password = "xxxx";      // enter MariaDB password

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a language: ");
        String language = scanner.nextLine();

        try {
            // Load MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Connect to database
            Connection conn = DriverManager.getConnection(url, user, password);

            String query =
            "SELECT c.name, cl.official " +
            "FROM countries c " +
            "JOIN country_languages cl ON c.country_id = cl.country_id " +
            "JOIN languages l ON cl.language_id = l.language_id " +
            "WHERE l.language = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, language);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nCountries where \"" + language + "\" is spoken:\n");

            boolean found = false;

            while (rs.next()) {
              String country = rs.getString("name");
               int official = rs.getInt("official");

               if (official == 1)
                   System.out.println(country + " (Official)");
              else
                   System.out.println(country + " (Unofficial)");

                  found = true;
                }

            if (!found) {
                System.out.println("No countries found for that language.");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}