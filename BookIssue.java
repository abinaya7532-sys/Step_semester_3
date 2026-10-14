public class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5.0 : 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // totalFineCollected is static because it adds fines across many books.
    // fineAmount is an instance method because each book has its own overdue days.
    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (BookIssue issue : issues)
            total += issue.fineAmount();
        return total;
    }

    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Ravi", 18),
            new BookIssue("Effective Java", "Anitha", 5),
            new BookIssue("Refactoring", "Karthik", 0),
            new BookIssue("DSA Handbook", "Meera", 21),
            new BookIssue("Design Patterns", "Suresh", 9)
        };

        for (BookIssue b : issues) {
            System.out.println(b.title + " - " + b.daysOverdue +
                    " days - " +
                    (b.isSeverelyOverdue() ? "Severely overdue" : "OK"));
        }

        System.out.println("Total fine collected: Rs " +
                totalFineCollected(issues));
    }
}