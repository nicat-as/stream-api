package az.developia;

import az.developia.collect.ToListCollector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) {
        var menu = new ArrayList<Dish>();
        menu.add(new Dish("steak", false, 300, Dish.Type.MEAT));
        menu.add(new Dish("fish", false, 400, Dish.Type.FISH));
        menu.add(new Dish("some vegetarian", true, 200, Dish.Type.OTHER));
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

    public static void testCount(List<Dish> dishes) {
        var count = dishes.stream().collect(Collectors.counting());
        System.out.println(count);

        var maxCaloriedDish = dishes.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        maxCaloriedDish.ifPresent(System.out::println);

    }

    public static void testManipulatingGrouppedElements(List<Dish> dishes) {
        var groupped = dishes.stream()
                .filter(d -> d.getCalories() > 500)
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(groupped);

        groupped = dishes.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())
                ));

        System.out.println(groupped);
    }

    public static void testMultilevelGroupping(List<Dish> dishes) {
        var collected = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return "DIET";
                            } else if (dish.getCalories() <= 600) {
                                return "NORMAL";
                            } else {
                                return "FAT";
                            }
                        }, Collectors.mapping(Dish::getName,toList()))));
        var map = collected.get(Dish.Type.MEAT).get("NORMAL");
    }

    public static void testCollectingDataInSubGroups(List<Dish> dishes) {
        var collectedWithCounts = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, counting()));
        System.out.println(collectedWithCounts);

        var collectedWithMaxes = dishes.stream()
                .collect(
                        groupingBy(Dish::getType,
                                maxBy(Comparator.comparingInt(Dish::getCalories))
                        )
                );
        System.out.println(collectedWithMaxes);
        var colletedAndThen = dishes.stream()
                .collect(
                        groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get
                                )
                        )
                );
        System.out.println(colletedAndThen);
    }
}
