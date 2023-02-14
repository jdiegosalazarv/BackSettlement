package co.com.ias.settlement.domain.model.employee;

import co.com.ias.settlement.domain.usecase.utils.SettlementOperations;
import org.springframework.util.Assert;

public class Salary {

    private final Double value;

    public Salary(Double value) {
        Assert.notNull(value, "El salario es obligatorio");
        Assert.isTrue(value >= SettlementOperations.MINIMUM_WAGE, "El salario debe ser mayor a " + SettlementOperations.MINIMUM_WAGE);
        Assert.isTrue(value <= SettlementOperations.MAXIMUM_WAGE, "El salario no debe ser mayor a " + SettlementOperations.MAXIMUM_WAGE);
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
