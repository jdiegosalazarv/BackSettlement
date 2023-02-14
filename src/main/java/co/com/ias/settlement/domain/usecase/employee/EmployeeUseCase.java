package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;
import co.com.ias.settlement.domain.model.updateemployee.UpdateEmployee;

import java.time.LocalDate;
import java.util.List;

public class EmployeeUseCase {

    private final IEmployeeRepository iEmployeeRepository;

    private final IEmployeeStateRepository iEmployeeStateRepository;

    private final ISalaryHistoryRepository iSalaryHistoryRepository;

    private final Integer EMPLOYEE_STATE_ID = 1;
    private final String EMPLOYEE_STATE_NAME = "Activo";

    public EmployeeUseCase(IEmployeeRepository iEmployeeRepository, IEmployeeStateRepository iEmployeeStateRepository, ISalaryHistoryRepository iSalaryHistoryRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
        this.iEmployeeStateRepository = iEmployeeStateRepository;
        this.iSalaryHistoryRepository = iSalaryHistoryRepository;
    }

    public Employee saveEmployee(Employee employee) {
        EmployeeState employeeState = new EmployeeState(new StateId(this.EMPLOYEE_STATE_ID), new StateName(this.EMPLOYEE_STATE_NAME));
        Employee employee1 = new Employee(
                employee.getIdentificationId(),
                employee.getName(),
                employee.getContractStartDate(),
                (employee.getEmployeePosition() == null) ? null : employee.getEmployeePosition(),
                employee.getSalary(),
                employee.getUpdateEmployDate(), employeeState
        );

        SalaryHistory salaryHistory = new SalaryHistory(
                new NewSalary(employee.getSalary().getValue()),
                new UpdateSalaryDate(employee.getContractStartDate().getValue()),
                employee1
        );
        Employee savedEmployee = this.iEmployeeRepository.saveEmployee(employee1);
        SalaryHistory salaryHistory1 = this.iSalaryHistoryRepository.saveSalaryHistory(salaryHistory);
        return savedEmployee;
    }

    public List<Employee> findEmployees() {
        return this.iEmployeeRepository.findEmployees();
    }

    public Employee findEmployeByid(String id) {
        return this.iEmployeeRepository.findEmployeeById(id);
    }

    public Employee updateEmployee(UpdateEmployee employee) {
        Employee employeeBD = this.iEmployeeRepository.findEmployeeById(employee.getIdentificationId().getValue());
        if (employee.getSalary().getValue() < employeeBD.getSalary().getValue()) {
            throw new IllegalArgumentException("El salario debe ser mayor que el actual");
        }
        Employee newEmployee = new Employee(
                employeeBD.getIdentificationId(),
                employeeBD.getName(),
                employeeBD.getContractStartDate(),
                (employee.getEmployeePosition() == null) ? null : employee.getEmployeePosition(),
                employee.getSalary(),
                employee.getUpdateEmployDate(),
                employeeBD.getEmployeeState()
        );
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
