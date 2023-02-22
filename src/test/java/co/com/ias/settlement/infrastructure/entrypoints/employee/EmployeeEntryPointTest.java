package co.com.ias.settlement.infrastructure.entrypoints.employee;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.updateemployee.UpdateEmployee;
import co.com.ias.settlement.domain.usecase.employee.EmployeeUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.EmployeeDTO;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.UpdateEmployeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

@WebMvcTest(EmployeeEntryPoint.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeEntryPointTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeUseCase employeeUseCase;

    Employee employee;
    EmployeeDTO employeeDTO;

    ObjectMapper mapper;

    @BeforeEach
    void beforeEach() {

        employee = new Employee(
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
        );

        employeeDTO = EmployeeDTO.fromDomain(employee);

        mapper = new ObjectMapper();

    }

    @Test
    @DisplayName("Save employee ok")
    void saveEmployee() throws Exception {
        when(this.employeeUseCase.saveEmployee(any(Employee.class))).thenReturn(employee);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(employeeDTO)))
                .andExpect(content().string(containsString((employeeDTO.getIdentificationId()))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Find employee ok")
    void findEmployeeById() throws Exception {
        when(this.employeeUseCase.findEmployeByid(any(String.class))).thenReturn(employee);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", "1234567"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString(employeeDTO.getIdentificationId())))
                .andExpect(content().string(containsString(employeeDTO.getEmployeePosition())));
    }

    @Test
    @DisplayName("FindEmployees ok")
    void findEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee);
        List<EmployeeDTO> employeeDTOS = Arrays.asList(employeeDTO);
        when(this.employeeUseCase.findEmployees()).thenReturn(employees);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString(employeeDTOS.get(0).getIdentificationId())));
    }

    @Test
    void updateEmployee() throws Exception {
        UpdateEmployeeDTO updateEmployeeDTO = new UpdateEmployeeDTO("1234567", "Desarrollador", 3000000D, "2022/16/01");
        UpdateEmployee updateEmployee = UpdateEmployeeDTO.toDomain(updateEmployeeDTO);

        when(this.employeeUseCase.updateEmployee(any(UpdateEmployee.class))).thenReturn(employee);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateEmployeeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString(employeeDTO.getUpdateEmployeeDate())))
                .andExpect(content().string(containsString(employeeDTO.getEmployeeState().getStateName())));

    }

    @Test
    void deleteEmployee() {
    }
}