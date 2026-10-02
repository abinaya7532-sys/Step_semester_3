interface Exportable {
    String exportData();
}
class ExportManager {
    static int count = 0;
    static int getTotalExports() {
        return count;
    }
    static void exportAll(Exportable[] items) {
        for (Exportable x : items) {
            System.out.println(x.exportData());
        }
    }
}
class ReportGenerator implements Exportable {
    String reportName;
    ReportGenerator(String reportName) {
        this.reportName = reportName;
    }
    public String exportData() {
        ExportManager.count++;
        return "Exported report: " + reportName;
    }
}
class UserProfile implements Exportable {
    String username;
    UserProfile(String username) {
        this.username = username;
    }
    public String exportData() {
        ExportManager.count++;
        return "Exported profile: " + username;
    }
}
public class DataExport {
    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");
        System.out.println(r.exportData());
        System.out.println(u.exportData());
        Exportable[] items = {r, u};
        ExportManager.exportAll(items);
        System.out.println(ExportManager.getTotalExports());
    }
}