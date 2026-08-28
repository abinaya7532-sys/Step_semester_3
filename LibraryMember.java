public class LibraryMember {
    String name;
    String memberId;
    int booksIssued;
    static String libraryName = "City Library";
    static int memberCount = 1000;
    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        memberId = "LM-" + memberCount;
    }
    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }
    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
    public static void main(String[] args) {
        LibraryMember member1 =new LibraryMember("Aditi", 2);
        LibraryMember member2 =new LibraryMember("Rohan", 3);
        member1.printMemberCard();
        member2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}