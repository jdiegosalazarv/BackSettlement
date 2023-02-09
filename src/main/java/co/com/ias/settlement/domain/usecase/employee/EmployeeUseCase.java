package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;

import java.util.List;

public class EmployeeUseCase {

    private final IEmployeeRepository iEmployeeSaveRepository;

    public EmployeeUseCase(IEmployeeRepository iEmployeeSaveRepository) {
        this.iEmployeeSaveRepository = iEmployeeSaveRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return this.iEmployeeSaveRepository.saveEmployee(employee);
    }

    //a partir de este punto nuevos metodos
    public List<Employee> findEmployees() {
        return this.iEmployeeSaveRepository.findEmployees();
    }

    public Employee findEmployeByid(String id) {
        return this.iEmployeeSaveRepository.findEmployeeById(id);
    }
}
