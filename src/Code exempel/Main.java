import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> myList = List.of("Kalle", "Anna", "Carl", "Kalle",
                "Joseph", "Ahmed", "Kalle", "Mohammad",
                 "Mostafa", "Emil", "Emma", "Johanna", "Kalle");

        /*System.out.println(myList
                .stream()
                .filter(name -> name.equals("Kalle"))
                .count());*/





        int[] numbers = {1, 2, 3, 4, 5};

        List<Integer> myList1 = Arrays.stream(numbers).boxed().collect(Collectors.toList());

        myList1
                .stream()
                .forEach(n -> System.out.println(n));

    }
}