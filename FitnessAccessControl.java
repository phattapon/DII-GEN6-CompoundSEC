import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.LocalDate;

public class FitnessAccessControl extends JFrame {
    private JTextField cardIdField, startDayField, endDayField, startTimeField, endTimeField;
    private JTextArea logArea;
    private JComboBox<String> cardTypeComboBox;  // เพิ่ม ComboBox สำหรับเลือกประเภทการ์ด
    private CardManager cardManager = new CardManager();

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

        // เพิ่ม ComboBox สำหรับเลือกประเภทการ์ด
        cardTypeComboBox = new JComboBox<>(new String[]{"Silver", "Gold", "Platinum"});

        JButton addButton = new JButton("Add");
        JButton modifyButton = new JButton("Modify");
        JButton revokeButton = new JButton("Revoke");
        JButton checkAccessButton = new JButton("Check Access");
        JButton showCardsButton = new JButton("Show Cards");

        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);

        add(new JLabel("Card ID:")); add(cardIdField);
        add(new JLabel("Start Day:")); add(startDayField);
        add(new JLabel("End Day:")); add(endDayField);
        add(new JLabel("Start Time:")); add(startTimeField);
        add(new JLabel("End Time:")); add(endTimeField);
        add(new JLabel("Card Type:")); add(cardTypeComboBox);  // เพิ่มการเลือกประเภทการ์ด

        add(addButton); add(modifyButton); add(revokeButton); add(checkAccessButton); add(showCardsButton);
        add(new JScrollPane(logArea));

        addButton.addActionListener(e -> addCard());
        modifyButton.addActionListener(e -> modifyCard());
        revokeButton.addActionListener(e -> revokeCard());
        checkAccessButton.addActionListener(e -> checkAccess());
        showCardsButton.addActionListener(e -> showCards());
    }

    private void addCard() {
        String cardId = cardIdField.getText();
        int startDay = Integer.parseInt(startDayField.getText());
        int endDay = Integer.parseInt(endDayField.getText());
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String cardType = (String) cardTypeComboBox.getSelectedItem();  // เลือกประเภทการ์ดจาก ComboBox

        cardManager.addCard(new AccessCard(cardId, startDay, endDay, startTime, endTime, cardType));
        logArea.setText("Card added successfully!");
    }

    private void modifyCard() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            int startDay = Integer.parseInt(startDayField.getText());
            int endDay = Integer.parseInt(endDayField.getText());
            String startTime = startTimeField.getText();
            String endTime = endTimeField.getText();
            String cardType = (String) cardTypeComboBox.getSelectedItem();  // เลือกประเภทการ์ดจาก ComboBox

            card.setAccessPeriod(startDay, endDay, startTime, endTime);
            card.setCardType(cardType);  // แก้ไขประเภทการ์ด
            logArea.setText("Card modified successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Card not found.");
        }
    }

    private void revokeCard() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            cardManager.removeCard(cardId);
            logArea.setText("Card revoked successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Card not found.");
        }
    }

    private void checkAccess() {
        String cardId = cardIdField.getText();
        AccessCard card = cardManager.getCard(cardId);

        if (card != null) {
            int currentDay = LocalDate.now().getDayOfMonth();
            String currentTime = LocalTime.now().toString().substring(0, 5);

            // ให้ผู้ใช้กรอกชั้นที่ต้องการเข้าถึง
            String levelToAccess = JOptionPane.showInputDialog(this, "Enter the level to access (Silver, Gold, Platinum):");

            if (card.isAccessAllowed(currentDay, currentTime)) {
                if (card.canAccessLevel(levelToAccess)) {
                    JOptionPane.showMessageDialog(this, "Access granted to level " + levelToAccess + " for card " + cardId);
                } else {
                    JOptionPane.showMessageDialog(this, "Access denied to level " + levelToAccess + " for card " + cardId);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Access denied due to invalid time or day.");
            }
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FitnessAccessControl().setVisible(true));
    }
}
