package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeSaveRepository;

public class EmployeeSaveUseCase {

    private final IEmployeeSaveRepository iEmployeeSaveRepository;

    public EmployeeSaveUseCase(IEmployeeSaveRepository iEmployeeSaveRepository) {
        this.iEmployeeSaveRepository = iEmployeeSaveRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return this.iEmployeeSaveRepository.saveEmployee(employee);
    }
}
