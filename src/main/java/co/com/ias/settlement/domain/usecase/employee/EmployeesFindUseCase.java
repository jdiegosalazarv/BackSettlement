package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeesFindRepository;

import java.util.List;

public class EmployeesFindUseCase {

    private final IEmployeesFindRepository iEmployeesFindRepository;

    public EmployeesFindUseCase(IEmployeesFindRepository iEmployeesFindRepository) {
        this.iEmployeesFindRepository = iEmployeesFindRepository;
    }

    public List<Employee> findEmployees() {
        return this.iEmployeesFindRepository.findEmployees();
    }
}
