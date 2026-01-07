import java.util.ArrayList;
import java.util.List;

public class ItemShelf {
    private int shelfCode;
    private List<Item> items;
    private Item_Type itemType;
    boolean isOutOfStock;

    public ItemShelf(int shelfCode) {
        this.shelfCode = shelfCode;
        this.items = new ArrayList<Item>();
    }

    public void updateShelf() {
        isOutOfStock = items.isEmpty();
    }

    public void addItem(Item item) {
        if (item.getType() == this.itemType) {
            items.add(item);
            updateShelf();
        }
    }

    public void removeItem(Item item) {
        if (item.getType() == this.itemType) {
            items.remove(item);
            updateShelf();
        }
    }

    //getters & setters

    public Item_Type getItemType() {
        return itemType;
    }

    public int getShelfCode() {
        return shelfCode;
    }


    public List<Item> getItems() {
        return items;
    }
}
