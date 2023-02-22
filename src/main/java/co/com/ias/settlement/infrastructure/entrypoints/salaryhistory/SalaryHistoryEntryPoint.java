package co.com.ias.settlement.infrastructure.entrypoints.salaryhistory;

import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.usecase.salaryhistory.SalaryHistoryUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.salaryhistory.dto.SalaryHistoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salaryhistory")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SalaryHistoryEntryPoint {
    private final SalaryHistoryUseCase salaryHistoryUseCase;

    @GetMapping
    public ResponseEntity<?> getSalaryHistories() {
        List<SalaryHistory> salaryHistories = this.salaryHistoryUseCase.getSalariesHistory();
        List<SalaryHistoryDTO> salaryHistoryDTOs = salaryHistories.stream().map(SalaryHistoryDTO::fromDomain).toList();
        return ResponseEntity.status(200).body(salaryHistoryDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSalaryHistoryByEmployeeId(@PathVariable String id) {
        List<SalaryHistory> salaryHistories = this.salaryHistoryUseCase.getSalariesHistoryByEmployeeId(id);
        List<SalaryHistoryDTO> salaryHistoryDTOs = salaryHistories.stream().map(SalaryHistoryDTO::fromDomain).toList();
        return ResponseEntity.status(200).body(salaryHistoryDTOs);
    }
}
