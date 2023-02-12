package co.com.ias.settlement.domain.model.gateways.settlement;

import co.com.ias.settlement.domain.model.settlement.Settlement;

import java.util.List;

public interface ISettlementRepository {

    Settlement saveSattlement(Settlement settlement);

    List<Settlement> findAllSettlements();


}
