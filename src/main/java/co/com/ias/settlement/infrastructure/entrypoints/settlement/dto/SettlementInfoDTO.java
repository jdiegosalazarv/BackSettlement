package co.com.ias.settlement.infrastructure.entrypoints.settlement.dto;

import co.com.ias.settlement.domain.model.employee.IdentificationId;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.FinalContractDate;
import co.com.ias.settlement.domain.model.settlement.WithdrawalReason;
import co.com.ias.settlement.infrastructure.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SettlementInfoDTO {

    @NotBlank(message = "La cédula no puede estar vacía")
    @Size(min = 7, max = 15, message = "La cédula debe tener entre 7 y 15 caracteres")
    private String employeeId;
    private String withdrawalReason;
    @NotBlank(message = "La fecha de contrato no puede estar vacía")
    @Pattern(regexp = "^\\d{4}\\/(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])$", message = "La fecha debe cumplir con" +
            " el formato yyyy/dd/MM")
    private String finalContractDate;

    public static SettlementInfo toDomain(SettlementInfoDTO settlementInfoDTO) {
        return new SettlementInfo(
                new IdentificationId(settlementInfoDTO.getEmployeeId()),
                new WithdrawalReason(settlementInfoDTO.getWithdrawalReason()),
                new FinalContractDate(LocalDate.parse(settlementInfoDTO.getFinalContractDate(), Constants.FORMATTER))
        );
    }
}
