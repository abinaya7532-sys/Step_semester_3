class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;
    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }
    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        } else {
            return 0;
        }
    }
    boolean isSeverelyOverdue() {
        if (daysOverdue > 14) {
            return true;
        } else {
            return false;
        }
    }
    static double totalFineCollected(BookIssue[] books) {
        double total = 0;
        for (int i = 0; i < books.length; i++) {
            total = total + books[i].fineAmount();
        }
        return total;
    }
    public static void main(String[] args) {
        BookIssue book1 = new BookIssue("Clean Code", "Aditi", 18);
        BookIssue book2 = new BookIssue("Effective Java", "Rohan", 5);
        BookIssue book3 = new BookIssue("Refactoring", "Karan", 0);
        BookIssue book4 = new BookIssue("DSA Handbook", "Meera", 21);
        BookIssue book5 = new BookIssue("Design Patterns", "Divya", 9);
        BookIssue[] books = {book1, book2, book3, book4, book5};
        for (int i = 0; i < books.length; i++) {
            System.out.print(books[i].title + " - " + books[i].daysOverdue + " days - ");
            if (books[i].isSeverelyOverdue()) {
                System.out.println("Severely overdue");
            } else {
                System.out.println("OK");
            }
        }
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(books));
    }
}