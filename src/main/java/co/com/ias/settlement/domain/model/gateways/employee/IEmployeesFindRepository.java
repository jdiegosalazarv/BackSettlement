package co.com.ias.settlement.domain.model.gateways.employee;

import co.com.ias.settlement.domain.model.employee.Employee;

import java.util.List;

public interface IEmployeesFindRepository {

    List<Employee> findEmployees();
}
