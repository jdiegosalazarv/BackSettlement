package co.com.ias.settlement.domain.model.gateways.employee;

import co.com.ias.settlement.domain.model.employee.Employee;

import java.util.List;

public interface IEmployeeRepository {

    Employee saveEmployee(Employee employee);

    Employee findEmployeeById(String id);

    List<Employee> findEmployees();

    Employee updateEmployee(Employee employee);
}
