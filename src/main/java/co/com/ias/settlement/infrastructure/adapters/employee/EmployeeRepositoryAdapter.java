package co.com.ias.settlement.infrastructure.adapters.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryAdapter implements IEmployeeRepository {
    private final IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeDBO employeeDBO = EmployeeDBO.fromDomainToDBO(employee);
        EmployeeDBO employeeDBO1 = this.iEmployeeRepositoryAdapter.save(employeeDBO);
        return EmployeeDBO.toDomain(employeeDBO1);
    }

    @Override
    public Employee findEmployeeById(String id) {
        Optional<EmployeeDBO> employeeDBO = this.iEmployeeRepositoryAdapter.findById(id);
        if (employeeDBO.isEmpty()) {
            throw new NullPointerException("No existe empleado con el id: " + id);
        }
        return EmployeeDBO.toDomain(employeeDBO.get());
    }

    @Override
    public List<Employee> findEmployees() {
        List<EmployeeDBO> employeeDBOS = this.iEmployeeRepositoryAdapter.findAll();
        return employeeDBOS.stream().map(EmployeeDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        EmployeeDBO employeeDBO = EmployeeDBO.fromDomain(employee);
        EmployeeDBO employeeDBO1 = this.iEmployeeRepositoryAdapter.save(employeeDBO);
        return EmployeeDBO.toDomain(employeeDBO1);
    }
}
