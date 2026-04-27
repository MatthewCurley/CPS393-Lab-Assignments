import java.sql.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class EmployeeApp {

    public static void main(String[] args) {

        List<Employee> employeesList = new ArrayList<>();

        // 1. JDBC connection (FIXED)
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/employee",
                "matthew",
                "0413")) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, salary FROM employees");

            // Populate list
            while (rs.next()) {
                employeesList.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2. Print all employees
        employeesList.stream()
                .forEach(System.out::println);

        // 3. Predicate: salary > 50000
        Predicate<Employee> highSalary = e -> e.getSalary() > 50000;

        List<Employee> highEarners = employeesList.stream()
                .filter(highSalary)
                .collect(Collectors.toList());

        // 4. Print high earners
        highEarners.stream()
                .forEach(System.out::println);

        // 5. Function to apply 15% tax
        Function<Employee, Employee> applyTax = e ->
                new Employee(e.getId(), e.getName(), e.getSalary() * 0.85);

        // 6. Format salary
        Function<Employee, String> formatSalary = e ->
                String.format("%s earns $%.2f", e.getName(), e.getSalary());

        // 7. Stream pipeline
        List<Employee> taxedHighEarners = employeesList.stream()
                .filter(highSalary)
                .map(applyTax)
                .collect(Collectors.toList());

        taxedHighEarners.stream()
                .map(formatSalary)
                .forEach(System.out::println);

        // 8. EXTRA: Single pipeline with different tax rates
        employeesList.stream()
                .map(e -> {
                    double newSalary = e.getSalary() <= 50000
                            ? e.getSalary() * 0.90
                            : e.getSalary() * 0.85;

                    return new Employee(e.getId(), e.getName(), newSalary);
                })
                .map(e -> String.format("%s -> $%.2f", e.getName(), e.getSalary()))
                .forEach(System.out::println);
    }
}

// Employee class
class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return String.format("Employee{id=%d, name='%s', salary=%.2f}", id, name, salary);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
}