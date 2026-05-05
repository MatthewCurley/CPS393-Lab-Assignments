public class TryForEver implements DoNotGiveUp<String> {

    @Override
    public String execute() {

        for (int i = 1; i <= 1000; i++) {

            double value = Math.random();

            System.out.println("Attempt " + i + ": " + value);

            if (value < 0.4) {
                return "You succeeded";
            }
        }

        return "Failed";
    }
}