import oop.ex3.spaceship.Item;

/**
 * the spaceship class
 */
public class Spaceship {

    private final int[] IDs;
    private int nLockers;
    private Item[][] constraintsArr;
    private LongTermStorage lts;
    private Locker[] lockers;

    /**
     * constructor
     *
     * @param name         name of the ship
     * @param crewIDs      list of crew members
     * @param numOfLockers number of lockers in the ship
     * @param constraints  list of constraint for all lockers in the ship
     */
    public Spaceship(String name, int[] crewIDs, int numOfLockers, Item[][] constraints) {
        IDs = crewIDs;
        nLockers = numOfLockers;
        constraintsArr = constraints;
        lts = new LongTermStorage();
        lockers = new Locker[nLockers];
    }

    /**
     * @return long term storage associated with the ship
     */
    public LongTermStorage getLongTermStorage() {
        return lts;
    }

    /**
     * @param crewID crew member id
     * @return true if crew member listed in the crew list, false otherwise
     */
    private boolean is_crew(int crewID) {
        for (int id : IDs) {
            if (id == crewID) {
                return true;
            }
        }
        return false;
    }

    /**
     * creates a locker in the ship for a specific crew member with a given capacity
     *
     * @param crewID   crew member
     * @param capacity the capacity of the locker to be created
     * @return -1 if crew id isn't related to a crew member,-2 if capacity is negative, -3 if no more locker can be
     * created due to reaching the num of max locker in this ship, 0 if the locker was created successfully
     */
    public int createLocker(int crewID, int capacity) {
        if (!is_crew(crewID)) {
            return -1;
        } else if (capacity < 0) {
            return -2;
        } else if (lockers[nLockers - 1] != null) {
            return -3;
        }
        Locker newLocker = new Locker(lts, capacity, constraintsArr);
        for (int i = 0; i < nLockers; i++) {
            if (lockers[i] == null) {
                lockers[i] = newLocker;
                break;
            }
        }
        return 0;
    }

    /**
     * @return ids of crew members
     */
    public int[] getCrewIDs() {
        return IDs;
    }

    /**
     * @return array of the lockers of the ship
     */
    public Locker[] getLockers() {
        return lockers;
    }
}
