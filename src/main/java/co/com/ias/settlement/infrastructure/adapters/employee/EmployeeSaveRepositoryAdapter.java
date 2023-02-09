package co.com.ias.settlement.infrastructure.adapters.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeSaveRepository;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EmployeeSaveRepositoryAdapter implements IEmployeeSaveRepository {
    private final IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeDBO employeeDBO = EmployeeDBO.fromDomain(employee);
        EmployeeDBO employeeDBO1 = this.iEmployeeRepositoryAdapter.save(employeeDBO);
        return EmployeeDBO.toDomain(employeeDBO1);
    }
}
