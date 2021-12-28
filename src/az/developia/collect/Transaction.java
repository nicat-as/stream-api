package az.developia.collect;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Transaction {
    private Long id;
    private BigDecimal amount;
    private Currency currency;

    public Transaction(Long id, BigDecimal amount, Currency currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("amount=" + amount)
                .add("currency=" + currency)
                .toString();
    }

    public enum Currency {
        AZN, EUR, USD;
    }

}
