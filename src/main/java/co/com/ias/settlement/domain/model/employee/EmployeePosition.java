package co.com.ias.settlement.domain.model.employee;

import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class EmployeePosition {

    private final String value;

    public EmployeePosition(String value) {
        Assert.isTrue(value.length() >= 10 && value.length() <= 30, "El cargo debe contener entre 10 y 30 caracteres");
        Assert.isTrue(Pattern.matches("^[a-zA-Z0-9 ]+$", value), "El cargo no puede contener caracteres especiales");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
