package waggoner.com.comedyhackday.locations;

/**
 * Created by bherrup on 10/24/15.
 */
public class Location {

    private final String mLocationName;

    public Location(String locationName) {
        mLocationName = locationName;
    }

    public String getName() {
        return mLocationName;
    }
}
