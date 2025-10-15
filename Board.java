import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private static final int SIZE = 4;
    private int[][] grid;
    private Random random;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Board() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        initialize();
        addRandomTile();
        addRandomTile();
    }

    private void initialize() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void addRandomTile() {
        List<Integer> emptyPositions = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    emptyPositions.add(i * SIZE + j);
                }
            }
        }
        if (!emptyPositions.isEmpty()) {
            int position = random.nextInt(emptyPositions.size());
            int row = position / SIZE;
            int col = position % SIZE;
            grid[row][col] = random.nextInt(10) < 9 ? 2 : 4;
        }
    }

    public boolean move(Direction direction) {
        int[][] originalGrid = copyGrid();
        boolean moved = false;

        switch (direction) {
            case LEFT:
                moved = slideLeft();
                break;
            case RIGHT:
                moved = slideRight();
                break;
            case UP:
                transpose();
                moved = slideLeft();
                transpose();
                break;
            case DOWN:
                transpose();
                reverseColumns();
                moved = slideLeft();
                reverseColumns();
                transpose();
                break;
        }

        if (moved) {
            addRandomTile();
        }

        return !gridsEqual(originalGrid, grid);
    }

    private boolean slideLeft() {
        boolean changed = false;
        for (int i = 0; i < SIZE; i++) {
            int[] row = extractRow(i);
            slideAndMerge(row);
            placeRow(i, row);
            if (!arraysEqual(extractRow(i), row)) {
                changed = true;
            }
        }
        return changed;
    }

    private boolean slideRight() {
        reverseRows();
        boolean changed = slideLeft();
        reverseRows();
        return changed;
    }

    private void transpose() {
        int[][] newGrid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newGrid[i][j] = grid[j][i];
            }
        }
        grid = newGrid;
    }

    private void reverseColumns() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE / 2; j++) {
                int temp = grid[i][j];
                grid[i][j] = grid[i][SIZE - 1 - j];
                grid[i][SIZE - 1 - j] = temp;
            }
        }
    }

    private void reverseRows() {
        for (int i = 0; i < SIZE / 2; i++) {
            int[] temp = extractRow(i);
            placeRow(i, extractRow(SIZE - 1 - i));
            placeRow(SIZE - 1 - i, temp);
        }
    }

    private int[] extractRow(int row) {
        int[] rowArray = new int[SIZE];
        for (int j = 0; j < SIZE; j++) {
            rowArray[j] = grid[row][j];
        }
        return rowArray;
    }

    private void placeRow(int row, int[] rowArray) {
        for (int j = 0; j < SIZE; j++) {
            grid[row][j] = rowArray[j];
        }
    }

    private void slideAndMerge(int[] line) {
        // Remove zeros
        int nonZeroIndex = 0;
        for (int i = 0; i < SIZE; i++) {
            if (line[i] != 0) {
                line[nonZeroIndex++] = line[i];
            }
        }
        // Add zeros at end
        while (nonZeroIndex < SIZE) {
            line[nonZeroIndex++] = 0;
        }
        // Merge adjacent equals
        for (int i = 0; i < SIZE - 1; i++) {
            if (line[i] != 0 && line[i] == line[i + 1]) {
                line[i] *= 2;
                line[i + 1] = 0;
                i++; // Skip next
            }
        }
        // Slide again to remove zeros after merge
        nonZeroIndex = 0;
        for (int i = 0; i < SIZE; i++) {
            if (line[i] != 0) {
                line[nonZeroIndex++] = line[i];
            }
        }
        while (nonZeroIndex < SIZE) {
            line[nonZeroIndex++] = 0;
        }
    }

    private boolean arraysEqual(int[] a, int[] b) {
        for (int i = 0; i < SIZE; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private int[][] copyGrid() {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }

    private boolean gridsEqual(int[][] a, int[][] b) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean isGameOver() {
        // Check if full and no possible moves
        if (!isFull()) return false;
        // Check horizontal merges
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (grid[i][j] == grid[i][j + 1]) return false;
            }
        }
        // Check vertical merges
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE - 1; i++) {
                if (grid[i][j] == grid[i + 1][j]) return false;
            }
        }
        return true;
    }

    private boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean hasWon() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] >= 2048) return true;
            }
        }
        return false;
    }

    public void reset() {
        initialize();
        addRandomTile();
        addRandomTile();
    }
}
