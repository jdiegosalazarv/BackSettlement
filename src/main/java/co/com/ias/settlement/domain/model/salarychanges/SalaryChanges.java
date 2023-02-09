package co.com.ias.settlement.domain.model.salarychanges;

public class SalaryChanges {

    private final SalaryID salaryID;

    private final NewSalary newSalary;

    private final UpdateSalaryDate updateSalaryDate;

    public SalaryChanges(SalaryID salaryID, NewSalary newSalary, UpdateSalaryDate updateSalaryDate) {
        this.salaryID = salaryID;
        this.newSalary = newSalary;
        this.updateSalaryDate = updateSalaryDate;
    }

    public SalaryID getSalaryID() {
        return salaryID;
    }

    public NewSalary getNewSalary() {
        return newSalary;
    }

    public UpdateSalaryDate getUpdateSalaryDate() {
        return updateSalaryDate;
    }
}
