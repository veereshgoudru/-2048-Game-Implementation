import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController {
    private Board board;
    private GameView view;

    public GameController(Board board, GameView view) {
        this.board = board;
        this.view = view;
        setupListeners();
    }

    private void setupListeners() {
        // Key listener for arrow keys
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Board.Direction direction = null;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        direction = Board.Direction.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        direction = Board.Direction.DOWN;
                        break;
                    case KeyEvent.VK_LEFT:
                        direction = Board.Direction.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        direction = Board.Direction.RIGHT;
                        break;
                }
                if (direction != null) {
                    boolean moved = board.move(direction);
                    if (moved) {
                        view.updateBoard(board.getGrid());
                        if (board.hasWon()) {
                            view.showMessage("Congratulations! You reached 2048!");
                        } else if (board.isGameOver()) {
                            view.showMessage("Game Over! No more moves possible.");
                        }
                    }
                }
            }
        });

        // Reset button listener
        view.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.reset();
                view.updateBoard(board.getGrid());
            }
        });
    }
}
