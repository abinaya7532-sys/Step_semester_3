class BrokenMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenMember(String name, String memberId, int booksIssued) {
        BrokenMember.name = name;
        BrokenMember.memberId = memberId;
        BrokenMember.booksIssued = booksIssued;
    }
}

class CorrectMember {
    String name;
    String memberId;
    int booksIssued;
    static String libraryName = "SRM Library";
    static int memberCount = 0;

    CorrectMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class LibraryMemberDemo {
    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenMember m1 = new BrokenMember("Aditi", "LM-1001", 2);
        BrokenMember m2 = new BrokenMember("Rohan", "LM-1002", 3);
        System.out.println(m1.name);
        System.out.println(m2.name);

        // name, memberId, and booksIssued must be instance fields because
        // each member has separate data. Static fields are shared by all members.

        System.out.println("Fixed version:");
        CorrectMember a = new CorrectMember("Aditi", 2);
        CorrectMember b = new CorrectMember("Rohan", 3);
        a.printMemberCard();
        b.printMemberCard();
        CorrectMember.printTotalMembers();
    }
}