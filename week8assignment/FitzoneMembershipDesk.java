public class FitZoneMembershipDesk {
    enum Status { ACTIVE, FROZEN, EXPIRED }

    interface MembershipPlan {
        int months();
        double discount();
        String name();

        default double calculateFee() {
            return 1000.0 * months() * (1 - discount());
        }
    }

    static class Monthly implements MembershipPlan {
        public int months() { return 1; }
        public double discount() { return 0; }
        public String name() { return "Monthly"; }
    }

    static class Quarterly implements MembershipPlan {
        public int months() { return 3; }
        public double discount() { return 0.10; }
        public String name() { return "Quarterly"; }
    }

    static class Annual implements MembershipPlan {
        public int months() { return 12; }
        public double discount() { return 0.25; }
        public String name() { return "Annual"; }
    }

    static class Member {
        final String name;
        Membership membership;

        Member(String name) { this.name = name; }

        Membership buy(MembershipPlan plan) {
            membership = new Membership(this, plan);
            return membership;
        }
    }

    static class Membership {
        final Member member;
        final MembershipPlan plan;
        private Status status = Status.ACTIVE;

        Membership(Member member, MembershipPlan plan) {
            this.member = member; this.plan = plan;
        }

        Status getStatus() { return status; }

        void freeze() {
            requireNotExpired("freeze");
            if (status != Status.ACTIVE) throw new IllegalStateException("Membership is not Active");
            status = Status.FROZEN;
        }

        void unfreeze() {
            requireNotExpired("unfreeze");
            if (status != Status.FROZEN) throw new IllegalStateException("Membership is not Frozen");
            status = Status.ACTIVE;
        }

        void expire() { status = Status.EXPIRED; }

        boolean checkIn() { return status == Status.ACTIVE; }

        private void requireNotExpired(String action) {
            if (status == Status.EXPIRED)
                throw new IllegalStateException("Cannot " + action + " an Expired membership");
        }
    }
}