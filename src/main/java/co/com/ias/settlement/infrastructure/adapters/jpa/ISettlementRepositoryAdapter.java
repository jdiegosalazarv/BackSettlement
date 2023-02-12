package co.com.ias.settlement.infrastructure.adapters.jpa;

import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SettlementDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISettlementRepositoryAdapter extends JpaRepository<SettlementDBO, Integer> {
}
