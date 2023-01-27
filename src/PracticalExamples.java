import java.security.SecureRandom;
import java.util.ArrayList;
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

    }
}
