abstract class LibraryItem{
    static int count=1001;
    final String itemId;
    LibraryItem(){
        itemId="LIB-"+count++;
    }
    abstract int getLoanPeriodDays();
    String getItemId(){
        return itemId;
    }
}
interface Renewable{
    String renew();
}
interface Reservable{
    String reserve();
}
class Textbook extends LibraryItem implements Renewable,Reservable{
    String title;
    Textbook(String title){
        this.title=title;
    }
    int getLoanPeriodDays(){
        return 14;
    }
    public String renew(){
        return title+" renewed";
    }
    public String reserve(){
        return title+" reserved";
    }
}
class Magazine extends LibraryItem implements Renewable{
    String title;
    Magazine(String title){
        this.title=title;
    }
    int getLoanPeriodDays(){
        return 7;
    }
    public String renew(){
        return title+" renewed";
    }
}
class DigitalPass implements Renewable{
    String resourceName;
    DigitalPass(String resourceName){
        this.resourceName=resourceName;
    }
    public String renew(){
        return resourceName+" renewed";
    }
}
public class LibraryCheckout{
    static void processCheckouts(LibraryItem[] items){
        for(LibraryItem x:items){
            System.out.println(x.getLoanPeriodDays());
        }
    }
    static String reserveIfSupported(Object o){
        if(o instanceof Reservable){
            Reservable r=(Reservable)o;
            return r.reserve();
        }
        return "Reservation not supported";
    }
    public static void main(String[] args){
        Textbook t=new Textbook("Java Fundamentals");
        Magazine m=new Magazine("Tech Monthly");
        DigitalPass d=new DigitalPass("E-Journal Access");
        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());
        System.out.println(reserveIfSupported(m));
        System.out.println(reserveIfSupported(d));
        LibraryItem ref=t;
        System.out.println(reserveIfSupported(ref));
        processCheckouts(new LibraryItem[]{t,m});
    }
}