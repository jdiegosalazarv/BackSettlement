package co.com.ias.settlement.infrastructure.adapters.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateSaveRepository;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EmployeeStateSaveRepositoryAdapter implements IEmployeeStateSaveRepository {

    private final IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;

    @Override
    public EmployeeState saveEmployeeState(EmployeeState employeeState) {
        EmployeeStateDBO employeeStateDBO = EmployeeStateDBO.fromDomain(employeeState);
        EmployeeStateDBO employeeStateDBO1 = this.iEmployeeStateRepositoryAdapter.save(employeeStateDBO);
        return EmployeeStateDBO.toDomain(employeeStateDBO1);
    }
}
