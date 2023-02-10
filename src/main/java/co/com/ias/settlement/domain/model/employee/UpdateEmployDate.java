package co.com.ias.settlement.domain.model.employee;

import java.time.LocalDate;

public class UpdateEmployDate {

    private final LocalDate value;

    public UpdateEmployDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
