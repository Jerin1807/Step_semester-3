
import java.util.Arrays;

class GymMember {

    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    // Stores late fees
    private int[] lateFeeHistory = new int[10];
    private int lateFeeCount = 0;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Monthly fee must be positive");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    // Parent method
    protected void chargeLateFee(int amount) {

        if (lateFeeCount < 10) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }

    // Return defensive copy
    public int[] getLateFeeHistory() {

        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    // Calculate total late fees
    public int getTotalLateFees() {

        int total = 0;

        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
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

    // Override parent method
    @Override
    protected void chargeLateFee(int amount) {

        // Premium members pay half
        super.chargeLateFee(amount / 2);
    }
}


public class Demo {

    public static void main(String[] args) {

        PremiumMember p =
            new PremiumMember(
                "MEM5",
                2000,
                "Coach Riya"
            );

        // Original fee = 200
        p.chargeLateFee(200);

        // Premium discount → 100
        System.out.println(
            p.getTotalLateFees()
        );

        // Get history
        int[] history = p.getLateFeeHistory();

        // Try to modify returned array
        history[0] = 999;

        // Original internal history is still 100
        System.out.println(
            Arrays.toString(
                p.getLateFeeHistory()
            )
        );
    }
}

