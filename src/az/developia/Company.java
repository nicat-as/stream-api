package az.developia;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Employee> employees;

    public Company(){
        this.employees = new ArrayList<>();
    }

    public Company(List<Employee> employees){
        this.employees = employees;
    }

    public void setEmployees(List<Employee> employees){
        this.employees = employees;
    }


    public Company addEmployee(Employee e){
        this.employees.add(e);
        return this;
    }
}
