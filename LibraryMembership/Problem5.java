class LibraryMember {
    protected int borrowLimit;
    protected int booksBorrowed;

    private static int counter = 100;

    public final String memberNumber;

    public LibraryMember(int borrowLimit) {
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        counter++;
        memberNumber = "LIB-" + counter;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }

    public static int getMembersEnrolled() {
        return counter - 100;
    }

    public static String processNightlyAudit(
        LibraryMember[] members
    ) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
            + nullSkipped + " null skipped | "
            + faculty + " faculty | "
            + regular + " regular";
    }
}


class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}
