package co.com.ias.settlement.domain.usecase.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateFindByIdRepository;

public class EmployeeStateFindByIdUseCase {

    private final IEmployeeStateFindByIdRepository iEmployeeStateFindByIdRepository;

    public EmployeeStateFindByIdUseCase(IEmployeeStateFindByIdRepository iEmployeeStateFindByIdRepository) {
        this.iEmployeeStateFindByIdRepository = iEmployeeStateFindByIdRepository;
    }

    public EmployeeState findByIdEmployeeState(Integer id) {
        return this.iEmployeeStateFindByIdRepository.findByIdEmployeeState(id);
    }
}
