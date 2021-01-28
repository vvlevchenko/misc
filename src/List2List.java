import java.util.*;
import java.util.stream.Collectors;

public class List2List {
    public static void main(String ... args) {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> doubleInts =  ints.stream().map((x) -> x + x).collect(Collectors.toList());
        List<Integer> squareInts = ints.stream().map((x) -> x * x).collect(Collectors.toList());
        System.out.println(ints + " to " + doubleInts);
        System.out.println(ints + " to " + squareInts);
    }
}
