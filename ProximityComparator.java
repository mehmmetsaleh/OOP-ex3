import java.util.Comparator;

/**
 * comparator class used to sort hotel according to proximity parameter
 */
public class ProximityComparator implements Comparator<HotelDistance> {
    /**
     * func to help sort hotels according to the proximity parameter
     *
     * @param o1 first hotel
     * @param o2 second hotel
     * @return 1, 0,-1
     */
    @Override
    public int compare(HotelDistance o1, HotelDistance o2) {
        if (o1.getEuclidianDistance() > o2.getEuclidianDistance()) {
            return 1;
        } else if (o1.getEuclidianDistance() < o2.getEuclidianDistance()) {
            return -1;
        } else {
            if (o2.getNumPOI() > o1.getNumPOI()) {
                return 1;
            } else if (o2.getNumPOI() < o1.getNumPOI()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
