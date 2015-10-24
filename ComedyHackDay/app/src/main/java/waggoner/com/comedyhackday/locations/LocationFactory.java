package waggoner.com.comedyhackday.locations;

import java.util.ArrayList;

/**
 * Created by bherrup on 10/24/15.
 */
public class LocationFactory {
    public static String DUPONT = "Dupont Circle";
    public static String BUS_STOP = "The Bus Stop";
    public static String METRO = "Metro";
    public static String COFFEE_SHOP= "Coffee Shop";
    public static String BRUNCH = "Brunch";
    public static String HAPPY_HOUR = "Happy Hour";
    public static String KICKBALL = "Kickball";
    public static String NETWORKING_EVENT = "Networking Event";


    public static Location[] getLocations() {
        ArrayList<Location> locationArr = new ArrayList();
        locationArr.add(new Location(DUPONT));
        locationArr.add(new Location(BUS_STOP));
        locationArr.add(new Location(METRO));
        locationArr.add(new Location(COFFEE_SHOP));
        locationArr.add(new Location(BRUNCH));
        locationArr.add(new Location(HAPPY_HOUR));
        locationArr.add(new Location(KICKBALL));
        locationArr.add(new Location(NETWORKING_EVENT));
        return locationArr.toArray(new Location[locationArr.size()]);
    }
}
