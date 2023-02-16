package co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.settlement.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "settlements")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SettlementDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private EmployeeDBO employeeDBO;

    private Double transportationAssistance;

    private LocalDate finalContractDate;

    private String withdrawalReason;

    private Integer workingDays;

    private Integer workingDaysActualYear;

    private Double vacationDays;

    private Integer workingDaysLastSemester;

    private Double baseSalary;

    private Double severance;

    private Double vacations;

    private Double severanceInterest;

    private Double serviceBonus;

    private Double payrollPayable;

    private Double bonus;
    private Double total;

    public static Settlement toDomain(SettlementDBO settlementDBO) {
        return new Settlement(
                new SettlementId(settlementDBO.getId()),
                new Employee(
                        new IdentificationId(settlementDBO.getEmployeeDBO().getIdentificationId()),
                        new EmployeeName(settlementDBO.getEmployeeDBO().getEmployeeName()),
                        new ContractStartDate(settlementDBO.getEmployeeDBO().getContractStartDate()),
                        (settlementDBO.getEmployeeDBO().getEmployeePosition() == null) ? null :
                                new EmployeePosition(settlementDBO.getEmployeeDBO().getEmployeePosition()),
                        new Salary(settlementDBO.getEmployeeDBO().getSalary()),
                        (settlementDBO.getEmployeeDBO().getUpdateEmployeeDate() == null) ? null :
                                new UpdateEmployDate(settlementDBO.getEmployeeDBO().getUpdateEmployeeDate()),
                        new EmployeeState(
                                new StateId(settlementDBO.getEmployeeDBO().getEmployeeState().getId()),
                                new StateName(settlementDBO.getEmployeeDBO().getEmployeeState().getStateName())
                        )
                ),
                new TransportationAssistance(settlementDBO.getTransportationAssistance()),
                new FinalContractDate(settlementDBO.getFinalContractDate()),
                new WithdrawalReason(settlementDBO.getWithdrawalReason()),
                new WorkingDays(settlementDBO.getWorkingDays()),
                new WorkingDaysActualYear(settlementDBO.getWorkingDaysActualYear()),
                new VacationDays(settlementDBO.getVacationDays()),
                new WorkingDaysLastSemester(settlementDBO.getWorkingDaysLastSemester()),
                new BaseSalary(settlementDBO.getBaseSalary()),
                new Severance(settlementDBO.getSeverance()),
                new Vacations(settlementDBO.getVacations()),
                new SeveranceInterest(settlementDBO.getSeveranceInterest()),
                new ServiceBonus(settlementDBO.getServiceBonus()),
                new PayrollPayable(settlementDBO.getPayrollPayable()),
                new Bonus(settlementDBO.getBonus()),
                new Total(settlementDBO.getTotal())
        );
    }

    public static SettlementDBO fromDomain(Settlement settlement) {
        return new SettlementDBO(
                (settlement.getId() == null) ? null : settlement.getId().getValue(),
                new EmployeeDBO(
                        settlement.getEmployee().getIdentificationId().getValue(),
                        settlement.getEmployee().getName().getValue(),
                        settlement.getEmployee().getContractStartDate().getValue(),
                        (settlement.getEmployee().getEmployeePosition() == null) ? null :
                                settlement.getEmployee().getEmployeePosition().getValue(),
                        settlement.getEmployee().getSalary().getValue(),
                        (settlement.getEmployee().getUpdateEmployDate() == null) ? null :
                                settlement.getEmployee().getUpdateEmployDate().getValue(),
                        new EmployeeStateDBO(
                                settlement.getEmployee().getEmployeeState().getStateId().getValue(),
                                settlement.getEmployee().getEmployeeState().getStateName().getValue()
                        )
                ),
                settlement.getTransportationAssistance().getValue(),
                settlement.getFinalContractDate().getValue(),
                settlement.getWithdrawalReason().getValue(),
                settlement.getWorkingDays().getValue(),
                settlement.getWorkingDaysActualYear().getValue(),
                settlement.getVacationDays().getValue(),
                settlement.getWorkingDaysLastSemester().getValue(),
                settlement.getBaseSalary().getValue(),
                settlement.getSeverance().getValue(),
                settlement.getVacations().getValue(),
                settlement.getSeveranceInterest().getValue(),
                settlement.getServiceBonus().getValue(),
                settlement.getPayrollPayable().getValue(),
                settlement.getBonus().getValue(),
                settlement.getTotal().getValue()
        );
    }
}
