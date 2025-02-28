public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            FitnessAccessControl gui = new FitnessAccessControl();
            gui.setVisible(true);
        });
    }
}
