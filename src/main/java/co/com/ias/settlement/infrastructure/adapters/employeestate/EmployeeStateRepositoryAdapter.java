package co.com.ias.settlement.infrastructure.adapters.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeStateRepositoryAdapter implements IEmployeeStateRepository {

    private final IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;

    @Override
    public EmployeeState saveEmployeeState(EmployeeState employeeState) {
        EmployeeStateDBO employeeStateDBO = EmployeeStateDBO.fromDomain(employeeState);
        EmployeeStateDBO employeeStateDBO1 = this.iEmployeeStateRepositoryAdapter.save(employeeStateDBO);
        return EmployeeStateDBO.toDomain(employeeStateDBO1);
    }

    @Override
    public EmployeeState findByIdEmployeeState(Integer id) {
        Optional<EmployeeStateDBO> employeeStateDBO = this.iEmployeeStateRepositoryAdapter.findById(id);
        if (employeeStateDBO.isEmpty()) {
            throw new NullPointerException("No existe employee state con el id: " + id);
        }
        return EmployeeStateDBO.toDomain(employeeStateDBO.get());
    }
}
