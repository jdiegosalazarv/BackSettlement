package co.com.ias.settlement.domain.model.employee;

public class Employee {

    private final IdEmployee id;

    private final IdentificationId identificationId;

    private final EmployeeName name;

    private final ContractStartDate date;

    private final EmployeePosition employeePosition;

    private final Salary salary;

    public Employee(IdEmployee id, IdentificationId identificationId, EmployeeName name, ContractStartDate date, EmployeePosition employeePosition, Salary salary) {
        this.id = id;
        this.identificationId = identificationId;
        this.name = name;
        this.date = date;
        this.employeePosition = employeePosition;
        this.salary = salary;
    }

    public IdEmployee getId() {
        return id;
    }

    public IdentificationId getIdentificationId() {
        return identificationId;
    }

    public EmployeeName getName() {
        return name;
    }

    public ContractStartDate getDate() {
        return date;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public Salary getSalary() {
        return salary;
    }
}
