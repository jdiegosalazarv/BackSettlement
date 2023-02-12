package co.com.ias.settlement.domain.usecase.settlement;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.gateways.settlement.ISettlementRepository;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.*;
import co.com.ias.settlement.domain.usecase.utils.SettlementOperations;

public class SettlementUseCase {

    private final ISettlementRepository iSettlementRepository;
    private final IEmployeeRepository iEmployeeRepository;
    private final ISalaryHistoryRepository iSalaryHistoryRepository;

    public SettlementUseCase(ISettlementRepository iSettlementRepository, IEmployeeRepository iEmployeeRepository, ISalaryHistoryRepository iSalaryHistoryRepository) {
        this.iSettlementRepository = iSettlementRepository;
        this.iEmployeeRepository = iEmployeeRepository;
        this.iSalaryHistoryRepository = iSalaryHistoryRepository;
    }

    public Settlement saveSettlement(SettlementInfo settlementInfo) {
        Employee employee = this.iEmployeeRepository.findEmployeeById(settlementInfo.getEmployeeId().getValue());
        Settlement settlement = new Settlement(
                null,
                employee,
                new TransportationAssistance(SettlementOperations.setTransportationAssistance(employee.getSalary().getValue())),
                new FinalContractDate(settlementInfo.getFinalContractDate().getValue()),
                new WithdrawalReason(settlementInfo.getWithdrawalReason().getValue()),
                new WorkingDays(SettlementOperations.findWorkingDays(employee.getContractStartDate().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new WorkingDaysActualYear(SettlementOperations.findWorkingDaysLastYear(settlementInfo.getFinalContractDate().getValue())),
                new VacationDays(SettlementOperations.findVacationDays(employee.getContractStartDate().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new WorkingDaysLastSemester(SettlementOperations.findWorkingDaysLastSemester(settlementInfo.getFinalContractDate().getValue())),
                new BaseSalary(SettlementOperations.findBaseSalary(employee.getSalary().getValue())),
                new Severance(SettlementOperations.findSeverance(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue()
                        , settlementInfo.getFinalContractDate().getValue())),
                new Vacations(SettlementOperations.findVacations(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue(), settlementInfo.getFinalContractDate().getValue())),
                new SeveranceInterest(SettlementOperations.findSeveranceInterests(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue(), settlementInfo.getFinalContractDate().getValue())),
                new ServiceBonus(SettlementOperations.findBonus(settlementInfo.getWithdrawalReason().getValue(),
                        employee.getSalary().getValue(), employee.getContractStartDate().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new PayrollPayable(SettlementOperations.findPayrollPayable(employee.getSalary().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new Total(SettlementOperations.findTotal(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue(), settlementInfo.getFinalContractDate().getValue(),
                        settlementInfo.getWithdrawalReason().getValue()))

        );
        return this.iSettlementRepository.saveSattlement(settlement);
    }
}
