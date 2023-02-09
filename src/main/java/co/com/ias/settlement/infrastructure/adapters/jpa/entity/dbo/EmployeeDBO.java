package co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDBO {
    @Id
    private Integer identificationId;

    private String employeeName;

    private LocalDate contractStartDate;

    private String employeePosition;

    private Double salary;

    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "employee_state_id", referencedColumnName = "id")
    private EmployeeStateDBO employeeState;


}
