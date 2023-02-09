package co.com.ias.settlement.infrastructure.adapters.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeesFindRepository;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class EmployeesFindRepositoryAdapter implements IEmployeesFindRepository {

    private final IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;

    @Override
    public List<Employee> findEmployees() {
        List<EmployeeDBO> employeeDBOS = this.iEmployeeRepositoryAdapter.findAll();
        return employeeDBOS.stream().map(EmployeeDBO::toDomain).collect(Collectors.toList());
    }
}
