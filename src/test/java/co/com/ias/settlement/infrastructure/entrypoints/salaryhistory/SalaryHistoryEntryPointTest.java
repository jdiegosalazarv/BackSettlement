package co.com.ias.settlement.infrastructure.entrypoints.salaryhistory;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;
import co.com.ias.settlement.domain.usecase.salaryhistory.SalaryHistoryUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.salaryhistory.dto.SalaryHistoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(SalaryHistoryEntryPoint.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SalaryHistoryEntryPointTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SalaryHistoryUseCase salaryHistoryUseCase;

    SalaryHistoryDTO salaryHistoryDTO;

    SalaryHistory salaryHistory;

    @BeforeEach
    void beforeEach() {
        salaryHistory = new SalaryHistory(
                new NewSalary(3000000D),
                new UpdateSalaryDate(LocalDate.parse("2020-01-01")),
                new Employee(
                        new IdentificationId("1234567"),
                        new EmployeeName("Juan"),
                        new ContractStartDate(LocalDate.parse("2020-01-01")),
                        new EmployeePosition("Desarrollador"),
                        new Salary(3000000D),
                        new UpdateEmployDate(LocalDate.parse("2022-01-16")),
                        new EmployeeState(
                                new StateId(1),
                                new StateName("Activo")
                        )
                )
        );

        salaryHistoryDTO = SalaryHistoryDTO.fromDomain(salaryHistory);

    }

    @Test
    void getSalaryHistories() throws Exception {

        List<SalaryHistory> salaryHistories = Arrays.asList(salaryHistory);
        List<SalaryHistoryDTO> salaryHistoryDTOS = Arrays.asList(salaryHistoryDTO);
        when(this.salaryHistoryUseCase.getSalariesHistory()).thenReturn(salaryHistories);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/salaryhistory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString(salaryHistoryDTOS.get(0).getEmployee().getEmployeeName())));
    }

    @Test
    void getSalaryHistoryByEmployeeId() throws Exception {

        List<SalaryHistory> salaryHistories = Arrays.asList(salaryHistory);
        List<SalaryHistoryDTO> salaryHistoryDTOS = Arrays.asList(salaryHistoryDTO);
        when(this.salaryHistoryUseCase.getSalariesHistoryByEmployeeId("1234567")).thenReturn(salaryHistories);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/salaryhistory/{employeeId}", "1234567"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString(salaryHistoryDTOS.get(0).getEmployee().getEmployeePosition())));
    }
}