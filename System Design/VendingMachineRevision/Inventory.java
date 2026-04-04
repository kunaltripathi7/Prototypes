package VendingMachineRevision;

public class Inventory {
    private ItemShelf[] inventory;

    public Inventory(int size) {
        inventory = new ItemShelf[size];
        initialEmptyInventory();
    }

    public ItemShelf[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemShelf[] inventory) {
        this.inventory = inventory;
    }

    private void initialEmptyInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {
            ItemShelf space = new ItemShelf();
            space.setCode(startCode);
            space.setSoldOut(true);
            inventory[i] = space;
            startCode++;
        }
    }

    public void addItem(Item item, int code) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == code) {
                if (itemShelf.isSoldOut()) {
                    itemShelf.setItem(item);
                    itemShelf.setSoldOut(false);
                } else {
                    throw new Exception("Shelf is already occupied");
                }
            }
        }
    }

    public Item getItem(int code) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == code) {
                if (itemShelf.isSoldOut()) {
                    throw new Exception("Item is sold out");
                } else {
                    return itemShelf.getItem();
                }
            }
        }
        throw new Exception("Invalid code");
    }

    public void updateSoldOutItem(int code) {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == code) {
                itemShelf.setSoldOut(true);
            }
        }
    }
}
