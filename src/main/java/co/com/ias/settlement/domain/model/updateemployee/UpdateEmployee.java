package co.com.ias.settlement.domain.model.updateemployee;

import co.com.ias.settlement.domain.model.employee.EmployeePosition;
import co.com.ias.settlement.domain.model.employee.IdentificationId;
import co.com.ias.settlement.domain.model.employee.Salary;
import co.com.ias.settlement.domain.model.employee.UpdateEmployDate;

public class UpdateEmployee {

    private final IdentificationId identificationId;
    private final EmployeePosition employeePosition;
    private final Salary salary;
    private final UpdateEmployDate updateEmployDate;

    public UpdateEmployee(IdentificationId identificationId, EmployeePosition employeePosition, Salary salary, UpdateEmployDate updateEmployDate) {
        this.identificationId = identificationId;
        this.employeePosition = employeePosition;
        this.salary = salary;
        this.updateEmployDate = updateEmployDate;
    }

    public IdentificationId getIdentificationId() {
        return identificationId;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public Salary getSalary() {
        return salary;
    }

    public UpdateEmployDate getUpdateEmployDate() {
        return updateEmployDate;
    }
}
