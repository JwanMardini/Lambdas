import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CodeExamples {
    public static void main(String[] args) {
        // filter method
        List<Product> filteredProducts = getProducts().stream()
                .filter((product) -> product.getPrice() > 25000)
                .collect(Collectors.toList());

        filteredProducts.forEach(System.out::println);
    }

    private static List <Product> getProducts() {
        List <Product> productsList = new ArrayList< Product >();
        productsList.add(new Product(1, "HP Laptop", 25000));
        productsList.add(new Product(2, "Dell Laptop", 30000));
        productsList.add(new Product(3, "Lenovo Laptop", 28000));
        productsList.add(new Product(4, "Sony Laptop", 28000));
        productsList.add(new Product(5, "Apple Laptop", 90000));
        return productsList;
    }
}
// Source
// https://www.javaguides.net/2020/04/java-8-stream-filter-and-foreach-example.html





class GFG {
    // Driver code
    public static void main(String[] args)
    {
        System.out.println("The stream after applying "
                + "the function is : ");

        // Creating a list of Integers
        List<Integer> list = Arrays.asList(3, 6, 9, 12, 15);

        // Using Stream map(Function mapper) and
        // displaying the corresponding new stream
        list.stream().map(number -> number * 3).forEach(System.out::println);
    }
}
// Source
// https://www.geeksforgeeks.org/stream-map-java-examples/