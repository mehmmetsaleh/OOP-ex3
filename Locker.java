import java.util.HashMap;
import java.util.Map;

import oop.ex3.spaceship.*;

/**
 * a locker class
 */
public class Locker implements Storage {

    private LongTermStorage lts;
    private final int capacity;
    private Item[][] constraints;
    private Map<String, Integer> inv;

    /**
     * locker constructor
     *
     * @param lts         LongTermStorage associated with this locker
     * @param capacity    - full capacity of this locker
     * @param constraints list of constraints associated with this locker
     */
    public Locker(LongTermStorage lts, int capacity, Item[][] constraints) {
        this.lts = lts;
        this.capacity = capacity;
        this.constraints = constraints;
        inv = new HashMap<String, Integer>();
    }

    /**
     * checks that the item given doesn't contradict with the inventory of the locker
     *
     * @param itemToBeAdded - item to add to locker
     * @return true if no contradictions, otherwise false
     */
    private boolean areConstraintsOkay(Item itemToBeAdded) {
        for (Item[] constraint : constraints) {
            if (itemToBeAdded.getType().equals(constraint[0].getType())) {
                if (inv.get(constraint[1].getType()) != null) {
                    return false;
                }
            } else if (itemToBeAdded.getType().equals(constraint[1].getType())) {
                if (inv.get(constraint[0].getType()) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * adds n times of the item to the locker when adding is possible
     *
     * @param item item to be added
     * @param n    num of times to add the item
     */
    private void insertItemsToLocker(Item item, int n) {
        if (!inv.containsKey(item.getType())) {
            inv.put(item.getType(), n);
        } else {
            inv.put(item.getType(), inv.get(item.getType()) + n);
        }
    }

    /**
     * this function adds n times the item to the locker
     *
     * @param item item to be added
     * @param n    number of times to  add the item
     * @return 0 if added and no problems, 1 if added but caused items to move to LTS, -1 if cannot be added,
     * and -2 if the item contradicts with the inventory (and cannot be added)
     */
    public int addItem(Item item, int n) {
        int neededStorage = item.getVolume() * n;
        int itemsSize = getItemCount(item.getType()) * item.getVolume();

        if (!areConstraintsOkay(item)) {
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: the locker cannot contain items of type " + item.getType() +
                    ", as it contains a contradicting item");
            return -2;
        } else if (neededStorage > getAvailableCapacity()) {
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: no room for " + n + " items of type " + item.getType());
            return -1;
        } else if (itemsSize + neededStorage > 0.5 * capacity) { //TODO: check if to delete itemsSize
            int nOf20Percent = (int) Math.floor(0.2 * capacity / item.getVolume());
            int nOfRemaining = n - nOf20Percent;
            int addToLtsRes = lts.addItem(item, nOfRemaining);
            if (addToLtsRes == -1) {
                System.out.println("Error: Your request cannot be completed at this time." +
                        " Problem: no room for " + nOfRemaining + " items of type " + item.getType());
                return -1;
            } else {
                System.out.println("Warning: Action successful, but has caused items to be moved to storage");
                return 1;
            }
        } else {
            insertItemsToLocker(item, n);
            return 0;
        }
    }

    /**
     * removes n times the item from the locker if possible
     *
     * @param item the item to be removed
     * @param n    number of times to remove the item
     * @return -1 if cannot be removed, 0 if removed successfully
     */
    public int removeItem(Item item, int n) {
        int count = getItemCount(item.getType());
        if (count < n) {
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: the locker does not contain " + n + " items of type " + item.getType());
            return -1;
        } else if (n < 0) {
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: cannot remove a negative number of items of type " + item.getType());
            return -1;
        } else if (n == 0) {
            System.out.println("Error: Your request cannot be completed at this time.");
            return -1;
        }
        inv.put(item.getType(), count - n);
        return 0;
    }

    /**
     * @param type item type
     * @return count of the item in the inventory of the locker
     */
    public int getItemCount(String type) {
        Integer count = inv.get(type);
        if (count == null) {
            return 0;
        }
        return inv.get(type);
    }

    /**
     * @return the inventory of the locker
     */
    public Map<String, Integer> getInventory() {
        return inv;
    }

    /**
     * @return the full capacity of the locker
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the available capacity of the locker
     */
    public int getAvailableCapacity() {
        int occupied = 0;
        for (String type : inv.keySet()) {
            Item type_obj = ItemFactory.createSingleItem(type);
            if (inv.get(type) != null && type_obj != null) {
                occupied += inv.get(type) * type_obj.getVolume();
            }
        }
        return capacity - occupied;
    }
}
