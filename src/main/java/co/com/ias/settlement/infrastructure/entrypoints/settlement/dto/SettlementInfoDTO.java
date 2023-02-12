package co.com.ias.settlement.infrastructure.entrypoints.settlement.dto;

import co.com.ias.settlement.domain.model.employee.IdentificationId;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.FinalContractDate;
import co.com.ias.settlement.domain.model.settlement.WithdrawalReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SettlementInfoDTO {

    private String employeeId;
    private String withdrawalReason;
    private LocalDate finalContractDate;

    public static SettlementInfo toDomain(SettlementInfoDTO settlementInfoDTO) {
        return new SettlementInfo(
                new IdentificationId(settlementInfoDTO.getEmployeeId()),
                new WithdrawalReason(settlementInfoDTO.getWithdrawalReason()),
                new FinalContractDate(settlementInfoDTO.getFinalContractDate())
        );
    }
}
