package co.com.ias.settlement.infrastructure.adapters.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeFindByIdRepository;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeFindByIdRepositoryAdapter implements IEmployeeFindByIdRepository {

    private final IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;

    @Override
    public Employee findEmployeeById(String id) {
        Optional<EmployeeDBO> employeeDBO = this.iEmployeeRepositoryAdapter.findById(id);
        if (employeeDBO.isEmpty()) {
            throw new NullPointerException("No existe empleado con el id: " + id);
        }
        return EmployeeDBO.toDomain(employeeDBO.get());
    }
}
