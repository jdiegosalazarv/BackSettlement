package co.com.ias.settlement.domain.usecase.salaryhistory;

import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;

import java.util.List;

public class SalaryHistoryUseCase {

    private final ISalaryHistoryRepository iSalaryHistoryRepository;

    public SalaryHistoryUseCase(ISalaryHistoryRepository iSalaryHistoryRepository) {
        this.iSalaryHistoryRepository = iSalaryHistoryRepository;
    }

    public List<SalaryHistory> getSalariesHistory() {
        return this.iSalaryHistoryRepository.findSalaryHistories();
    }

    public List<SalaryHistory> getSalariesHistoryByEmployeeId(String employeeId) {
        return this.iSalaryHistoryRepository.findSalaryHistoryByEmployeeId(employeeId);
    }
}
