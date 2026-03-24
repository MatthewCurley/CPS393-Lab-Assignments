import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/** Submit to the TA, and he will assign you a grade based on a few selected responses.  */





public class Week10_labs
{
    public static void main(String[] args)
	{
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

		// **Collect elements into a Set

		// A Set automatically removes duplicates, so I used stream to stream the list and collect it into a Set using Collectors.toSet()
		Set<String> fruitSet = fruit.stream().collect(Collectors.toSet());
        System.out.println("Set: " + fruitSet);

        // **Collect the fruit into groups based on their first character

		// I used groupingBy which needs a "key"
        // Here, the key is the first letter → s.charAt(0)
        // The result is a Map<Character, List<String>>
		Map<Character, List<String>> groupByFirstChar = fruit.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println("\nGroup by first character: " + groupByFirstChar);

		// **Group fruit by the length of the name

		//Same idea as above, but now the "key" is length → s.length()
		Map<Integer, List<String>> groupByLength = fruit.stream().collect(Collectors.groupingBy(s -> s.length()));
        System.out.println("\nGroup by length: " + groupByLength);

		// **Collect the fruit that has erry in it

		// I Used filter to keep only elements that match the condition
        // condition = string contains "erry"
		List<String> hasErry = fruit.stream().filter(s -> s.contains("erry")).collect(Collectors.toList());
        System.out.println("\nContains 'erry': " + hasErry);

		// **Create a partition of fruit based on if it contains erry

		// I used partitioningBy which always splits into TWO groups: true and false
		// In this case:
        // true → contains "erry"
        // false → does not contain "erry"
		Map<Boolean, List<String>> partitionErry = fruit.stream().collect(Collectors.partitioningBy(s -> s.contains("erry")));
        System.out.println("\nPartition by 'erry': " + partitionErry);

		// **Collect the fruit that has 5 or less symbols

		// I Used filter again, but now the condition is length <= 5
		List<String> shortFruit = fruit.stream().filter(s -> s.length() <= 5).collect(Collectors.toList());
        System.out.println("\n5 or fewer letters: " + shortFruit);

		// **Find the total number of symbols in all the fruit stored

		// I used mapToInt to convert each string → its length
        // Then sum all lengths
		int totalChars = fruit.stream().mapToInt(s -> s.length()).sum();
        System.out.println("\nTotal characters: " + totalChars);

		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

        // **Partition data based on if >=50

		// I used the same approach as earlier partition → condition is n >= 50
		Map<Boolean, List<Integer>> partition50 = data.stream().collect(Collectors.partitioningBy(n -> n >= 50));
        System.out.println("\nPartition >=50: " + partition50);

		// **Divide data into groups based on the remainder when divided by 7

		// I used groupingBy again, but now the "key" is n % 7
		Map<Integer, List<Integer>> mod7Groups = data.stream().collect(Collectors.groupingBy(n -> n % 7));
        System.out.println("\nGroup by mod 7: " + mod7Groups);

		// **Find the sum of the data

		// I Converted stream to IntStream, then summed all values
		int sum = data.stream().mapToInt(n -> n).sum();
        System.out.println("\nSum: " + sum);

		// **Collect the unique values

		// I used toSet() to collect unique values since it removes duplicates
		Set<Integer> uniqueValues = data.stream().collect(Collectors.toSet());
        System.out.println("\nUnique values: " + uniqueValues);

        // **Compute the cube of each value

		// I used map to transform each number → n^3
		List<Integer> cubes = data.stream().map(n -> n * n * n).collect(Collectors.toList());
        System.out.println("\nCubes: " + cubes);

		// **Find the sum of the cubes of each value

		// I used mapToInt to convert each number → n^3, then summed all cubes
		int sumOfCubes = data.stream().mapToInt(n -> n * n * n).sum();
        System.out.println("\nSum of cubes: " + sumOfCubes);

		// **Increase the value of each element by 5

		// I used map to transform each number → n + 5
		List<Integer> plusFive = data.stream().map(n -> n + 5).collect(Collectors.toList());
        System.out.println("\nEach +5: " + plusFive);

		// **Compute the cube of the even values

		// I used filter to keep only even numbers, then map to transform each → n^3
		List<Integer> evenCubes = data.stream().filter(n -> n % 2 == 0).map(n -> n * n * n).collect(Collectors.toList());
        System.out.println("\nCubes of even numbers: " + evenCubes);
   }
}
