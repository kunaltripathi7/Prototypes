import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private final Map<Integer, Item> items = new HashMap<>();

    public void addItem(int code, Item item) {
        items.put(code, item);
    }

    public boolean isAvailable(int code) {
        return items.containsKey(code);
    }

    public Item dispense(int code) {
        if (!items.containsKey(code)) {
            throw new IllegalStateException("Item not available");
        }
        return items.remove(code);
    }
}
