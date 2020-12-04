import oop.ex3.spaceship.ItemFactory;
import oop.ex3.spaceship.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * a test class that tests the longTermStorage class functionalites
 */
public class LongTermTest {
    private static final int capacity = 1000;
    private LongTermStorage lts;
    private Item engine_obj;
    private int availableCapacity = capacity;


    @Before
    public void preTest() {
        lts = new LongTermStorage();
        engine_obj = ItemFactory.createSingleItem("spores engine");
    }

    @Test
    public void testAddItem() {
        int res1 = lts.addItem(engine_obj, 5);
        availableCapacity -= 5 * engine_obj.getVolume();
        Assert.assertEquals(0, res1);


        int res2 = lts.addItem(engine_obj, 96);
        Assert.assertEquals(-1, res2);
    }

    @Test
    public void testResetInventory() {
        lts.resetInventory();
        Assert.assertEquals(1000, lts.getAvailableCapacity());
    }

    @Test
    public void testGetItemCount() {
        lts.addItem(engine_obj, 4);
        Assert.assertEquals(4, lts.getItemCount(engine_obj.getType()));
    }

    @Test
    public void testAvailableCapacity() {
        Assert.assertEquals(availableCapacity, lts.getAvailableCapacity());
    }

    @Test
    public void testCapacity() {
        Assert.assertEquals(capacity, lts.getCapacity());
    }
}
