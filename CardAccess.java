class CardAccess extends SecurityModule {
    public CardAccess() {
        super("บัตรสมาชิก (PermissionCard)");
    }

    @Override
    public void activate() {
        System.out.println("ระบบตรวจสอบสิทธิ์ผ่านบัตรสมาชิกทำงานแล้ว!");
    }

    public boolean verifyAccess(String userId) {
        System.out.println("ตรวจสอบสิทธิ์ผ่านบัตรสมาชิก...");
        return userId.startsWith("MEMBER-");
    }
}