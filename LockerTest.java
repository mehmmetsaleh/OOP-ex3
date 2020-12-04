import oop.ex3.spaceship.ItemFactory;
import oop.ex3.spaceship.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * a test class that checks the Locker class functionalites
 */
public class LockerTest {
    private final static int capacity = 20;
    private LongTermStorage lts = new LongTermStorage();
    private Locker myLocker = new Locker(lts, capacity, ItemFactory.getConstraintPairs());
    private Item bat_obj = ItemFactory.createSingleItem("baseball bat");
    private Item helm3_obj = ItemFactory.createSingleItem("helmet, size 3");
    private Item engine_obj = ItemFactory.createSingleItem("spores engine");
    private Item helm1_obj = ItemFactory.createSingleItem("helmet, size 1");
    private Item football_obj = ItemFactory.createSingleItem("football");


    @Test
    public void testAddingItems() {
        int res1 = myLocker.addItem(bat_obj, 1);
        Assert.assertEquals("Adding Error: Can't add a single item!", 0, res1);
        int res2 = myLocker.addItem(football_obj, 2);
        Assert.assertEquals("Adding Error: Constraint items shouldn't be added", -2, res2);
        int res3 = myLocker.addItem(helm3_obj, 3);
        Assert.assertEquals("Adding Error: Items should've moved to LTS,but they didn't !", 1, res3);
        int res4 = myLocker.addItem(engine_obj, 101);
        Assert.assertEquals(-1, res4); // no place for items in LTS
    }

    @Test
    public void testRemovingItems() {
        myLocker.addItem(engine_obj, 1);
        myLocker.addItem(helm1_obj, 2);
        myLocker.addItem(football_obj, 1);
        Assert.assertEquals(-1, myLocker.removeItem(engine_obj, -2));
        Assert.assertEquals(-1, myLocker.removeItem(helm1_obj, 3));
        Assert.assertEquals(-1, myLocker.removeItem(football_obj, 0));
        Assert.assertEquals(0, myLocker.removeItem(helm1_obj, 2));
        Assert.assertEquals(0, myLocker.removeItem(football_obj, 1));
    }

    @Test
    public void testCapacity() {
        Assert.assertEquals("Error: capacity val isn't right!", capacity, myLocker.getCapacity());
    }
}
