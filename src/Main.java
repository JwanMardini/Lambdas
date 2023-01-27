import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // 17.2 Streams and reduction
        // Summing the Integers from 1 through 10 with a for Loop
        // External Iteration with for loop
        int total = 0;
        for (int number = 1; number <= 10; number++) {
            total += number;
        }
        /*There are several opportunities for error in the preceding code. For
        example, you could:
        . initialize the variable total incorrectly
        . initialize the for loop’s control variable number incorrectly
        . use the wrong loop-continuation condition
        . increment control variable number incorrectly or
        . incorrectly add each value of number to the total.*/

        //instead
        // internal iteration
        // sum the integers from 1 through 10
        System.out.println("Sum of 1 through 10 is " + IntStream.rangeClosed(   1, 10).sum());
        /* In this code, notice that there is neither a counter-control
            variable nor a variable to store the total—this is because
            IntStream conveniently defines rangeClosed and
            sum.*/
        /*The processing step performed by method sum is known
        as a reduction—it reduces the stream of values to a single value (the sum).
        predefined reductions count, min, max, average and summaryStatistics, as well as the reduce method
         for defining your own reductions. a key aspect of functional programming*/

        // 17.3 Mapping and Lambdas
        /* mapping, which transforms a stream’s elements to new values.
        The result is a stream with the same number of elements containing the transformation’s results
         */
        // the sum of the even integers from 2 through 20 using external iteration
        int total1 = 0;
        for (int number = 2; number <= 20; number += 2) {
            total1 += number;
        }

        /* reimplements this task using streams and
            internal iteration.*/
            System.out.println("Sum of even integers "+ IntStream.rangeClosed(1, 10).map(
                    (int x) -> {return x * 2;}).sum());
        //map is an intermediate operation and sum is a terminal operation

        /*Lambda expressions enable you to create methods that can be treated as data. You can:
            . pass lambdas as arguments to other methods (like map, or even other lambdas)
            . assign lambda expressions to variables for later use and
            . return lambda expressions from methods.
            . Any local variable that a lambda references in its lexical scope must be final*/
        //Lambda syntax (parameterList) -> {statements} or x -> x * 2 if the parameter list contains only one parameter the body contains only one expression

        /*Another common intermediate stream operation is filtering
          elements to select those that match a condition—known as a
          predicate*/
        int total3 = 0;
        for (int x = 1; x <= 10; x++) {
            if (x % 2 == 0) { // if x is even
                total3 += x * 3;
            }
        }
        //reimplements this loop using streams.
        // sum the triples of the even integers from 2 through 10
        System.out.printf("Sum of the triples of the even ints from 2 through 10 is: %d%n", IntStream.rangeClosed(1, 10)
                .filter(x -> x % 2 == 0).map(x -> x * 3).sum());



       }

       public static void methodReferences () {
           SecureRandom randomNumbers = new SecureRandom();
           // display 10 random integers on separate lines
           System.out.println("Random numbers on separate lines:");
           randomNumbers.ints(10, 1, 7).forEach(System.out::println);
           // display 10 random integers on the same line
           String numbers = randomNumbers.ints(10, 1, 7)
                   .mapToObj(String::valueOf)
                   .collect(Collectors.joining(" "));
           System.out.println("Random numbers on one line:" + numbers);
       }

       public static void intStreamOperationsArrayOfInt() {
        int[] values = {3, 10, 6, 1};
        //display original values
           System.out.println("Original value: ");
           System.out.println(IntStream.of(values).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
           //count, min, max, sum, average of the array
           System.out.println("Count: "+ IntStream.of(values).count());
           System.out.println("Min: "+ IntStream.of(values).min());
           System.out.println("Max: "+ IntStream.of(values).max());
           System.out.println("Average: "+ IntStream.of(values).average());
           //sum of the values with reduce method
           System.out.println("Sum of squares via map: "+ IntStream.of(values).map(x -> x * x).sum());
           // Displaying the elements in sorted order
           System.out.println("Values displayed in sorted order: " + IntStream.of(values).sorted().
                   mapToObj(String::valueOf).collect(Collectors.joining(" ")));
           // Class IntStream also provides method summaryStatistics that performs the count, min, max, sum and average operations in one pass
           System.out.println(IntStream.of(values).summaryStatistics());

           // terminal operation reduce
           //calculating the sum of the list
           System.out.println(IntStream.of(values).reduce(0, (x,y) -> x + y));
           //calculating the product of the list
           System.out.println(IntStream.of(values).reduce((x, y) -> x * y).getAsInt());

        streamsOfRandomValues();

       }

       public static void arrayOfIntegerAsObj () {
           Integer[] values = {2, 9, 5, 0, 3, 7, 1, 4};
           System.out.println("Original values: " + Arrays.asList(values));
           //Sort values
           System.out.println("Sorted values: " + Arrays.stream(values).sorted().collect(Collectors.toList()));
           // values greater than 4
           List<Integer> greaterThan4 = Arrays.stream(values).filter(value -> value > 4).collect(Collectors.toList());
           // filter values that are greater than 4 and sort them
           System.out.println("Sorted values greater than 4: " + Arrays.stream(values).filter(value -> value > 4).sorted().collect(Collectors.toList()));
           // sorted value that are greater than 4
           System.out.println("Values greater than 4 (ascending with streams): "+greaterThan4.stream().sorted().collect(Collectors.toList()));
       }

       public static void arrayOfString () {
        String [] strings = {"Red", "orange", "Yellow", "green", "Blue", "indigo"};
           // Display original strings
           System.out.println("Original strings: "+ Arrays.asList(strings));
           // strings in uppercase
           System.out.println("Strings in uppercase: "+ Arrays.stream(strings).map(String::toUpperCase)
                   .collect(Collectors.toList()));
           // strings less than "n" and sorted
           System.out.println("strings less than n sorted ascending: "+ Arrays.stream(strings).filter(s -> s.compareToIgnoreCase("n") < 0)
                   .sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList()));
           //
           System.out.println("strings less than n sorted descending: "+ Arrays.stream(strings).filter(s -> s.compareToIgnoreCase("n") < 0)
                   .sorted(String.CASE_INSENSITIVE_ORDER.reversed()).collect(Collectors.toList()));
       }


       public static void arrayOfObj () {
           Employee[] employees = {
                   new Employee("Jason", "Red", 5000, "IT"),
                   new Employee("Ashley", "Green", 7600, "IT"),
                   new Employee("Matthew", "Indigo", 3587.5, "Sales"),
                   new Employee("James", "Indigo", 4700.77, "Marketing"),
                   new Employee("Luke", "Indigo", 6200, "IT"),
                   new Employee("Jason", "Blue", 3200, "Sales"),
                   new Employee("Wendy", "Brown", 4236.4, "Marketing")
           };
           // get List view of the Employees
           List<Employee> list = Arrays.asList(employees);
           // display all Employees
           System.out.println("Complete Employee list:");
           list.stream().forEach(System.out::println);
           //you can populate an immutable List directly via List static method of, as in:
           List<Employee> list1 = List.of(
                   new Employee("Jason", "Red", 5000, "IT"),
                   new Employee("Ashley", "Green", 7600, "IT"),
                   new Employee("Matthew", "Indigo", 3587.5, "Sales"),
                   new Employee("James", "Indigo", 4700.77, "Marketing"),
                   new Employee("Luke", "Indigo", 6200, "IT"),
                   new Employee("Jason", "Blue", 3200, "Sales"),
                   new Employee("Wendy", "Brown", 4236.4, "Marketing"));

           // Predicate that return true for salaries
           Predicate<Employee> fourToSixThousand = e -> (e.getSalary() >= 4000 && e.getSalary() < 6000);
           //Display Employees with salaries in the range $4000-$600
           System.out.println("Employees earning $4000-$6000 per month sorted by salary: ");
           list1.stream().filter(fourToSixThousand).sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);

           // Display first Employee with salary in the range $4000-$6000
           System.out.printf("%nFirst employee who earns $4000-$6000:%n%s%n", list.stream()
                   .filter(fourToSixThousand)
                   .findFirst()
                   .get());

           // Functions for getting first and last names from an Employee
           Function<Employee, String> byFirstName = Employee::getFirstName;
           Function<Employee, String> byLastName = Employee::getLastName;
           // Comparator for comparing Employees by first name then last name
           Comparator<Employee> lastThenFirst = Comparator.comparing(byLastName).thenComparing(byFirstName);
           // sort employees by last name, then first name
           System.out.printf("%nEmployees in ascending order by last name then first:%n");
           list.stream().sorted(lastThenFirst).forEach(System.out::println);
           // sort employees in descending order by last name, then first name67
           System.out.printf("%nEmployees in descending order by last name then first:%n");
           list.stream().sorted(lastThenFirst.reversed()).forEach(System.out::println);

           // group Employees by department
           System.out.printf("%nEmployees by department:%n");
           Map<String, List<Employee>> groupedByDepartment = list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
           groupedByDepartment.forEach((department, employeesInDepartment) -> {System.out.printf("%n%s%n", department);
               employeesInDepartment.forEach(employee -> System.out.printf(" %s%n", employee));}
           /*Map<String, List<Employee>> in which
            each String key is a department and each
            List<Employee> contains the Employees in that
            department. We assign this Map to variable
            groupedByDepartment*/
           );

           // count number of Employees in each department
           System.out.printf("%nCount of Employees by department:%n");
           Map<String, Long> employeeCountByDepartment = list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
           employeeCountByDepartment.forEach((department, count) -> System.out.printf("%s has %d employee(s)%n", department, count));


           /*To locate all the even integers greater than 5 in an
            IntStream, you could pass to IntStream method
            filter the following composed IntPredicate*/
           IntPredicate even = value -> value % 2 == 0;
           IntPredicate greaterThan5 = value -> value > 5;
           even.and(greaterThan5);


       }

       public static void creatAStreamFromAFile () throws IOException {
           // Regex that matches one or more consecutive whitespace characters
           Pattern pattern = Pattern.compile("\\s+");

           // count occurrences of each word in a Stream<String> sorted by word
           Map<String, Long> wordCounts = Files.lines(Paths.get("Chapter2Paragraph.txt")).flatMap(line -> pattern.splitAsStream(line))
                   .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()));
           // display the words grouped by starting letter
           wordCounts.entrySet().stream().collect(Collectors.groupingBy(entry -> entry.getKey().charAt(0), TreeMap::new, Collectors.toList()))
                   .forEach((letter, wordList) -> {
                       System.out.printf("%n%C%n", letter);
                       wordList.stream().forEach(word -> System.out.printf("%13s:  %d%n", word.getKey(), word.getValue()));
                   });
       }


       public static void streamsOfRandomValues (){
        SecureRandom random = new SecureRandom();
        // roll a die 60,000,000 times and summarize the results
        System.out.printf("%-6s%s%n", "Face", "Frequency");
        random.ints(60_000_000, 1, 7).boxed().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())).
                forEach((face, frequency) -> System.out.printf("%-6d%d%n", face, frequency));
   }



}
