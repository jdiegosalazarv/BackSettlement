package co.com.ias.settlement.domain.model.settlement;

import co.com.ias.settlement.domain.model.employee.Employee;

public class Settlement {

    private final SettlementId id;

    private final Employee employee;

    private final TransportationAssistance transportationAssistance;

    private final FinalContractDate finalContractDate;

    private final WithdrawalReason withdrawalReason;

    private final WorkingDays workingDays;

    private final WorkingDarysActualYear workingDarysActualYear;

    private final VacationDays vacationDays;

    private final WorkingDaysLastSemester workingDaysLastSemester;

    private final BaseSalary baseSalary;

    private final Severance severance;

    private final Vacations vacations;

    private final SeveranceInterest severanceInterest;

    private final ServiceBonus serviceBonus;

    private final PayrollPayable payrollPayable;

    private final Total total;

    public Settlement(SettlementId id, Employee employee, TransportationAssistance transportationAssistance,
                      FinalContractDate finalContractDate, WithdrawalReason withdrawalReason, WorkingDays workingDays,
                      WorkingDarysActualYear workingDarysActualYear, VacationDays vacationDays, WorkingDaysLastSemester workingDaysLastSemester,
                      BaseSalary baseSalary, Severance severance, Vacations vacations, SeveranceInterest severanceInterest, ServiceBonus serviceBonus,
                      PayrollPayable payrollPayable, Total total) {
        this.id = id;
        this.employee = employee;
        this.transportationAssistance = transportationAssistance;
        this.finalContractDate = finalContractDate;
        this.withdrawalReason = withdrawalReason;
        this.workingDays = workingDays;
        this.workingDarysActualYear = workingDarysActualYear;
        this.vacationDays = vacationDays;
        this.workingDaysLastSemester = workingDaysLastSemester;
        this.baseSalary = baseSalary;
        this.severance = severance;
        this.vacations = vacations;
        this.severanceInterest = severanceInterest;
        this.serviceBonus = serviceBonus;
        this.payrollPayable = payrollPayable;
        this.total = total;
    }

    public SettlementId getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public TransportationAssistance getTransportationAssistance() {
        return transportationAssistance;
    }

    public FinalContractDate getFinalContractDate() {
        return finalContractDate;
    }

    public WithdrawalReason getWithdrawalReason() {
        return withdrawalReason;
    }

    public WorkingDays getWorkingDays() {
        return workingDays;
    }

    public WorkingDarysActualYear getWorkingDarysActualYear() {
        return workingDarysActualYear;
    }

    public VacationDays getVacationDays() {
        return vacationDays;
    }

    public WorkingDaysLastSemester getWorkingDaysLastSemester() {
        return workingDaysLastSemester;
    }

    public BaseSalary getBaseSalary() {
        return baseSalary;
    }

    public Severance getSeverance() {
        return severance;
    }

    public Vacations getVacations() {
        return vacations;
    }

    public SeveranceInterest getSeveranceInterest() {
        return severanceInterest;
    }

    public ServiceBonus getServiceBonus() {
        return serviceBonus;
    }

    public PayrollPayable getPayrollPayable() {
        return payrollPayable;
    }

    public Total getTotal() {
        return total;
    }
}