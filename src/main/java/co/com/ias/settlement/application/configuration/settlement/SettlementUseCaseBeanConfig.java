package co.com.ias.settlement.application.configuration.settlement;

import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.gateways.settlement.ISettlementRepository;
import co.com.ias.settlement.domain.usecase.settlement.SettlementUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SettlementUseCaseBeanConfig {

    @Bean
    public SettlementUseCase settlementUseCase(ISettlementRepository iSettlementRepository,
                                               IEmployeeRepository iEmployeeRepository,
                                               ISalaryHistoryRepository iSalaryHistoryRepository) {
        return new SettlementUseCase(iSettlementRepository, iEmployeeRepository, iSalaryHistoryRepository);
    }
}
