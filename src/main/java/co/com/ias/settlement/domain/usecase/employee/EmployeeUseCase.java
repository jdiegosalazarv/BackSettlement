package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;

import java.time.LocalDate;
import java.util.List;

public class EmployeeUseCase {

    private final IEmployeeRepository iEmployeeRepository;

    private final IEmployeeStateRepository iEmployeeStateRepository;

    private final ISalaryHistoryRepository iSalaryHistoryRepository;

    public EmployeeUseCase(IEmployeeRepository iEmployeeRepository, IEmployeeStateRepository iEmployeeStateRepository, ISalaryHistoryRepository iSalaryHistoryRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
        this.iEmployeeStateRepository = iEmployeeStateRepository;
        this.iSalaryHistoryRepository = iSalaryHistoryRepository;
    }

    public Employee saveEmployee(Employee employee) {
        SalaryHistory salaryHistory = new SalaryHistory(
                new NewSalary(employee.getSalary().getValue()),
                new UpdateSalaryDate(employee.getContractStartDate().getValue()),
                employee
        );
        Employee savedEmployee = this.iEmployeeRepository.saveEmployee(employee);
        this.iSalaryHistoryRepository.saveSalaryHistory(salaryHistory);
        return savedEmployee;
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
                employeeBD.getContractStartDate(), employee.getEmployeePosition(), employee.getSalary(), employee.getUpdateEmployDate(),
                employeeBD.getEmployeeState());
        SalaryHistory salaryHistory = new SalaryHistory(new NewSalary(newEmployee.getSalary().getValue()),
                new UpdateSalaryDate(LocalDate.now()), newEmployee);
        Employee employeeUpdated = this.iEmployeeRepository.updateEmployee(newEmployee);
        this.iSalaryHistoryRepository.updateSalaryHistory(salaryHistory);
        return employeeUpdated;
    }

    public void deleteEmployee(String id) {
        this.iEmployeeRepository.deleteEmployee(id);
    }
}
