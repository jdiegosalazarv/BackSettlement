package co.com.ias.settlement.domain.model.employee;

import org.springframework.util.Assert;

public class IdentificationId {

    private final String value;

    public IdentificationId(String value) {
        Assert.notNull(value, "La cédula no puede ser nula");
        Assert.isTrue(value.length() >= 7 && value.length() <= 17, "La cédula debe tener entre 7 y 15 caracteres");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
