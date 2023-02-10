package co.com.ias.settlement.domain.model.salaryhistory;

import co.com.ias.settlement.domain.model.employee.Employee;

public class SalaryHistory {
    private final NewSalary newSalary;
    private final UpdateSalaryDate updateSalaryDate;

    private final Employee employee;

    public SalaryHistory(NewSalary newSalary, UpdateSalaryDate updateSalaryDate, Employee employee) {
        this.newSalary = newSalary;
        this.updateSalaryDate = updateSalaryDate;
        this.employee = employee;
    }

    public NewSalary getNewSalary() {
        return newSalary;
    }

    public UpdateSalaryDate getUpdateSalaryDate() {
        return updateSalaryDate;
    }

    public Employee getEmployee() {
        return employee;
    }
}
