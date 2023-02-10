package co.com.ias.settlement.infrastructure.entrypoints.salaryhistory;

import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.usecase.salaryhistory.SalaryHistoryUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.salaryhistory.dto.SalaryHistoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salaryhistory")
@AllArgsConstructor
public class SalaryHistoryEntryPoint {

    private final SalaryHistoryUseCase salaryHistoryUseCase;

    @PostMapping
    public ResponseEntity<?> saveSalaryHistory(@RequestBody SalaryHistoryDTO salaryHistoryDTO) {
        SalaryHistory salaryHistory = SalaryHistoryDTO.toDomain(salaryHistoryDTO);
        String employeeId = salaryHistoryDTO.getEmployee().getIdentificationId();
        SalaryHistoryDTO salaryHistorySaved =
                SalaryHistoryDTO.fromDomain(this.salaryHistoryUseCase.saveSalaryHistory(salaryHistory, employeeId));
        return ResponseEntity.status(201).body(salaryHistorySaved);
    }
}
