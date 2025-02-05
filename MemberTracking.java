class MemberTracking extends SecurityModule {
    public MemberTracking() {
        super("Member Tracking");
    }

    @Override
    public void activate() {
        System.out.println("ระบบติดตามสมาชิกถูกเปิดใช้งาน!");
    }

    public void trackMember(String memberId, String zone) {
        System.out.println("สมาชิก " + memberId + " กำลังอยู่ในโซน: " + zone);
    }
}
