package Enums;

public enum CurrDenomination {
    Hundred(100),
    FIFTY(50),
    TWENTY(20),
    TEN(10),
    FIVE(5),
    TWO(2),
    ONE(1);

    private final int value;

    CurrDenomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
