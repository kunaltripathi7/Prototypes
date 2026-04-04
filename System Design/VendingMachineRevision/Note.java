package VendingMachineRevision;

public enum Note {
    ONE(100),
    FIVE(500),
    TEN(1000),
    TWENTY(2000);

    private final int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
