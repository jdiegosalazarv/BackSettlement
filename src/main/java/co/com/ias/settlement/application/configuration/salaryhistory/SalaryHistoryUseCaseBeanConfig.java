package co.com.ias.settlement.application.configuration.salaryhistory;

import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.usecase.salaryhistory.SalaryHistoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalaryHistoryUseCaseBeanConfig {

    @Bean
    public SalaryHistoryUseCase salaryHistoryUseCase(ISalaryHistoryRepository iSalaryHistoryRepository) {
        return new SalaryHistoryUseCase(iSalaryHistoryRepository);
    }
}
