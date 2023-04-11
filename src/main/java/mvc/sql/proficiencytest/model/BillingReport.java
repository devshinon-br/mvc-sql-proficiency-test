package mvc.sql.proficiencytest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingReport {
    private UUID id = UUID.randomUUID();;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private List<Ticket> tickets;

    public BigDecimal calculateBilling(final LocalDateTime initialDate, final LocalDateTime finalDate) {
        final Duration interval = Duration.between(initialDate, finalDate);

        if ( interval.toDays() > 1 && interval.toDays() <= 31) {
            return this.calculateMonthlyBilling();
        }

        if (interval.toDays() == 1 || interval.toHours() <= 24) {
            return this.calculateDailyBilling();
        }

        return new BigDecimal(0);
    }

    private BigDecimal calculateDailyBilling() {

        // TODO: do the calculation
        return new BigDecimal(0);
    }

    private BigDecimal calculateMonthlyBilling() {

        // TODO: do the calculation
        return new BigDecimal(0);
    }
}
