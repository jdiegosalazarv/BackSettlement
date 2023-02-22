package co.com.ias.settlement.infrastructure.adapters.employee;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class EmployeeRepositoryAdapterTest {

    @InjectMocks
    EmployeeRepositoryAdapter employeeRepositoryAdapter;

    @Autowired
    IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;
    @Autowired
    IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;

    Employee employee;
    EmployeeDBO employeeDBO;

    EmployeeStateDBO employeeStateDBO;

    @BeforeAll
    void setUp() {
        employeeRepositoryAdapter = new EmployeeRepositoryAdapter(iEmployeeRepositoryAdapter);
    }

    @BeforeEach
    void beforeEach() {

        employeeStateDBO = new EmployeeStateDBO(
                1,
                "Activo"
        );

        employee = new Employee(
                new IdentificationId("1234567"),
                new EmployeeName("Juan"),
                new ContractStartDate(LocalDate.parse("2020-01-01")),
                new EmployeePosition("Desarrollador"),
                new Salary(2000000D),
                null,
                new EmployeeState(
                        new StateId(1),
                        new StateName("Activo")
                )
        );

        employeeDBO = EmployeeDBO.fromDomain(employee);

        this.iEmployeeStateRepositoryAdapter.save(employeeStateDBO);
        this.iEmployeeRepositoryAdapter.save(employeeDBO);

    }

    @Test
    @DisplayName("Save employee ok")
    void saveEmployee() {
        Employee employeeSaved = this.employeeRepositoryAdapter.saveEmployee(employee);

        Assertions.assertEquals(employeeDBO.getEmployeeName(), employeeSaved.getName().getValue());
    }

    @Test
    @DisplayName("find employee by identification id ok")
    void findEmployeeById() {
        Employee employeeSaved = this.employeeRepositoryAdapter.findEmployeeById("1234567");

        Assertions.assertEquals(employeeDBO.getEmployeeName(), employeeSaved.getName().getValue());
    }

    @Test
    @DisplayName("find employees ok")
    void findEmployees() {
        List<Employee> employees = this.employeeRepositoryAdapter.findEmployees();

        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals(employeeDBO.getContractStartDate(), employees.get(0).getContractStartDate().getValue());
    }

    @Test
    void updateEmployee() {
        Employee employeeModified = new Employee(
                new IdentificationId("1234567"),
                new EmployeeName("Juan"),
                new ContractStartDate(LocalDate.parse("2020-01-01")),
                new EmployeePosition("Desarrollador"),
                new Salary(3000000D),
                new UpdateEmployDate(LocalDate.parse("2022-01-01")),
                new EmployeeState(
                        new StateId(1),
                        new StateName("Activo")
                )
        );
        Employee updated = this.employeeRepositoryAdapter.updateEmployee(employeeModified);

        Assertions.assertEquals(3000000D, updated.getSalary().getValue());
    }

}