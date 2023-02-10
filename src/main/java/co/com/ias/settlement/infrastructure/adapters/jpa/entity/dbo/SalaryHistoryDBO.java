package co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "salary_history")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SalaryHistoryDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double newSalary;

    private LocalDate updateSalaryDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "identificationId")
    private EmployeeDBO employee;

    public static SalaryHistory toDomainForSave(SalaryHistoryDBO salaryHistoryDBO) {
        return new SalaryHistory(
                new NewSalary(salaryHistoryDBO.getNewSalary()),
                new UpdateSalaryDate(salaryHistoryDBO.getUpdateSalaryDate()),
                new Employee(
                        new IdentificationId(salaryHistoryDBO.getEmployee().getIdentificationId()),
                        new EmployeeName(salaryHistoryDBO.getEmployee().getEmployeeName()),
                        new ContractStartDate(salaryHistoryDBO.getEmployee().getContractStartDate()),
                        new EmployeePosition(salaryHistoryDBO.getEmployee().getEmployeePosition()),
                        new Salary(salaryHistoryDBO.getEmployee().getSalary()),
                        null,
                        new EmployeeState(
                                new StateId(salaryHistoryDBO.getEmployee().getEmployeeState().getId()),
                                new StateName(salaryHistoryDBO.getEmployee().getEmployeeState().getStateName())
                        )
                )
        );
    }

    public static SalaryHistoryDBO fromDomainForSave(SalaryHistory salaryHistory) {
        return new SalaryHistoryDBO(
                null,
                salaryHistory.getNewSalary().getValue(),
                salaryHistory.getUpdateSalaryDate().getValue(),
                new EmployeeDBO(
                        salaryHistory.getEmployee().getIdentificationId().getValue(),
                        salaryHistory.getEmployee().getName().getValue(),
                        salaryHistory.getEmployee().getContractStartDate().getValue(),
                        salaryHistory.getEmployee().getEmployeePosition().getValue(),
                        salaryHistory.getEmployee().getSalary().getValue(),
                        null,
                        new EmployeeStateDBO(
                                salaryHistory.getEmployee().getEmployeeState().getStateId().getValue(),
                                salaryHistory.getEmployee().getEmployeeState().getStateName().getValue()
                        )

                )
        );
    }

    public static SalaryHistory toDomain(SalaryHistoryDBO salaryHistoryDBO) {
        return new SalaryHistory(
                new NewSalary(salaryHistoryDBO.getNewSalary()),
                new UpdateSalaryDate(salaryHistoryDBO.getUpdateSalaryDate()),
                new Employee(
                        new IdentificationId(salaryHistoryDBO.getEmployee().getIdentificationId()),
                        new EmployeeName(salaryHistoryDBO.getEmployee().getEmployeeName()),
                        new ContractStartDate(salaryHistoryDBO.getEmployee().getContractStartDate()),
                        new EmployeePosition(salaryHistoryDBO.getEmployee().getEmployeePosition()),
                        new Salary(salaryHistoryDBO.getEmployee().getSalary()),
                        new UpdateEmployDate(salaryHistoryDBO.getEmployee().getUpdateEmployeeDate()),
                        new EmployeeState(
                                new StateId(salaryHistoryDBO.getEmployee().getEmployeeState().getId()),
                                new StateName(salaryHistoryDBO.getEmployee().getEmployeeState().getStateName())
                        )
                )
        );
    }

    public static SalaryHistoryDBO fromDomain(SalaryHistory salaryHistory) {
        return new SalaryHistoryDBO(
                null,
                salaryHistory.getNewSalary().getValue(),
                salaryHistory.getUpdateSalaryDate().getValue(),
                new EmployeeDBO(
                        salaryHistory.getEmployee().getIdentificationId().getValue(),
                        salaryHistory.getEmployee().getName().getValue(),
                        salaryHistory.getEmployee().getContractStartDate().getValue(),
                        salaryHistory.getEmployee().getEmployeePosition().getValue(),
                        salaryHistory.getEmployee().getSalary().getValue(),
                        salaryHistory.getUpdateSalaryDate().getValue(),
                        new EmployeeStateDBO(
                                salaryHistory.getEmployee().getEmployeeState().getStateId().getValue(),
                                salaryHistory.getEmployee().getEmployeeState().getStateName().getValue()
                        )

                )
        );
    }
}
