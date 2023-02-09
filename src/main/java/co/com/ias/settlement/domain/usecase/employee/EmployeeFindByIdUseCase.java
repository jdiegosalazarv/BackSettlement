package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeFindByIdRepository;

public class EmployeeFindByIdUseCase {

    private final IEmployeeFindByIdRepository iEmployeeFindByIdRepository;

    public EmployeeFindByIdUseCase(IEmployeeFindByIdRepository iEmployeeFindByIdRepository) {
        this.iEmployeeFindByIdRepository = iEmployeeFindByIdRepository;
    }

    public Employee findEmployeByid(String id) {
        return this.iEmployeeFindByIdRepository.findEmployeeById(id);
    }
}
