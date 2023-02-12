package co.com.ias.settlement.domain.model.employee;

import org.springframework.util.Assert;

public class EmployeeName {

    private final String value;

    public EmployeeName(String value) {
        Assert.notNull(value, "El nombre no puede ser nulo");
        Assert.hasLength(value, "El nombre no puede ser vacio");
        Assert.isTrue(value.length() <= 50, "El nombre no puede tener mas de 50 caracteres");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
