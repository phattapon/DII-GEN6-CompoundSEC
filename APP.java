public class APP {
    public static void main(String[] args) {
        // 🏋️‍♂️ สร้างผู้ใช้
        User user = new User("จอห์น");

        // ✅ เพิ่มสิทธิ์ให้ผู้ใช้
        user.getCard().addPermission("เข้าถึงคอร์สออกกำลังกายพื้นฐาน");
        user.getCard().addPermission("โค้ชชิ่งพรีเมียม");

        // 🛑 ลบสิทธิ์บางอย่าง
        user.getCard().revokePermission("เข้าถึงคอร์สออกกำลังกายพื้นฐาน");

        // 📜 แสดงประวัติการเปลี่ยนแปลง
        user.getCard().showAuditLog();
    }
}
