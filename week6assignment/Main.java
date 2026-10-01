
class GymMember {

    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Monthly fee must be positive");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
            "Standard Member | Sessions: " + sessionsAttended
        );
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
        String memberId,
        int monthlyFee,
        String trainerName
    ) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {

        System.out.println(
            "Premium Member | Trainer: "
            + trainerName
            + " | Sessions: "
            + getSessionsAttended()
        );
    }
}


// EliteMember extends PremiumMember
// GymMember → PremiumMember → EliteMember
class EliteMember extends PremiumMember {

    private String lockerNumber;

    public EliteMember(
        String memberId,
        int monthlyFee,
        String trainerName,
        String lockerNumber
    ) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public void displayInfo() {

        System.out.println(
            "Elite Member | Trainer: "
            + getTrainerName()
            + " | Locker: "
            + lockerNumber
            + " | Sessions: "
            + getSessionsAttended()
        );
    }
}


// GroupClassMember directly extends GymMember
class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(
        String memberId,
        int monthlyFee,
        String className
    ) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {

        System.out.println(
            "Group Class Member | Class: "
            + className
            + " | Sessions: "
            + getSessionsAttended()
        );
    }
}


public class Main {

    // Identify the type using instanceof only
    static String classifyGeneration(GymMember member) {

        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember) {
            return "Premium Member";
        }

        return "Standard Member";
    }


    // Polymorphism is used here
    static int getTotalSessionsAttended(GymMember[] members) {

        int total = 0;

        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }


    public static void main(String[] args) {

        GymMember member =
            new GymMember("MEM1", 1000);

        PremiumMember premium =
            new PremiumMember(
                "MEM2",
                2000,
                "Coach Riya"
            );

        EliteMember elite =
            new EliteMember(
                "MEM3",
                3000,
                "Coach Arjun",
                "L12"
            );

        GroupClassMember group =
            new GroupClassMember(
                "MEM4",
                1500,
                "Zumba"
            );


        // Display information
        member.displayInfo();
        premium.displayInfo();
        elite.displayInfo();
        group.displayInfo();


        // Classification
        System.out.println(
            classifyGeneration(elite)
        );

        System.out.println(
            classifyGeneration(group)
        );


        // Attend sessions
        premium.attendSession();
        premium.attendSession();
        premium.attendSession();

        elite.attendSession();
        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();


        // Total = 3 + 2 + 4 = 9
        System.out.println(
            getTotalSessionsAttended(
                new GymMember[] {
                    premium,
                    elite,
                    group
                }
            )
        );
    }
}

