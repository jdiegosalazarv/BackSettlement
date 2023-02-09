package co.com.ias.settlement.domain.usecase.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateSaveRepository;

public class EmployeeStateSaveUseCase {

    private final IEmployeeStateSaveRepository iEmployeeStateSaveRepository;

    public EmployeeStateSaveUseCase(IEmployeeStateSaveRepository iEmployeeStateSaveRepository) {
        this.iEmployeeStateSaveRepository = iEmployeeStateSaveRepository;
    }

    public EmployeeState saveEmployeeState(EmployeeState employeeState) {
        return iEmployeeStateSaveRepository.saveEmployeeState(employeeState);
    }
}
