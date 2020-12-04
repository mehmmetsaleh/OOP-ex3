import oop.ex3.spaceship.Item;

import java.util.Map;

/**
 * an interface that describes a general storage operations
 */
public interface Storage {

    public int addItem(Item item, int n);

    public int getItemCount(String type);

    public Map<String, Integer> getInventory();

    public int getCapacity();

    public int getAvailableCapacity();

}
