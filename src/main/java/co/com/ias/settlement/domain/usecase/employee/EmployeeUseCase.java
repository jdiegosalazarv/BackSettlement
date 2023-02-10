package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.employee.UpdateDate;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;

import java.time.LocalDate;
import java.util.List;

public class EmployeeUseCase {

    private final IEmployeeRepository iEmployeeRepository;

    private final IEmployeeStateRepository iEmployeeStateRepository;

    public EmployeeUseCase(IEmployeeRepository iEmployeeSaveRepository, IEmployeeStateRepository iEmployeeStateRepository) {
        this.iEmployeeRepository = iEmployeeSaveRepository;
        this.iEmployeeStateRepository = iEmployeeStateRepository;
    }

    public Employee saveEmployee(Employee employee) {
        Employee newEmployee = new Employee(employee.getIdentificationId(), employee.getName(),
                employee.getContractStartDate(), employee.getEmployeePosition(), employee.getSalary(), null,
                employee.getEmployeeState());
        return this.iEmployeeRepository.saveEmployee(newEmployee);
    }

    public List<Employee> findEmployees() {
        return this.iEmployeeRepository.findEmployees();
    }

    public Employee findEmployeByid(String id) {
        return this.iEmployeeRepository.findEmployeeById(id);
    }

    public Employee updateEmployee(Employee employee) {
        Employee employeeBD = this.iEmployeeRepository.findEmployeeById(employee.getIdentificationId().getValue());
        Employee newEmployee = new Employee(employeeBD.getIdentificationId(), employeeBD.getName(),
                employeeBD.getContractStartDate(), employee.getEmployeePosition(), employee.getSalary(),
                new UpdateDate(LocalDate.now()), employeeBD.getEmployeeState());
        return this.iEmployeeRepository.updateEmployee(newEmployee);
    }

    public void deleteEmployee(String id) {
        this.iEmployeeRepository.deleteEmployee(id);
    }
}
