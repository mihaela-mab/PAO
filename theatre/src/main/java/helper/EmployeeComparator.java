package helper;

import model.Employee;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee employee1, Employee employee2) {
        return employee1.getLastName().compareTo(employee2.getLastName());
    }
}
