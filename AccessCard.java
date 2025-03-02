public class AccessCard {
    private String cardId;
    private int startDay;
    private int endDay;
    private String startTime;
    private String endTime;
    private String cardType;  // Silver, Gold, Platinum

    // Constructor
    public AccessCard(String cardId, int startDay, int endDay, String startTime, String endTime, String cardType) {
        this.cardId = cardId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cardType = cardType;
    }

    // Getter for cardId
    public String getCardId() {
        return cardId;
    }

    // Getter and Setter for cardType
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public boolean isAccessAllowed(int currentDay, String currentTime) {
        System.out.println("Checking Access - Card ID: " + cardId);
        System.out.println("Allowed Days: " + startDay + " to " + endDay);
        System.out.println("Allowed Time: " + startTime + " to " + endTime);
        System.out.println("Current Day: " + currentDay);
        System.out.println("Current Time: " + currentTime);

        boolean dayCheck = currentDay >= startDay && currentDay <= endDay;
        boolean timeCheck = currentTime.compareTo(startTime) >= 0 && currentTime.compareTo(endTime) <= 0;

        System.out.println("Day Check: " + dayCheck);
        System.out.println("Time Check: " + timeCheck);

        return dayCheck && timeCheck;
    }


    // เพิ่มเมธอด setAccessPeriod เพื่อแก้ไขช่วงวันและเวลา
    public void setAccessPeriod(int startDay, int endDay, String startTime, String endTime) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // ฟังก์ชันตรวจสอบการเข้าถึงชั้นต่างๆ
    public boolean canAccessLevel(String level) {
        switch (this.cardType) {
            case "Silver":
                return level.equals("Silver");  // Silver สามารถเข้าชั้น Silver เท่านั้น
            case "Gold":
                return level.equals("Silver") || level.equals("Gold");  // Gold สามารถเข้าชั้น Silver และ Gold
            case "Platinum":
                return true;  // Platinum สามารถเข้าทุกชั้น
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "Card ID: " + cardId + ", Type: " + cardType + ", Access from " + startDay + " to " + endDay + ", Time: " + startTime + " to " + endTime;
    }
}
