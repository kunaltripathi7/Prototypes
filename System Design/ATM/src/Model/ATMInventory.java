package Model;

import Enums.CurrDenomination;

import java.util.HashMap;
import java.util.Map;

public class ATMInventory {
    private final Map<CurrDenomination, Integer> currencyStock = new HashMap<>();

    public void addMoney(CurrDenomination cashType, int quantity) {
       currencyStock.put(cashType,currencyStock.getOrDefault(cashType, 0)+quantity);
    }

    private boolean checkDispenseLimit(double amount) {
        if (amount < 1) throw new IllegalArgumentException("Amount can't be negative.");
        double totalAmount = 0.00;
        for (Map.Entry<CurrDenomination, Integer> entry : currencyStock.entrySet()) {
            totalAmount += (entry.getKey().getValue() * entry.getValue());
        }
        return totalAmount >= amount;
    }

    public boolean processWithdrawl(double amount) {
        if (!checkDispenseLimit(amount)) return false;
        for ()
    }
}
