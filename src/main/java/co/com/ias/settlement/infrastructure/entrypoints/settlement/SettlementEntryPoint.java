package co.com.ias.settlement.infrastructure.entrypoints.settlement;

import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.Settlement;
import co.com.ias.settlement.domain.usecase.settlement.SettlementUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.settlement.dto.SettlementDTO;
import co.com.ias.settlement.infrastructure.entrypoints.settlement.dto.SettlementInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settlement")
@AllArgsConstructor
public class SettlementEntryPoint {

    private final SettlementUseCase settlementUseCase;

    @PostMapping
    public ResponseEntity<?> saveSettlement(@RequestBody SettlementInfoDTO settlementInfoDTO) {
        SettlementInfo settlementInfo = SettlementInfoDTO.toDomain(settlementInfoDTO);
        Settlement settlementInfoSaved = this.settlementUseCase.saveSettlement(settlementInfo);
        SettlementDTO settlementDTO = SettlementDTO.fromDomain(settlementInfoSaved);
        return ResponseEntity.status(201).body(settlementDTO);
    }
}
