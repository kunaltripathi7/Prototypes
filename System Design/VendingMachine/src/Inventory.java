public class Inventory {
    private ItemShelf[] itemShelves;

    public Inventory(int itemShelvesCount) {
        itemShelves = new ItemShelf[itemShelvesCount];
        initializeEmptyInventory();
    }

    public void initializeEmptyInventory() {
       int startCode = 101;
         for (int i=0; i<itemShelves.length; i++) {
            ItemShelf space = new ItemShelf(startCode++);
        }
    }

    public int findAvailability(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        for (ItemShelf itemShelf : itemShelves) {
            if (itemShelf.getItemType() == item.getType() && !itemShelf.isOutOfStock) {
                return itemShelf.getShelfCode();
            }
        }
//        return -1;
        throw new IllegalArgumentException("Item does not exist");
    }


    public ItemShelf[] getInventory() {
        return itemShelves;
    }

    public void setInventory(ItemShelf[] itemShelves) {
        this.itemShelves = itemShelves;
    }
//    public Item getItem(Item item) { // error -> not aware

    public Item getItem(int codeNumber) {
        if (codeNumber < 0 ) throw new IllegalArgumentException("Item cannot be null");
        Item item = null;
        for (ItemShelf itemShelf : itemShelves) {
            if (itemShelf.getShelfCode()  == codeNumber && !itemShelf.isOutOfStock) {
                item = itemShelf.getItems().get(0);
            }
        }
        return item;
    }

    //similarly removeItem

    public Item dispenseItem(int codeNumber) {
        // logic for dispensing
        return null;
    }

}
