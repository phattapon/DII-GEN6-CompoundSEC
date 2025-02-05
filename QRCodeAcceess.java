class QRCodeAccess extends SecurityModule {
    public QRCodeAccess() {
        super("QR Code / RFID");
    }

    @Override
    public void activate() {
        System.out.println("ระบบตรวจสอบ QR Code / RFID ทำงานแล้ว!");
    }

    public boolean verifyAccess(String userId) {
        System.out.println("ตรวจสอบ QR Code / RFID...");
        return userId.length() == 10;
    }
}
