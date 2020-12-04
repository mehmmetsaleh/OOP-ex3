import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * a class responsible for running the test classes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LockerTest.class,
        LongTermTest.class,
        SpaceshipTest.class
})

public class SpaceshipDepositoryTest {
}
