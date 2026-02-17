package Model;

import Enums.CurrDenomination;

import java.util.HashMap;
import java.util.Map;

public class ATMInventory {
    private final Map<CurrDenomination, Integer> currencyStock = new HashMap<>();


    public ATMInventory() {
        intializeInventory();
    }


    private void intializeInventory() {
        currencyStock.put(CurrDenomination.Hundred, 10);
        currencyStock.put(CurrDenomination.FIFTY, 10);
        currencyStock.put(CurrDenomination.TWENTY, 10);
        currencyStock.put(CurrDenomination.TEN, 10);
        currencyStock.put(CurrDenomination.FIVE, 10);
        currencyStock.put(CurrDenomination.TWO, 10);
        currencyStock.put(CurrDenomination.ONE, 10);
    }

    public void addMoney(CurrDenomination cashType, int quantity) {
       currencyStock.put(cashType,currencyStock.getOrDefault(cashType, 0)+quantity);
    }

    private double getTotalCash() {
        double totalAmount = 0.00;
        for (Map.Entry<CurrDenomination, Integer> entry : currencyStock.entrySet()) {
            totalAmount += (entry.getKey().getValue() * entry.getValue());
        }
        return totalAmount;
    }

    public Map<CurrDenomination, Integer> processWithdrawl(int amount) {
        // when you are facing issues -> break down the operations.
        Map<CurrDenomination, Integer> dispenseDenominations = new HashMap<>();
        int  remaining = amount;
        double totalCash = getTotalCash();
        if (totalCash < amount) return null;
        for (CurrDenomination currDenomination : CurrDenomination.values()) {
            int denomValue = currDenomination.getValue();
            int availableNotes = currencyStock.get(currDenomination);
            int neededNotes = remaining / denomValue;
            remaining -= (denomValue*availableNotes);
            if (neededNotes >= availableNotes) {
                currencyStock.put(currDenomination, 0);
                dispenseDenominations.put(currDenomination, availableNotes);
            }
            else {
                currencyStock.put(currDenomination, availableNotes-neededNotes);
                dispenseDenominations.put(currDenomination, neededNotes);
            }
            if (remaining == 0) {
                return dispenseDenominations;
            }
        }
        return null;
    }
}
