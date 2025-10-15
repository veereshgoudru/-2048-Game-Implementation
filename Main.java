import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Board board = new Board();
            GameView view = new GameView();
            GameController controller = new GameController(board, view);
            view.updateBoard(board.getGrid());
            view.setVisible(true);
        });
    }
}
