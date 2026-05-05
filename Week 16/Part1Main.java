public class Part1Main {

    public static void main(String[] args) {

        TryThreeTimes t1 = new TryThreeTimes();
        TryForEver t2 = new TryForEver();

        System.out.println("=== TryThreeTimes ===");
        System.out.println(t1.execute());

        System.out.println();

        System.out.println("=== TryForEver ===");
        System.out.println(t2.execute());
    }
}