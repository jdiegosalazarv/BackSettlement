package co.com.ias.settlement.domain.model.gateways.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;

public interface IEmployeeStateSaveRepository {

    EmployeeState saveEmployeeState(EmployeeState employeeState);
}
