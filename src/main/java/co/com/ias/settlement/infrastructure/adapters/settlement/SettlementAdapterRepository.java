package co.com.ias.settlement.infrastructure.adapters.settlement;

import co.com.ias.settlement.domain.model.gateways.settlement.ISettlementRepository;
import co.com.ias.settlement.domain.model.settlement.Settlement;
import co.com.ias.settlement.infrastructure.adapters.jpa.ISettlementRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SettlementDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SettlementAdapterRepository implements ISettlementRepository {

    private final ISettlementRepositoryAdapter iSettlementRepositoryAdapter;

    @Override
    public Settlement saveSattlement(Settlement settlement) {
        SettlementDBO settlementDBO = SettlementDBO.fromDomain(settlement);
        SettlementDBO settlementSaved = this.iSettlementRepositoryAdapter.save(settlementDBO);
        return SettlementDBO.toDomain(settlementSaved);
    }

    @Override
    public List<Settlement> findAllSettlements() {
        List<SettlementDBO> settlementDBOs = this.iSettlementRepositoryAdapter.findAll();
        return settlementDBOs.stream().map(SettlementDBO::toDomain).toList();
    }
}
