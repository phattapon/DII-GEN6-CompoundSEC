    class FacialRecognition extends SecurityModule {
        public FacialRecognition() {
            super("การจดจำใบหน้า (Facial Recognition)");
        }

        @Override
        public void activate() {
            System.out.println("ระบบจดจำใบหน้าทำงานแล้ว!");
        }

        public boolean verifyAccess(String userId) {
            System.out.println("กำลังตรวจสอบใบหน้าของ " + userId + "...");
            return userId.equals("AUTHORIZED_FACE");
        }
    }
