import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * the long term storage class
 */
public class LongTermStorage implements Storage {

    private final static int capacity = 1000;
    private Map<String, Integer> longTermInv;

    /**
     * constructor
     */
    public LongTermStorage() {
        longTermInv = new HashMap<String, Integer>();

    }

    /**
     * adds n time the item into the long term storage
     *
     * @param item item to be added
     * @param n    the number of times to add the item
     */
    private void insertItemsToLocker(Item item, int n) {
        if (!longTermInv.containsKey(item.getType())) {
            longTermInv.put(item.getType(), n);
        } else {
            longTermInv.put(item.getType(), longTermInv.get(item.getType()) + n);
        }
    }

    /**
     * adds n time the item into the long term storage
     *
     * @param item item to be added
     * @param n    the number of times to add the item
     */
    public int addItem(Item item, int n) {
        int neededStorage = item.getVolume() * n;
        if (neededStorage > getAvailableCapacity()) {
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: no room for " + n + " items of type " + item.getType());
            return -1;
        }
        insertItemsToLocker(item, n);
        return 0;
    }

    /**
     * clears the long term storage out of items
     */
    public void resetInventory() {
        longTermInv.clear();
    }

    /**
     * @param type item type
     * @return the count of certain item with the given type
     */
    public int getItemCount(String type) {
        Integer count = longTermInv.get(type);
        if (count == null) {
            return 0;
        }
        return count;
    }

    /**
     * @return inventory of the lts
     */
    public Map<String, Integer> getInventory() {
        return longTermInv;
    }

    /**
     * @return the total capacity of the lts
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the unused capacity of the lts
     */
    public int getAvailableCapacity() {
        int occupied = 0;
        for (String type : longTermInv.keySet()) {
            Item type_item = ItemFactory.createSingleItem(type);
            if (longTermInv.get(type) != null && type_item != null) {
                occupied += longTermInv.get(type) * type_item.getVolume();
            }
        }
        return capacity - occupied;
    }

}
