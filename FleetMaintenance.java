abstract class ServiceableVehicle {
    private double mileage;
    abstract String performMaintenance();
    double getMileage() {
        return mileage;
    }
    void addMileage(double km) {
        if (km >= 0) {
            mileage += km;
        }
    }
}
interface Insurable {
    String getInsuranceInfo();
}
class Forklift extends ServiceableVehicle implements Insurable {
    String assetTag;
    Forklift(String assetTag) {
        this.assetTag = assetTag;
    }
    String performMaintenance() {
        return "Forklift " + assetTag + ": hydraulic and fork inspection complete";
    }
    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}
class HeavyDutyForklift extends Forklift {
    HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }
    String performMaintenance() {
        return super.performMaintenance() + " | high-pressure hydraulic check complete";
    }
}
public class FleetMaintenance {
    static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            Insurable x = (Insurable) v;
            return x.getInsuranceInfo();
        }
        return "No insurance record exists";
    }
    public static void main(String[] args) {
        Forklift f = new Forklift("FL-22");
        f.addMileage(120);
        System.out.println(f.getMileage());
        System.out.println(f.performMaintenance());
        HeavyDutyForklift hd = new HeavyDutyForklift("HD-9");
        System.out.println(hd.performMaintenance());
        System.out.println(getInsuranceIfApplicable(f));
    }
}