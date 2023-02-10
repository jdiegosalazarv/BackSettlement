package co.com.ias.settlement.domain.usecase.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;

public class EmployeeStateUseCase {

    private final IEmployeeStateRepository iEmployeeStateSaveRepository;

    public EmployeeStateUseCase(IEmployeeStateRepository iEmployeeStateSaveRepository) {
        this.iEmployeeStateSaveRepository = iEmployeeStateSaveRepository;
    }

    public EmployeeState saveEmployeeState(EmployeeState employeeState) {
        return iEmployeeStateSaveRepository.saveEmployeeState(employeeState);
    }

    public EmployeeState findByIdEmployeeState(Integer id) {
        return this.iEmployeeStateSaveRepository.findByIdEmployeeState(id);
    }
}
