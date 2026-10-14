
public class BusRoute {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        if (routeCode == null || routeCode.trim().isEmpty() || routeName == null || routeName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid route details");
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 3);
    }

    public int compareTo(BusRoute other) {
        int result = Integer.compare(other.priority, this.priority);
        if (result != 0)
            return result;

        result = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (result != 0)
            return result;

        return Integer.compare(this.routeName.length(), other.routeName.length());
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] result = routes.clone();

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        for (BusRoute route : rankRoutes(routes))
            System.out.println(route.getRouteCode());
    }
}
