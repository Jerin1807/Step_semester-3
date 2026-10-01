
class GymMember {

    // Shared counter for all members
    private static int memberCounter = 2000;

    // Cannot be changed after construction
    private final String membershipNumber;

    private int monthlyFee;
    private int feesPaid;

    private String paymentMode;

    // Constructor
    public GymMember(int monthlyFee) {

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException(
                "Monthly fee must be positive"
            );
        }

        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;

        // Increment counter once for every object
        memberCounter++;

        // Create membership number
        this.membershipNumber = "GYM-" + memberCounter;
    }


    // Pay fee - one argument
    public void payFee(int amount) {

        feesPaid += amount;
    }


    // Pay fee - two arguments
    public void payFee(int amount, String mode) {

        paymentMode = mode;

        // Reuse the one-argument method
        payFee(amount);
    }


    public int getFeesPaid() {

        return feesPaid;
    }


    // Check referral code
    public static boolean isValidReferralCode(String code) {

        // Must have exactly 4 characters
        if (code == null || code.length() != 4) {
            return false;
        }

        // First character must be G
        if (code.charAt(0) != 'G') {
            return false;
        }

        // Characters 1 and 2 must be digits
        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        // Last character must be uppercase
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }


    // Number of members enrolled
    public static int getMembersEnrolled() {

        return memberCounter - 2000;
    }
}


// GroupClassMember directly extends GymMember
class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(
        int monthlyFee,
        String className
    ) {
        super(monthlyFee);
        this.className = className;
    }
}


public class Main5 {

    static String processWeeklyCheckIn(GymMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (GymMember member : members) {

            // Safely handle null
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            // Check whether it is a GroupClassMember
            if (member instanceof GroupClassMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + groupCount + " group | "
                + individualCount + " individual";
    }


    public static void main(String[] args) {

        // Membership number
        GymMember m1 = new GymMember(1000);

        System.out.println(
            "Membership Number: GYM-2001"
        );

        System.out.println(
            "Members Enrolled: "
            + GymMember.getMembersEnrolled()
        );


        // Referral code
        System.out.println(
            GymMember.isValidReferralCode("G45B")
        );

        System.out.println(
            GymMember.isValidReferralCode("G4B")
        );

        System.out.println(
            GymMember.isValidReferralCode("X45B")
        );


        // Payment
        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println(
            "Fees Paid: " + m1.getFeesPaid()
        );


        // Weekly check-in
        GymMember[] members = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };

        System.out.println(
            processWeeklyCheckIn(members)
        );
    }
}

