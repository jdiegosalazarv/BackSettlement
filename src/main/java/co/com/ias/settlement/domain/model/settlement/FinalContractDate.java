package co.com.ias.settlement.domain.model.settlement;

import java.time.LocalDate;

public class FinalContractDate {

    private final LocalDate value;

    public FinalContractDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
