public class Piece {

    String name;
    int row;
    int col;

    public Piece(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
    }

    public boolean canMove(int targetRow, int targetCol) {

        int rowDiff = Math.abs(targetRow - row);
        int colDiff = Math.abs(targetCol - col);

        switch (name) {

            case "Rook":
                return row == targetRow || col == targetCol;

            case "Bishop":
                return rowDiff == colDiff;

            case "Queen":
                return row == targetRow ||
                        col == targetCol ||
                        rowDiff == colDiff;

            case "King":
                return rowDiff <= 1 && colDiff <= 1;

            case "Knight":
                return (rowDiff == 2 && colDiff == 1) ||
                        (rowDiff == 1 && colDiff == 2);

            case "Pawn":
                return targetCol == col &&
                        targetRow == row - 1;

            default:
                return false;
        }
    }
}