package co.com.ias.settlement.domain.model.employee;

import java.time.LocalDate;

public class UpdateDate {

    private final LocalDate value;

    public UpdateDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
