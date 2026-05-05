import java.util.Random;

public class TryThreeTimes implements DoNotGiveUp<String> {

    @Override
    public String execute() {

        Random rand = new Random();

        for (int i = 1; i <= 3; i++) {

            int number = rand.nextInt(100) + 1;

            System.out.println("Attempt " + i + ": " + number);

            if (number > 50) {
                return "You succeeded";
            }
        }

        return "Failed";
    }
}