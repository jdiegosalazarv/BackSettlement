package co.com.ias.settlement.infrastructure.entrypoints.settlement;

import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.Settlement;
import co.com.ias.settlement.domain.usecase.settlement.SettlementUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.settlement.dto.SettlementDTO;
import co.com.ias.settlement.infrastructure.entrypoints.settlement.dto.SettlementInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/settlement")
@AllArgsConstructor
public class SettlementEntryPoint {

    private final SettlementUseCase settlementUseCase;

    @PostMapping
    public ResponseEntity<?> saveSettlement(@RequestBody @Valid SettlementInfoDTO settlementInfoDTO) {
        SettlementInfo settlementInfo = SettlementInfoDTO.toDomain(settlementInfoDTO);
        Settlement settlementInfoSaved = this.settlementUseCase.saveSettlement(settlementInfo);
        SettlementDTO settlementDTO = SettlementDTO.fromDomain(settlementInfoSaved);
        return ResponseEntity.status(201).body(settlementDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllSettlements() {
        List<Settlement> settlementList = this.settlementUseCase.findAllSettlements();
        List<SettlementDTO> settlementDTOList = settlementList.stream().map(SettlementDTO::fromDomain).toList();
        return ResponseEntity.status(200).body(settlementDTOList);
    }
}
