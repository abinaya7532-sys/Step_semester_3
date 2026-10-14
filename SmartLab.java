
import java.util.*;

interface Capability {
    void apply(Device device, int value);
    String getName();
}

class Device {
    String name;
    Map<String, Capability> capabilities = new LinkedHashMap<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability c) {
        capabilities.put(c.getName(), c);
        System.out.println(name + ": " + c.getName() + " capability added.");
    }

    void set(String capability, int value) {
        Capability c = capabilities.get(capability);
        if (c == null) {
            System.out.println(name + " does not support " + capability + ".");
            return;
        }
        c.apply(this, value);
    }
}

class PowerCapability implements Capability {
    public String getName() {
        return "Power";
    }

    public void apply(Device d, int value) {
        if (value != 0 && value != 1) {
            System.out.println("Rejected: Power must be 0 or 1.");
            return;
        }
        System.out.println(d.name + ": " + (value == 1 ? "ON" : "OFF") + ".");
    }
}

class BrightnessCapability implements Capability {
    public String getName() {
        return "Brightness";
    }

    public void apply(Device d, int value) {
        if (value < 0 || value > 100) {
            System.out.println("Rejected: Brightness must be between 0% and 100%.");
            return;
        }
        System.out.println(d.name + ": brightness set to " + value + "%.");
    }
}

class TemperatureCapability implements Capability {
    public String getName() {
        return "Temperature";
    }

    public void apply(Device d, int value) {
        if (value < 16 || value > 30) {
            System.out.println("Rejected: " + d.name +
                    " temperature must be between 16°C and 30°C.");
            return;
        }
        System.out.println(d.name + ": temperature set to " + value + "°C.");
    }
}

class Scene {
    String name;
    List<Runnable> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(Runnable step) {
        steps.add(step);
    }

    void run() {
        System.out.println("Scene '" + name + "' started.");
        for (Runnable step : steps) {
            step.run();
        }
        System.out.println("Scene '" + name + "' completed.");
    }
}

public class SmartLab {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        Device lights = new Device("Ceiling Lights");
        Device projector = new Device("Projector");

        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);
        Scene scene = new Scene("Lecture Mode");

        scene.addStep(() -> devices.forEach(d -> d.set("Power", 1)));
        scene.addStep(() -> devices.forEach(d -> d.set("Brightness", 40)));
        scene.addStep(() -> devices.forEach(d -> d.set("Temperature", 24)));

        scene.run();

        ac.set("Temperature", 12);
        projector.addCapability(new BrightnessCapability());
        projector.set("Brightness", 70);
    }
}
