abstract class SecurityModule {
    protected String moduleName;

    public SecurityModule(String moduleName) {
        this.moduleName = moduleName;
    }

    public abstract void activate();

    public void showModuleInfo() {
        System.out.println("ระบบรักษาความปลอดภัย: " + moduleName);
    }
}
