package co.com.ias.settlement.domain.model.gateways.employee;

import co.com.ias.settlement.domain.model.employee.Employee;

public interface IEmployeeSaveRepository {

    Employee saveEmployee(Employee employee);
}
