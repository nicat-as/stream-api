package az.developia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Main {

    public static void main(String[] args) {
        var menu = new ArrayList<Dish>();
        menu.add(new Dish("steak", false, 300, Dish.Type.MEAT));
        menu.add(new Dish("fish", false, 400, Dish.Type.FISH));
        menu.add(new Dish("some vegetarian", true, 200, Dish.Type.OTHER));
//        System.out.println(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)));
//        var result = menu
//                .stream()
//                .collect(Collectors.reducing((i, j) -> i.getCalories() > j.getCalories() ? i : j));
//        System.out.println(result.get());
//        System.out.println(findLowCaloric(menu));
        System.out.println(findLowCaloricWithStream(menu));
//        testFibonacci();
    }

    public static List<String> findLowCaloric(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        lowCaloricDishes.sort(Comparator.comparingInt(Dish::getCalories));
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }

        return lowCaloricDishesName;
    }

    public static List<String> findLowCaloricWithStream(List<Dish> dishes) {
        return dishes.stream()
                .limit(2)
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public static void test(List<Dish> dishes) {
        var streamed = dishes.stream()
                .filter(dish -> {
                    System.out.println("filtering:" + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping:" + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println(streamed);


    }

    public static void testFibonacci() {
        System.out.print("(");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(v -> System.out.print(v[0] + ","));
        System.out.println(")");
    }

    public static void testGenerate() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
