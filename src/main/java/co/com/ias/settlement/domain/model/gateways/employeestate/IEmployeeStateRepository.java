package co.com.ias.settlement.domain.model.gateways.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;

public interface IEmployeeStateRepository {

    EmployeeState saveEmployeeState(EmployeeState employeeState);

    EmployeeState findByIdEmployeeState(Integer id);
}
