package az.developia;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class Number {
    public Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.range(2,n)
                .boxed()
                .collect(partitioningBy(this::isPrime));
    }

    private boolean isPrime(int candidate){
        // optimization
        var candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.range(2, candidateRoot)
                .noneMatch(i -> candidate%i ==0);
    }

    public static void main(String[] args) {
        Number number = new Number();
        System.out.println(number.partitionPrimes(47));
    }
}
