package az.developia.collect;

import az.developia.Dish;

import java.util.ArrayList;
import java.util.List;

public class ToListMain {
    public static void main(String[] args) {
        var menu = new ArrayList<Dish>();
        menu.add(new Dish("steak", false, 300, Dish.Type.MEAT));
        menu.add(new Dish("fish", false, 400, Dish.Type.FISH));
        menu.add(new Dish("some vegetarian", true, 200, Dish.Type.OTHER));

        var collected = menu.stream()
                .filter(dish ->  dish.getCalories() <400)
                .collect(new ToListCollector<>());
        System.out.println(collected);

        // same with previous
        List<Dish> collectedWith = menu.stream()
                .filter(dish ->  dish.getCalories() <400)
                .collect(ArrayList::new,List::add,List::addAll);
    }
}
