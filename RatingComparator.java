import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

/**
 * a comparator so that we can sort hotels according to their star rating
 */
public class RatingComparator implements Comparator<Hotel> {

    /**
     * func to help sort hotels according to star rating
     *
     * @param o1 first hotel
     * @param o2 second hotel
     * @return 1, 0,-1
     */
    @Override
    public int compare(Hotel o1, Hotel o2) {
        if (o1.getStarRating() > o2.getStarRating()) {
            return -1;
        } else if (o1.getStarRating() < o2.getStarRating()) {
            return 1;
        } else {
            return o1.getPropertyName().compareTo(o2.getPropertyName());
        }
    }
}
