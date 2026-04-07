/**
 * This class represents a salesperson and stores both
 * their basic information and computed total sales.
 */
public class SalesPerson {

    // Instance variables to hold salesperson data
    String name;        // Salesperson's name
    String city;        // City they work in
    double commission;  // Commission rate (e.g., 0.1 for 10%, 0.15 for 15%, etc.)
    double totalSales;  // Total sales amount for this salesperson

    /**
     * Constructor used to initialize a SalesPerson object
     * with all required data retrieved from the database.
     */
    public SalesPerson(String name, String city, double commission, double totalSales) {
        this.name = name;
        this.city = city;
        this.commission = commission;
        this.totalSales = totalSales;
    }

    /**
     * toString() method provides a nicely formatted string
     * representation of the object.
     */
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-10.2f %-12.2f",
                name, city, commission, totalSales);
    }
}