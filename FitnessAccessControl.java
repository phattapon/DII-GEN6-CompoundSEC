import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FitnessAccessControl extends JFrame {
    private JTextField cardIdField;
    private JTextField accessLevelField;
    private JTextField validDaysField;
    private JTextArea logArea;

    private List<String> accessCards = new ArrayList<>();
    private List<String> auditLogs = new ArrayList<>();

    public FitnessAccessControl() {
        setTitle("Fitness Access Control System");
        setSize(600, 500); // ขยายขนาดหน้าต่าง
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        cardIdField = new JTextField(10);
        accessLevelField = new JTextField(10);
        validDaysField = new JTextField(5);

        JButton addButton = new JButton("Add");
        JButton modifyButton = new JButton("Modify");
        JButton revokeButton = new JButton("Revoke");
        JButton checkAccessButton = new JButton("Check Access");
        JButton showCardsButton = new JButton("Show Cards");
        JButton auditLogsButton = new JButton("Audit Logs");

        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);

        // เพิ่มคำแนะนำ (Label) และช่องกรอกข้อมูล
        add(new JLabel("Card ID:"));
        add(cardIdField);
        add(new JLabel("Access Level:"));
        add(accessLevelField);
        add(new JLabel("Valid for (days):"));
        add(validDaysField);

        // เพิ่มปุ่มต่าง ๆ ที่ใช้งานในระบบ
        add(addButton);
        add(modifyButton);
        add(revokeButton);
        add(checkAccessButton);
        add(showCardsButton);
        add(auditLogsButton);

        // เพิ่มพื้นที่สำหรับแสดงผล
        add(new JScrollPane(logArea));

        // Action listener สำหรับปุ่มต่าง ๆ
        addButton.addActionListener(e -> addCard());
        modifyButton.addActionListener(e -> modifyCard());
        revokeButton.addActionListener(e -> revokeCard());
        checkAccessButton.addActionListener(e -> checkAccess());
        showCardsButton.addActionListener(e -> showCards());
        auditLogsButton.addActionListener(e -> showAuditLogs());
    }

    // ฟังก์ชันการเพิ่มบัตร
    private void addCard() {
        String cardId = cardIdField.getText();
        String accessLevel = accessLevelField.getText();
        String validDays = validDaysField.getText();

        if (!cardId.isEmpty() && !accessLevel.isEmpty() && !validDays.isEmpty()) {
            accessCards.add("Card ID: " + cardId + " | Level: " + accessLevel + " | Days: " + validDays);
            auditLogs.add(getTimestamp() + " - Card " + cardId + " was created");
            logArea.setText("Card added successfully!");
        } else {
            logArea.setText("Please fill in all fields.");
        }
    }

    // ฟังก์ชันการแก้ไขบัตร
    private void modifyCard() {
        String cardId = cardIdField.getText();
        if (cardId.isEmpty()) {
            logArea.setText("Enter Card ID to modify.");
            return;
        }

        for (int i = 0; i < accessCards.size(); i++) {
            if (accessCards.get(i).contains("Card ID: " + cardId)) {
                String newAccessLevel = accessLevelField.getText();
                String newValidDays = validDaysField.getText();

                // อัปเดตข้อมูลของบัตร
                accessCards.set(i, "Card ID: " + cardId + " | Level: " + newAccessLevel + " | Days: " + newValidDays);
                auditLogs.add(getTimestamp() + " - Card " + cardId + " was modified");
                logArea.setText("Card modified successfully!");
                return;
            }
        }
        logArea.setText("Card ID not found.");
    }

    // ฟังก์ชันการเพิกถอนบัตร
    private void revokeCard() {
        String cardId = cardIdField.getText();
        accessCards.removeIf(card -> card.contains("Card ID: " + cardId));
        auditLogs.add(getTimestamp() + " - Card " + cardId + " was revoked");
        logArea.setText("Card revoked successfully!");
    }

    // ฟังก์ชันการตรวจสอบการเข้าถึง
    private void checkAccess() {
        String cardId = cardIdField.getText();
        for (String card : accessCards) {
            if (card.contains("Card ID: " + cardId)) {
                JOptionPane.showMessageDialog(this, "Access granted:\n" + card);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Access denied. Card not found.");
    }

    // ฟังก์ชันแสดงบัตรทั้งหมด
    private void showCards() {
        StringBuilder result = new StringBuilder("All Access Cards:\n");
        for (String card : accessCards) {
            result.append(card).append("\n");
        }
        logArea.setText(result.toString());
    }

    // ฟังก์ชันแสดง Log
    private void showAuditLogs() {
        StringBuilder result = new StringBuilder("Audit Logs:\n");
        for (String log : auditLogs) {
            result.append(log).append("\n");
        }
        logArea.setText(result.toString());
    }

    // ฟังก์ชันเพื่อให้ได้เวลาในรูปแบบ Timestamp
    private String getTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            FitnessAccessControl gui = new FitnessAccessControl();
            gui.setVisible(true);
        });
    }
}
