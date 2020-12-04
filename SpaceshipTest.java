import oop.ex3.spaceship.ItemFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * a test class that tests the spaceship functionalites
 */
public class SpaceshipTest {
    private Spaceship myShip;
    private final int[] crewIDs = {11, 12, 13};

    @Before
    public void preTest() {
        int numLockers = 3;
        myShip = new Spaceship("BlackPearl", crewIDs, numLockers, ItemFactory.getConstraintPairs());
    }

    @Test
    public void testLongTermAvailability() {
        Assert.assertNotNull("Error: LTS is null!", myShip.getLongTermStorage());
    }

    @Test
    public void testCreateLocker1() {
        Assert.assertEquals(-1, myShip.createLocker(10, 15)); // if id isn't valid
        Assert.assertEquals(-2, myShip.createLocker(11, -10)); // negative capacity
    }

    @Test
    public void testCreateLocker2() {
        myShip.createLocker(11, 20);
        myShip.createLocker(12, 20);
        myShip.createLocker(13, 20);
        Assert.assertEquals(-3, myShip.createLocker(13, 20)); // max number of lockers reached
    }

    @Test
    public void testCreateLocker3() {
        // locker created successfully
        Assert.assertEquals(0, myShip.createLocker(13, 15));
    }

    @Test
    public void testGetCrewIDs() {
        Assert.assertArrayEquals(crewIDs, myShip.getCrewIDs());
    }

}
