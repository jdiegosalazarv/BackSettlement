package co.com.ias.settlement.infrastructure.adapters.salaryhistory;

import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.infrastructure.adapters.jpa.ISalaryHistoryRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SalaryHistoryDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SalaryHistoryRepositoryAdapter implements ISalaryHistoryRepository {

    private final ISalaryHistoryRepositoryAdapter iSalaryHistoryRepositoryAdapter;

    @Override
    public SalaryHistory saveSalaryHistory(SalaryHistory salaryHistory) {
        SalaryHistoryDBO salaryHistoryDBO = SalaryHistoryDBO.fromDomain(salaryHistory);
        return SalaryHistoryDBO.toDomain(this.iSalaryHistoryRepositoryAdapter.save(salaryHistoryDBO));
    }

    @Override
    public List<SalaryHistory> findSalaryHistoryByEmployeeId(String employeeId) {
        return null;
    }
}
