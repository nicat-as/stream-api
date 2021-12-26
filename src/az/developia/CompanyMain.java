package az.developia;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CompanyMain {
    public static void main(String[] args) {
        var list = new ArrayList<Employee>();
        list.add(new Employee("hugh","ten", BigDecimal.valueOf(400), "active"));
        list.add(new Employee("ben","ten", BigDecimal.valueOf(300), "deleted"));
        list.add(new Employee("jack","ten", BigDecimal.valueOf(700), "active"));
        list.add(new Employee("luke","ten", BigDecimal.valueOf(900), "deleted"));
        list.add(new Employee("peter","ten", BigDecimal.valueOf(480), "active"));
        var emp = new Employee("peter","ten", BigDecimal.valueOf(480), "active");

        // active statuslu employeelerin adini liste yig
        List<String> activeEmployees = new ArrayList<>();
        for (Employee e : list){
            if (e.getStatus().equals("active")){
                activeEmployees.add(e.getName());
            }
        }
//        System.out.println(activeEmployees);

        var actives = list.stream()
                .filter(employee -> employee.getStatus().equals("active"))
                .filter(employee -> employee.getName().startsWith("j"))
                .map(employee -> employee.getName())
                .collect(toList());

        // employeelerin maaslarinin cemini tap
        var sumOfSalaries = list.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);


        System.out.println(sumOfSalaries);

        var sum = BigDecimal.ZERO;
        for (Employee e : list){
            sum.add(e.getSalary());
        }
        System.out.println(sum);

    }
}
