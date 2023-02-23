package co.com.ias.settlement.domain.usecase.employee;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;
import co.com.ias.settlement.domain.model.updateemployee.UpdateEmployee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeUseCaseTest {

    @InjectMocks
    EmployeeUseCase employeeUseCase;
    @Mock
    IEmployeeRepository iEmployeeRepository;
    @Mock
    ISalaryHistoryRepository iSalaryHistoryRepository;

    Employee employee;

    UpdateEmployee updateEmployee;

    SalaryHistory salaryHistory;

    @BeforeEach
    void setUp() {
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

        updateEmployee = new UpdateEmployee(
                new IdentificationId("1234567"),
                new EmployeePosition("Desarrollador"),
                new Salary(3000000D),
                new UpdateEmployDate(LocalDate.parse("2022-01-16"))
        );

        salaryHistory = new SalaryHistory(
                new NewSalary(3000000D),
                new UpdateSalaryDate(LocalDate.parse("2022-01-16")),
                employee
        );
    }

    @Test
    @DisplayName("Sabe employee ok")
    void saveEmployee() {
        when(this.iEmployeeRepository.saveEmployee(any(Employee.class))).thenReturn(employee);
        when(this.iSalaryHistoryRepository.saveSalaryHistory(any(SalaryHistory.class))).thenReturn(salaryHistory);

        Employee employeeSaved = this.employeeUseCase.saveEmployee(employee);

        Assertions.assertEquals(employee, employeeSaved);
        Assertions.assertEquals(employee.getEmployeePosition().getValue(), employeeSaved.getEmployeePosition().getValue());
    }

    @Test
    void findEmployees() {
        List<Employee> employees = Arrays.asList(employee);

        when(this.iEmployeeRepository.findEmployees()).thenReturn(employees);
        List<Employee> employeesFound = this.employeeUseCase.findEmployees();
        Assertions.assertEquals(1, employeesFound.size());
        Assertions.assertEquals("1234567", employeesFound.get(0).getIdentificationId().getValue());
    }

    @Test
    void findEmployeByid() {
        when(this.iEmployeeRepository.findEmployeeById(any(String.class))).thenReturn(employee);

        Employee employeeFound = this.employeeUseCase.findEmployeByid("1234567");

        Assertions.assertEquals("Juan", employeeFound.getName().getValue());
    }

    @Test
    void updateEmployee() {
        when(this.iEmployeeRepository.findEmployeeById(any(String.class))).thenReturn(employee);
        when(this.iEmployeeRepository.updateEmployee(any(Employee.class))).thenReturn(employee);
        when(this.iSalaryHistoryRepository.saveSalaryHistory(any(SalaryHistory.class))).thenReturn(salaryHistory);

        Employee employeeSaved = this.employeeUseCase.updateEmployee(updateEmployee);

        Assertions.assertNotNull(employeeSaved);
        Assertions.assertEquals(updateEmployee.getIdentificationId().getValue(), employeeSaved.getIdentificationId().getValue());
    }
}