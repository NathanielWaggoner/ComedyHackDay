package waggoner.com.comedyhackday.locations;

import java.util.ArrayList;

/**
 * Created by bherrup on 10/24/15.
 */
public class LocationFactory {
    public static Location[] getLocations() {
        ArrayList<Location> locationArr = new ArrayList();
        locationArr.add(new Location("Dupont Circle"));
        locationArr.add(new Location("The Bus Stop"));
        locationArr.add(new Location("Metro"));
        locationArr.add(new Location("Coffee Shop"));
        locationArr.add(new Location("Brunch"));
        locationArr.add(new Location("Happy Hour"));
        locationArr.add(new Location("Kickball"));
        locationArr.add(new Location("Networking Event"));
        return locationArr.toArray(new Location[locationArr.size()]);
    }
}
