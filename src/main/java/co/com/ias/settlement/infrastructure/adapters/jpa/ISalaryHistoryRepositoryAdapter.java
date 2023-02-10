package co.com.ias.settlement.infrastructure.adapters.jpa;

import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SalaryHistoryDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalaryHistoryRepositoryAdapter extends JpaRepository<SalaryHistoryDBO, Integer> {
}
