import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FitnessAccessControl extends JFrame {
    private JTextField cardIdField, startDayField, endDayField, startTimeField, endTimeField;
    private JTextArea logArea;
    private JComboBox<String> cardTypeComboBox;
    private CardManager cardManager = new CardManager();
    private static final String LOG_FILE = "audit_log.txt";

    public FitnessAccessControl() {
        setTitle("Fitness Access Control System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        cardIdField = new JTextField(10);
        startDayField = new JTextField(5);
        endDayField = new JTextField(5);
        startTimeField = new JTextField(5);
        endTimeField = new JTextField(5);

        cardTypeComboBox = new JComboBox<>(new String[]{"Silver", "Gold", "Platinum"});

        JButton addButton = new JButton("Add");
        JButton modifyButton = new JButton("Modify");
        JButton revokeButton = new JButton("Revoke");
        JButton checkAccessButton = new JButton("Check Access");
        JButton showCardsButton = new JButton("Show Cards");
        JButton checkInButton = new JButton("Check In");
        JButton checkOutButton = new JButton("Check Out");

        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);

        add(new JLabel("Card ID:")); add(cardIdField);
        add(new JLabel("Start Day:")); add(startDayField);
        add(new JLabel("End Day:")); add(endDayField);
        add(new JLabel("Start Time:")); add(startTimeField);
        add(new JLabel("End Time:")); add(endTimeField);
        add(new JLabel("Card Type:")); add(cardTypeComboBox);
        add(addButton); add(modifyButton); add(revokeButton); add(checkAccessButton); add(showCardsButton);
        add(checkInButton); add(checkOutButton);
        add(new JScrollPane(logArea));

        addButton.addActionListener(e -> addCard());
        modifyButton.addActionListener(e -> modifyCard());
        revokeButton.addActionListener(e -> revokeCard());
        checkAccessButton.addActionListener(e -> checkAccess());
        showCardsButton.addActionListener(e -> showCards());
        checkInButton.addActionListener(e -> checkIn());
        checkOutButton.addActionListener(e -> checkOut());
    }

    private void addCard() {
        String cardId = cardIdField.getText();
        int startDay = Integer.parseInt(startDayField.getText());
        int endDay = Integer.parseInt(endDayField.getText());
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String cardType = (String) cardTypeComboBox.getSelectedItem();

        AccessCard newCard = AccessCardFactory.createCard(cardId, startDay, endDay, startTime, endTime, cardType);

        cardManager.addCard(newCard);
        logAction("Card added: " + cardId);
    }


    private void modifyCard() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            int startDay = Integer.parseInt(startDayField.getText());
            int endDay = Integer.parseInt(endDayField.getText());
            String startTime = startTimeField.getText();
            String endTime = endTimeField.getText();
            String cardType = (String) cardTypeComboBox.getSelectedItem();

            card.setAccessPeriod(startDay, endDay, startTime, endTime);
            card.setCardType(cardType);
            logAction("Card modified: " + cardId);
        } else {
            JOptionPane.showMessageDialog(this, "Card not found.");
        }
    }

    private void revokeCard() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            cardManager.removeCard(cardId);
            logAction("Card revoked: " + cardId);
        } else {
            JOptionPane.showMessageDialog(this, "Card not found.");
        }
    }
    private void showCards() {
        StringBuilder allCards = new StringBuilder();
        for (AccessCard card : cardManager.getAllCards()) {
            allCards.append(card.toString()).append("\n");
        }
        logArea.setText(allCards.toString());
        logAction("Show all cards");
    }


    private void checkIn() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            logAction("✅ Check-in: Card " + cardId + " at " + timestamp);
            JOptionPane.showMessageDialog(this, "✅ Check-in successful for Card " + cardId);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Card not found.");
        }
    }

    private void checkOut() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            logAction("🚪 Check-out: Card " + cardId + " at " + timestamp);
            JOptionPane.showMessageDialog(this, "🚪 Check-out successful for Card " + cardId);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Card not found.");
        }
    }

    private void logAction(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = timestamp + " - " + message;
        logArea.append(logMessage + "\n");
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void checkAccess() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            try {
                int currentDay = Integer.parseInt(JOptionPane.showInputDialog("Enter current day (1-31):"));
                String currentTime = JOptionPane.showInputDialog("Enter current time (HH:mm):");
                String levelToAccess = JOptionPane.showInputDialog("Enter the level to access (Silver, Gold, Platinum):");

                if (card.isAccessAllowed(currentDay, currentTime)) {
                    if (card.canAccessLevel(levelToAccess)) {
                        JOptionPane.showMessageDialog(this, "✅ Access granted to level " + levelToAccess + " for card " + cardId);
                        logAction("Access granted: " + cardId + " to level " + levelToAccess);
                    } else {
                        JOptionPane.showMessageDialog(this, "⛔ Access denied to level " + levelToAccess + " for card " + cardId);
                        logAction("Access denied: " + cardId + " to level " + levelToAccess);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "⛔ Access denied due to invalid time or day.");
                    logAction("Access denied: " + cardId + " due to invalid time/day");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "❌ Invalid input.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "❌ Card not found.");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FitnessAccessControl().setVisible(true));
    }
}