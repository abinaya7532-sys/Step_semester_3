interface Alertable{
    String sendAlert(String message);
}
class SecuritySensor{
    String zoneName;
    SecuritySensor(String zoneName){
        this.zoneName=zoneName;
    }
    String getZoneName(){
        return zoneName;
    }
}
class MotionSensor extends SecuritySensor implements Alertable{
    MotionSensor(String zoneName){
        super(zoneName);
    }
    public String sendAlert(String message){
        return "["+zoneName+"] "+message;
    }
}
class DualZoneMotionSensor extends MotionSensor{
    String secondZoneName;
    DualZoneMotionSensor(String zoneName,String secondZoneName){
        super(zoneName);
        this.secondZoneName=secondZoneName;
    }
    public String sendAlert(String message){
        return super.sendAlert(message)+" [also covering "+secondZoneName+"]";
    }
}
class SmokeDetector implements Alertable{
    String deviceId;
    SmokeDetector(String deviceId){
        this.deviceId=deviceId;
    }
    public String sendAlert(String message){
        return "["+deviceId+"] "+message;
    }
}
public class HomeSafety{
    static void broadcastAll(Alertable[] devices,String message){
        for(Alertable x:devices){
            System.out.println(x.sendAlert(message));
        }
    }
    static String getZoneIfMotionSensor(Alertable a){
        if(a instanceof MotionSensor){
            MotionSensor m=(MotionSensor)a;
            return m.getZoneName();
        }
        return "Not a motion sensor";
    }
    public static void main(String[] args){
        MotionSensor m=new MotionSensor("Living Room");
        DualZoneMotionSensor d=new DualZoneMotionSensor("Hallway","Stairwell");
        SmokeDetector s=new SmokeDetector("SD-01");
        System.out.println(m.sendAlert("Motion detected"));
        System.out.println(d.sendAlert("Motion detected"));
        System.out.println(s.sendAlert("Smoke detected"));
        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));
        broadcastAll(new Alertable[]{m,d,s},"Alert detected");
    }
}