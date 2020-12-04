import oop.ex3.searchengine.Hotel;

/**
 * a helping class (like a wrapper class of Hotel) that gives enables us to calculate proximity from a certain hotel
 */
public class HotelDistance {
    private Hotel myHotel;
    private double euclidianDistance;
    private int numPOI;

    /**
     * constructor of the class
     *
     * @param myHotel           the hotel obj
     * @param euclidianDistance the euclidian distance from the hotel
     * @param numPOI            number of points of interest around the hotel
     */
    public HotelDistance(Hotel myHotel, double euclidianDistance, int numPOI) {
        this.myHotel = myHotel;
        this.euclidianDistance = euclidianDistance;
        this.numPOI = numPOI;
    }

    /**
     * @return hotel object
     */
    public Hotel getHotel() {
        return myHotel;
    }

    /**
     * @return euclidian distance
     */
    public double getEuclidianDistance() {
        return euclidianDistance;
    }

    /**
     * @return number of points of interest
     */
    public int getNumPOI() {
        return numPOI;
    }
}
