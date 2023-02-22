package co.com.ias.settlement.infrastructure.entrypoints.settlement;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.*;
import co.com.ias.settlement.domain.usecase.settlement.SettlementUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.settlement.dto.SettlementDTO;
import co.com.ias.settlement.infrastructure.entrypoints.settlement.dto.SettlementInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(SettlementEntryPoint.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SettlementEntryPointTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SettlementUseCase settlementUseCase;

    SettlementInfoDTO settlementInfoDTO;

    SettlementDTO settlementDTO;

    Settlement settlement;

    ObjectMapper mapper;

    @BeforeEach
    void beforeEach() {

        settlementInfoDTO = new SettlementInfoDTO("1234567", "Justified", "2021/01/01");

        settlement = new Settlement(
                new SettlementId(1),
                new Employee(
                        new IdentificationId("1234567"),
                        new EmployeeName("Juan"),
                        new ContractStartDate(LocalDate.parse("2020-01-01")),
                        new EmployeePosition("Desarrollador"),
                        new Salary(3000000D),
                        new UpdateEmployDate(LocalDate.parse("2020-03-16")),
                        new EmployeeState(
                                new StateId(1),
                                new StateName("Activo")
                        )
                ),
                new TransportationAssistance(0D),
                new FinalContractDate(LocalDate.parse("2021-01-01")),
                new WithdrawalReason("Justified"),
                new WorkingDays(366),
                new WorkingDaysActualYear(1),
                new VacationDays(15D),
                new WorkingDaysLastSemester(1),
                new BaseSalary(3000000D),
                new Severance(1700000D),
                new Vacations(3000000D),
                new SeveranceInterest(200000D),
                new ServiceBonus(1700000D),
                new PayrollPayable(150000D),
                new Bonus(0D),
                new Total(6000000D)
        );

        settlementDTO = SettlementDTO.fromDomain(settlement);


        mapper = new ObjectMapper();

    }

    @Test
    void saveSettlement() throws Exception {
        when(this.settlementUseCase.saveSettlement(any(SettlementInfo.class))).thenReturn(settlement);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/settlement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(settlementInfoDTO)))
                .andExpect(content().string(containsString((settlementDTO.getWithdrawalReason()))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getAllSettlements() throws Exception {
        List<Settlement> settlements = Arrays.asList(settlement);
        List<SettlementDTO> settlementDTOS = Arrays.asList(settlementDTO);
        when(this.settlementUseCase.findAllSettlements()).thenReturn(settlements);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/settlement"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString(settlementDTOS.get(0).getEmployeeDTO().getEmployeePosition())));
    }
}