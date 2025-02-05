public class Main {
    public static void main(String[] args) {
        SecurityModule cardAccess = new CardAccess();
        SecurityModule faceAccess = new FacialRecognition();
        SecurityModule qrAccess = new QRCodeAccess();
        SecurityModule surveillance = new SurveillanceSystem();
        SecurityModule memberTracking = new MemberTracking();

        System.out.println("\nทดสอบการตรวจสอบสิทธิ์:\n");

        cardAccess.showModuleInfo();
        cardAccess.activate();
        System.out.println("ผลลัพธ์: " + (((CardAccess) cardAccess).verifyAccess("MEMBER-12345") ? "อนุญาต" : "ปฏิเสธ") + "\n");

        faceAccess.showModuleInfo();
        faceAccess.activate();
        System.out.println("ผลลัพธ์: " + (((FacialRecognition) faceAccess).verifyAccess("AUTHORIZED_FACE") ? "อนุญาต" : "ปฏิเสธ") + "\n");

        qrAccess.showModuleInfo();
        qrAccess.activate();
        System.out.println("ผลลัพธ์: " + (((QRCodeAccess) qrAccess).verifyAccess("1234567890") ? "อนุญาต" : "ปฏิเสธ") + "\n");

        surveillance.showModuleInfo();
        surveillance.activate();
        ((SurveillanceSystem) surveillance).analyzeFootage("unauthorized_entry");
        ((SurveillanceSystem) surveillance).analyzeFootage("suspicious_behavior");
        ((SurveillanceSystem) surveillance).analyzeFootage("normal_activity");

        memberTracking.showModuleInfo();
        memberTracking.activate();
        ((MemberTracking) memberTracking).trackMember("USER-001", "โซนคาร์ดิโอ");
        ((MemberTracking) memberTracking).trackMember("USER-002", "ห้องโยคะ");
    }
}
