package az.developia;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class Employee {
    private String name;
    private String lastname;
    private BigDecimal salary;
    private String status;

    public Employee(String name, String lastname, BigDecimal salary, String status) {
        this.name = name;
        this.lastname = lastname;
        this.salary = salary;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(lastname, employee.lastname) && Objects.equals(salary, employee.salary) && Objects.equals(status, employee.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, salary, status);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("lastname='" + lastname + "'")
                .add("salary=" + salary)
                .add("status='" + status + "'")
                .toString();
    }
}
