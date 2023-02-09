package co.com.ias.settlement.domain.model.gateways.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;

public interface IEmployeeStateFindByIdRepository {

    EmployeeState findByIdEmployeeState(Integer id);
}
