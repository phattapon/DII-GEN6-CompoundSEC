import java.util.*;

public class PermissionCard {

    public class PermissionCard {
        private Set<String> permissions;
        private List<String> auditLog;

        public PermissionCard() {
            this.permissions = new HashSet<>();
            this.auditLog = new ArrayList<>();
        }

        public void addPermission(String permission) {
            permissions.add(permission);
            auditLog.add("เพิ่มสิทธิ์: " + permission + " - " + new Date()); // 🕒 บันทึกเวลาที่เพิ่ม
        }

        public void revokePermission(String permission) {
            permissions.remove(permission);
            auditLog.add("ลบสิทธิ์: " + permission + " - " + new Date()); //
        }

        public boolean hasPermission(String permission) {
            return permissions.contains(permission);
        }

        public void showAuditLog() {
            System.out.println("📜 ประวัติการเปลี่ยนแปลงสิทธิ์:");
            for (String log : auditLog) {
                System.out.println(log);
            }
        }

        @Override
        public String toString() {
            return "🔐 สิทธิ์ทั้งหมดตอนนี้: " + permissions;
        }
    }

}
