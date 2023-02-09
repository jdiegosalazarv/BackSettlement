package co.com.ias.settlement.domain.model.employeestate;

public class EmployeeState {

    private final StateId stateId;

    private final StateName stateName;

    public EmployeeState(StateId stateId, StateName stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }

    public StateId getStateId() {
        return stateId;
    }

    public StateName getStateName() {
        return stateName;
    }
}
