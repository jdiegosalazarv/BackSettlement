package co.com.ias.settlement.domain.usecase.settlement;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.gateways.settlement.ISettlementRepository;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.Settlement;
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
        Settlement settlement = SettlementOperations.generateSettlement(employee, settlementInfo);
        return this.iSettlementRepository.saveSattlement(settlement);
    }
}
