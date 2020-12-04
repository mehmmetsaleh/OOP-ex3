import oop.ex3.searchengine.Hotel;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * a test class that test the BoopingSite class functionalites
 */
public class BoopingSiteTest {
    private BoopingSite siteObj1;
    private BoopingSite siteObj2;
    private BoopingSite siteObj3;

    @Before
    public void preTest() {
        siteObj1 = new BoopingSite("hotels_dataset.txt");
        siteObj2 = new BoopingSite("hotels_tst1.txt");
        siteObj3 = new BoopingSite("hotels_tst2.txt");
    }

    @Test
    public void testGetHotelsInCityByRating() {
        Hotel[] myArray = siteObj1.getHotelsInCityByRating("alleppey");
        Assert.assertEquals("carnoustie ayurveda &amp; wellness resort", myArray[0].getPropertyName());
        Assert.assertEquals("sreelakshmi residency", myArray[3].getPropertyName());
        Assert.assertEquals("xandari pearl", myArray[8].getPropertyName());

        Hotel[] myArray2 = siteObj2.getHotelsInCityByRating("manali");
        Assert.assertEquals("baragarh villa", myArray2[0].getPropertyName());
        Assert.assertEquals("the serenity resort &amp; spa", myArray2[3].getPropertyName());
        Assert.assertEquals("de vivendi resorts", myArray2[8].getPropertyName());

        Hotel[] myArray3 = siteObj3.getHotelsInCityByRating("");
        Assert.assertEquals(0, myArray3.length);
    }

    @Test
    public void testGetHotelsByProximity() {
        Hotel[] myArray = siteObj1.getHotelsByProximity(100.0, 30.0);
        Assert.assertEquals(0, myArray.length);
        Hotel[] myArray2 = siteObj1.getHotelsByProximity(80.0, 20.0);
        Assert.assertEquals("hotel shaw inn", myArray2[0].getPropertyName());

        Hotel[] myArray3 = siteObj2.getHotelsByProximity(17.0, 12.0);
        Assert.assertEquals("hotel ragini", myArray3[3].getPropertyName());

        Hotel[] myArray4 = siteObj3.getHotelsByProximity(50.0, 50.0);
        Assert.assertEquals(0, myArray4.length);
    }

    @Test
    public void testGetHotelsInCityByProximity() {
        Hotel[] myArray = siteObj1.getHotelsInCityByProximity("srinagar", 70.0, 30.0);
        Assert.assertEquals("triden kashmir resort", myArray[1].getPropertyName());

        Hotel[] myArray2 = siteObj1.getHotelsInCityByProximity("jamnagar", 30.5, 52.5);
        Assert.assertEquals("hotel punit", myArray2[0].getPropertyName());
        Assert.assertEquals(2, myArray2.length);

        Hotel[] myArray3 = siteObj2.getHotelsInCityByProximity("manali", 22.1, 23.1);
        Assert.assertEquals("snow cottage", myArray3[8].getPropertyName());
    }
}
