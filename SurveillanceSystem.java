class SurveillanceSystem extends SecurityModule {
    public SurveillanceSystem() {
        super("Surveillance System");
    }

    @Override
    public void activate() {
        System.out.println("ระบบกล้องวงจรปิดและ AI วิเคราะห์พฤติกรรมทำงานแล้ว!");
    }

    public void analyzeFootage(String event) {
        if (event.equals("unauthorized_entry")) {
            System.out.println("แจ้งเตือน: พบบุคคลไม่ได้รับอนุญาตพยายามเข้าใช้บริการ!");
        } else if (event.equals("suspicious_behavior")) {
            System.out.println("แจ้งเตือน: พบพฤติกรรมที่ผิดปกติ กำลังแจ้งเจ้าหน้าที่รักษาความปลอดภัย!");
        } else {
            System.out.println("ไม่มีเหตุการณ์ผิดปกติที่ตรวจพบ.");
        }
    }
}