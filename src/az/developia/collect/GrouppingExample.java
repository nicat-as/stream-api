package az.developia.collect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GrouppingExample {

    public static void main(String[] args) {
        var transactions = List.of(
                new Transaction(1L, BigDecimal.valueOf(12), Transaction.Currency.AZN),
                new Transaction(2L, BigDecimal.valueOf(25), Transaction.Currency.EUR),
                new Transaction(3L, BigDecimal.valueOf(6), Transaction.Currency.USD),
                new Transaction(4L, BigDecimal.valueOf(44), Transaction.Currency.EUR),
                new Transaction(5L, BigDecimal.valueOf(54), Transaction.Currency.AZN),
                new Transaction(6L, BigDecimal.valueOf(34), Transaction.Currency.USD)
        );

        System.out.println(groupByCurrencyStream(transactions));

    }

    public static Map<Transaction.Currency, List<Transaction>> groupByCurrencyImperative(List<Transaction> transactions){
        Map<Transaction.Currency, List<Transaction>> transactionsByCurrencies =
                new HashMap<>();
        for (Transaction transaction : transactions) {
            Transaction.Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency =
                    transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies
                        .put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
        return transactionsByCurrencies;
    }

    public static Map<Transaction.Currency, List<Transaction>> groupByCurrencyStream(List<Transaction> transactions){
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
    }

}
