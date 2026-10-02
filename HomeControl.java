abstract class HomeDevice {
    static int count = 1001;
    final String serialNumber;
    HomeDevice() {
        serialNumber = "HD-" + count++;
    }
    abstract String activate();
    String getSerialNumber() {
        return serialNumber;
    }
}
interface RemoteControllable {
    String connect(String appId);
}
interface EnergyTrackable {
    double getConsumptionWatts();
}
class WashingMachine extends HomeDevice implements RemoteControllable, EnergyTrackable {
    double consumptionWatts;
    WashingMachine(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }
    String activate() {
        return "Washing machine " + serialNumber + " started a cycle";
    }
    public String connect(String appId) {
        return serialNumber + " connected to " + appId;
    }
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}
class Refrigerator extends HomeDevice implements EnergyTrackable {
    double consumptionWatts;
    Refrigerator(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }
    String activate() {
        return "Refrigerator " + serialNumber + " started";
    }
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}
class MobileApp implements RemoteControllable {
    String appName;
    MobileApp(String appName) {
        this.appName = appName;
    }
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}
public class HomeControl {
    static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable x : items) {
            System.out.println(x.connect(appId));
        }
    }
    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable x = (EnergyTrackable) d;
            return x.getConsumptionWatts();
        }
        return 0;
    }
    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500);
        Refrigerator fridge = new Refrigerator(150);
        MobileApp app = new MobileApp("HomeConnect App");
        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));
        System.out.println(getConsumptionIfTrackable(fridge));
        System.out.println(app.connect("HomeConnect"));
        HomeDevice ref = wm;
        System.out.println(getConsumptionIfTrackable(ref));
    }
}