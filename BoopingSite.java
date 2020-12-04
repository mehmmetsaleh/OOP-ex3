import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BoopingSite class
 */
public class BoopingSite {
    private Hotel[] hotelsArr;

    /**
     * boopingSite constructor
     *
     * @param name hotels txt file and their attributes
     */
    public BoopingSite(String name) {
        hotelsArr = HotelDataset.getHotels(name);
    }

    /**
     * @param city the city to look for hotels in
     * @return array of hotels located in the given city, sorted from the highest star-rating to the lowest.
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        Hotel[] emptyArr = {};
        List<Hotel> hotelsInCityArr = new ArrayList<Hotel>();
        for (Hotel hotel : hotelsArr) {
            if (hotel.getCity().equals(city)) {
                hotelsInCityArr.add(hotel);
            }
        }
        if (hotelsInCityArr.isEmpty()) {
            return emptyArr;
        }
        Collections.sort(hotelsInCityArr, new RatingComparator());
        Hotel[] sortedArray = new Hotel[hotelsInCityArr.size()];
        return hotelsInCityArr.toArray(sortedArray);
    }

    /**
     * @param latitude  of the person looking for a hotel
     * @param longitude of the person looking for a hotel
     * @return an array of hotels, sorted according to their Euclidean distance from the given geographic
     * location, in ascending order
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude) {
        Hotel[] emptyArr = {};
        if ((latitude < -90 || latitude > 90) || (longitude < -180 || longitude > 180)) {
            return emptyArr;
        }
        List<HotelDistance> hotelsListWithDist = new ArrayList<HotelDistance>();
        for (Hotel hotel : hotelsArr) {
            double latDiff = Math.pow(latitude - hotel.getLatitude(), 2);
            double longDiff = Math.pow(longitude - hotel.getLongitude(), 2);
            double eucDist = Math.sqrt(latDiff + longDiff);
            hotelsListWithDist.add(new HotelDistance(hotel, eucDist, hotel.getNumPOI()));
        }
        Collections.sort(hotelsListWithDist, new ProximityComparator());
        Hotel[] sortedHotelsArrWithDist = new Hotel[hotelsListWithDist.size()];
        for (int i = 0; i < hotelsListWithDist.size(); i++) {
            sortedHotelsArrWithDist[i] = hotelsListWithDist.get(i).getHotel();
        }
        return sortedHotelsArrWithDist;
    }

    /**
     * @param city      the city to look for hotels in
     * @param latitude  of the person looking for a hotel
     * @param longitude of the person looking for a hotel
     * @return an array of hotels in the given city, sorted according to their Euclidean distance
     * from the given geographic location, in ascending order
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
        Hotel[] emptyArr = {};
        Hotel[] allHotelsSortedByProximity = getHotelsByProximity(latitude, longitude);
        if (allHotelsSortedByProximity.length == 0) {
            return emptyArr;
        }
        List<Hotel> hotelsInCitySortedByProximity = new ArrayList<Hotel>();
        for (Hotel hotel : allHotelsSortedByProximity) {
            if (hotel.getCity().equals(city)) {
                hotelsInCitySortedByProximity.add(hotel);
            }
        }
        if (hotelsInCitySortedByProximity.isEmpty()) {
            return emptyArr;
        }
        Hotel[] resArr = new Hotel[hotelsInCitySortedByProximity.size()];
        return hotelsInCitySortedByProximity.toArray(resArr);
    }
}
