public class AccessCardFactory {
    public static AccessCard createCard(String cardId, int startDay, int endDay, String startTime, String endTime, String cardType) {
        return new AccessCard(cardId, startDay, endDay, startTime, endTime, cardType);
    }
}
