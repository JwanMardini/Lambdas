import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

public class PracticalExamples {
    public static void main(String[] args) {
        /*//Task 16
        SecureRandom random = new SecureRandom();
        random.ints(50, 1, 999).
                filter(x -> x % 2 != 0).sorted().
                forEach(System.out::println);
        //Task 18
        System.out.println(IntStream.rangeClosed(1, 10).
                filter(x -> x % 2 == 0).map(x -> x * 3).sum());*/

        task1723();
    }

    public static void task1720 () {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> myList = new ArrayList<>();
        boolean i = true;
        while (i){
            System.out.print("Enter the grades then enter 0 to map the grades: ");
            int grade = input.nextInt();
            if (grade == 0){i = false;}
            myList.add(grade);
        }
        myList.stream().map(x -> {
            if (x == 5){
                return "A";
        } else if (x == 4) {
                return "B";
            } else if (x == 3) {
                return "C";
            } else if (x == 2) {
                return "D";
            }else{
                return "F";
            }
        }).forEach(System.out::println);
    }

    public static void task1722 () {
        List<List<Integer>> gradesArray = List.of(
                List.of(87, 96, 70),
                List.of(68, 87, 90),
                List.of(94, 100, 90),
                List.of(100, 81, 82));

        gradesArray.stream().forEach(x -> {
            System.out.println("The average of " + x + " is " + x.stream().mapToInt(y -> y).average().orElse(0));
        });
    }

    public static void task1723(){
        List<Person> personList = List.of(
                new Person("Jwan", "iiii"),
                new Person("Mazen", "Mardini"),
                new Person("Salah", "ii"),
                new Person("Lana", "Mardini")
        );

        Comparator<Person> personComparator = Comparator.comparing(Person::lastName);
        System.out.println(personList.stream().filter(x -> x.lastName().equals("Mardini")).findFirst().get());
    }
}


