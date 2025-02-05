abstract class AccessControl {
    protected String accessType;

    public AccessControl(String accessType) {
        this.accessType = accessType;
    }

    public abstract boolean verifyAccess(String userId);

    public void showAccessMethod() {
        System.out.println("ระบบตรวจสอบสิทธิ์: " + accessType);
    }
}
