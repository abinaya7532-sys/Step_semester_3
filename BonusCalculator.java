abstract class StaffMember{
    private double baseSalary;
    double bonusRate;
    StaffMember(double baseSalary){
        this(baseSalary,0.10);
    }
    StaffMember(double baseSalary,double bonusRate){
        this.baseSalary=baseSalary;
        this.bonusRate=bonusRate;
    }
    abstract double calculateBonus();
    double getSalary(){
        return baseSalary;
    }
    void setSalary(double baseSalary){
        if(baseSalary>=0){
            this.baseSalary=baseSalary;
        }
    }
}
interface Auditable{
    String auditRecord();
}
class TeamLead extends StaffMember implements Auditable{
    int teamSize;
    TeamLead(double baseSalary,int teamSize){
        super(baseSalary);
        this.teamSize=teamSize;
    }
    TeamLead(double baseSalary,double bonusRate,int teamSize){
        super(baseSalary,bonusRate);
        this.teamSize=teamSize;
    }
    double calculateBonus(){
        return getSalary()*bonusRate;
    }
    public String auditRecord(){
        return "TeamLead audit: "+teamSize+" team members, salary $"+getSalary();
    }
}
public class BonusCalculator{
    static String getAuditIfApplicable(StaffMember s){
        if(s instanceof Auditable){
            Auditable a=(Auditable)s;
            return a.auditRecord();
        }
        return "No audit required";
    }
    public static void main(String[] args){
        TeamLead t=new TeamLead(60000,5);
        TeamLead t2=new TeamLead(60000,0.20,5);
        System.out.println(t.calculateBonus());
        System.out.println(t2.calculateBonus());
        t.setSalary(-5000);
        StaffMember ref=t;
        System.out.println(getAuditIfApplicable(ref));
    }
}