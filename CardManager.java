import java.util.ArrayList;
import java.util.List;

public class CardManager {
    private List<AccessCard> cards;

    public CardManager() {
        cards = new ArrayList<>();
    }

    // เมธอดที่เพิ่มการ์ดใหม่
    public void addCard(AccessCard card) {
        cards.add(card);
    }

    // เมธอดที่ดึงการ์ดจาก ID
    public AccessCard getCard(String cardId) {
        for (AccessCard card : cards) {
            if (card.getCardId().equals(cardId)) {
                return card;
            }
        }
        return null;
    }

    // เพิ่มเมธอด removeCard() สำหรับลบการ์ดจาก CardManager
    public void removeCard(String cardId) {
        AccessCard cardToRemove = getCard(cardId);  // ค้นหาการ์ดที่ตรงกับ cardId
        if (cardToRemove != null) {
            cards.remove(cardToRemove);  // ลบการ์ดออกจากรายการ
        }
    }

    // เมธอดที่ดึงการ์ดทั้งหมด
    public List<AccessCard> getAllCards() {
        return cards;
    }
}
