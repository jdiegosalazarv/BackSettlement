package co.com.ias.settlement.infrastructure.adapters.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateFindByIdRepository;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeStateFindByIdRepositoryAdapter implements IEmployeeStateFindByIdRepository {

    private final IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;

    @Override
    public EmployeeState findByIdEmployeeState(Integer id) {
        Optional<EmployeeStateDBO> employeeStateDBO = this.iEmployeeStateRepositoryAdapter.findById(id);
        if (employeeStateDBO.isEmpty()) {
            throw new NullPointerException("No existe employee state con el id: " + id);
        }
        return EmployeeStateDBO.toDomain(employeeStateDBO.get());
    }
}
