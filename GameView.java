import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class GameView extends JFrame {
    private JLabel[][] tileLabels;
    private JButton resetButton;
    private Map<Integer, Color> tileColors;

    public GameView() {
        initializeColors();
        setTitle("2048 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 500));
        setFocusable(true);

        // Create tile grid
        JPanel boardPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        boardPanel.setBackground(new Color(187, 173, 160));

        tileLabels = new JLabel[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tileLabels[i][j] = new JLabel("", SwingConstants.CENTER);
                tileLabels[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                tileLabels[i][j].setOpaque(true);
                tileLabels[i][j].setBackground(tileColors.get(0));
                tileLabels[i][j].setPreferredSize(new Dimension(80, 80));
                boardPanel.add(tileLabels[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        // Reset button
        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(187, 173, 160));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void initializeColors() {
        tileColors = new HashMap<>();
        tileColors.put(0, new Color(205, 193, 180)); // Empty
        tileColors.put(2, new Color(238, 228, 218));
        tileColors.put(4, new Color(237, 224, 200));
        tileColors.put(8, new Color(242, 177, 121));
        tileColors.put(16, new Color(245, 149, 99));
        tileColors.put(32, new Color(246, 124, 95));
        tileColors.put(64, new Color(246, 94, 59));
        tileColors.put(128, new Color(237, 207, 114));
        tileColors.put(256, new Color(237, 204, 97));
        tileColors.put(512, new Color(237, 200, 80));
        tileColors.put(1024, new Color(237, 197, 63));
        tileColors.put(2048, new Color(237, 194, 46));
        // For higher values, use a default orange
        for (int i = 4096; i <= 131072; i *= 2) {
            tileColors.put(i, new Color(255, 140, 0)); // Dark orange
        }
    }

    public void updateBoard(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = grid[i][j];
                tileLabels[i][j].setText(value == 0 ? "" : String.valueOf(value));
                tileLabels[i][j].setBackground(tileColors.getOrDefault(value, tileColors.get(2048)));
                tileLabels[i][j].setForeground(getTextColor(value));
            }
        }
        repaint();
    }

    private Color getTextColor(int value) {
        if (value <= 4) {
            return new Color(119, 110, 101);
        } else {
            return Color.WHITE;
        }
    }

    public void addKeyListener(KeyListener listener) {
        super.addKeyListener(listener);
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
