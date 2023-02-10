package co.com.ias.settlement.domain.model.employee;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;

public class Employee {
    private final IdentificationId identificationId;
    private final EmployeeName name;
    private final ContractStartDate contractStartDate;
    private final EmployeePosition employeePosition;
    private final Salary salary;
    private final EmployeeState employeeState;

    public Employee(IdentificationId identificationId, EmployeeName name, ContractStartDate date,
                    EmployeePosition employeePosition, Salary salary, EmployeeState employeeState) {
        this.identificationId = identificationId;
        this.name = name;
        this.contractStartDate = date;
        this.employeePosition = employeePosition;
        this.salary = salary;
        this.employeeState = employeeState;
    }

    public IdentificationId getIdentificationId() {
        return identificationId;
    }

    public EmployeeName getName() {
        return name;
    }

    public ContractStartDate getContractStartDate() {
        return contractStartDate;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public Salary getSalary() {
        return salary;
    }

    public EmployeeState getEmployeeState() {
        return employeeState;
    }


}
