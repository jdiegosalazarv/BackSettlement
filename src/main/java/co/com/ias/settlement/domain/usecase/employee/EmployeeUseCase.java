package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;

import java.util.List;

public class EmployeeUseCase {

    private final IEmployeeRepository iEmployeeRepository;

    private final IEmployeeStateRepository iEmployeeStateRepository;

    public EmployeeUseCase(IEmployeeRepository iEmployeeSaveRepository, IEmployeeStateRepository iEmployeeStateRepository) {
        this.iEmployeeRepository = iEmployeeSaveRepository;
        this.iEmployeeStateRepository = iEmployeeStateRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return this.iEmployeeRepository.saveEmployee(employee);
    }

    //a partir de este punto nuevos metodos
    public List<Employee> findEmployees() {
        return this.iEmployeeRepository.findEmployees();
    }

    public Employee findEmployeByid(String id) {
        return this.iEmployeeRepository.findEmployeeById(id);
    }
}
