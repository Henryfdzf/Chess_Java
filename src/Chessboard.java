import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Chessboard extends JPanel {

    private final int tileSize = 80;

    private Piece selectedPiece = null;
    private String currentTurn = "White";

    ArrayList<Piece> pieces = new ArrayList<>();

    public Chessboard() {

        // White pieces
        pieces.add(new Piece("Rook", "White", 7, 0));
        pieces.add(new Piece("Knight", "White", 7, 1));
        pieces.add(new Piece("Bishop", "White", 7, 2));
        pieces.add(new Piece("Queen", "White", 7, 3));
        pieces.add(new Piece("King", "White", 7, 4));
        pieces.add(new Piece("Bishop", "White", 7, 5));
        pieces.add(new Piece("Knight", "White", 7, 6));
        pieces.add(new Piece("Rook", "White", 7, 7));

        for (int col = 0; col < 8; col++) {
            pieces.add(new Piece("Pawn", "White", 6, col));
        }

        // Black pieces
        pieces.add(new Piece("Rook", "Black", 0, 0));
        pieces.add(new Piece("Knight", "Black", 0, 1));
        pieces.add(new Piece("Bishop", "Black", 0, 2));
        pieces.add(new Piece("Queen", "Black", 0, 3));
        pieces.add(new Piece("King", "Black", 0, 4));
        pieces.add(new Piece("Bishop", "Black", 0, 5));
        pieces.add(new Piece("Knight", "Black", 0, 6));
        pieces.add(new Piece("Rook", "Black", 0, 7));

        for (int col = 0; col < 8; col++) {
            pieces.add(new Piece("Pawn", "Black", 1, col));
        }

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                int col = e.getX() / tileSize;
                int row = e.getY() / tileSize;

                if (selectedPiece == null) {

                    for (Piece piece : pieces) {

                        if (piece.row == row &&
                                piece.col == col &&
                                piece.color.equals(currentTurn)) {

                            selectedPiece = piece;
                            repaint();
                            return;
                        }
                    }

                } else {

                    Piece targetPiece = null;

                    for (Piece piece : pieces) {

                        if (piece.row == row &&
                                piece.col == col &&
                                piece != selectedPiece) {

                            targetPiece = piece;
                            break;
                        }
                    }

                    boolean validMove = false;

                    if (selectedPiece.name.equals("Pawn")) {

                        if (selectedPiece.color.equals("White")) {

                            if (col == selectedPiece.col &&
                                    row == selectedPiece.row - 1 &&
                                    targetPiece == null) {

                                validMove = true;
                            }

                            if (Math.abs(col - selectedPiece.col) == 1 &&
                                    row == selectedPiece.row - 1 &&
                                    targetPiece != null &&
                                    targetPiece.color.equals("Black")) {

                                validMove = true;
                            }

                        } else {

                            if (col == selectedPiece.col &&
                                    row == selectedPiece.row + 1 &&
                                    targetPiece == null) {

                                validMove = true;
                            }

                            if (Math.abs(col - selectedPiece.col) == 1 &&
                                    row == selectedPiece.row + 1 &&
                                    targetPiece != null &&
                                    targetPiece.color.equals("White")) {

                                validMove = true;
                            }
                        }

                    } else {

                        validMove = selectedPiece.canMove(row, col);
                    }

                    if (validMove) {

                        if (targetPiece != null &&
                                !targetPiece.color.equals(selectedPiece.color)) {

                            pieces.remove(targetPiece);
                        }

                        selectedPiece.row = row;
                        selectedPiece.col = col;

                        if (currentTurn.equals("White")) {
                            currentTurn = "Black";
                        } else {
                            currentTurn = "White";
                        }
                    }

                    selectedPiece = null;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {

                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }

                g.fillRect(
                        col * tileSize,
                        row * tileSize,
                        tileSize,
                        tileSize
                );
            }
        }

        for (Piece piece : pieces) {

            if (piece.color.equals("White")) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }

            int x = piece.col * tileSize + 5;
            int y = piece.row * tileSize + 40;

            String text =
                    piece.color.charAt(0)
                            + piece.name.substring(0, 1);

            g.drawString(text, x, y);
        }

        if (selectedPiece != null) {

            g.setColor(Color.GREEN);

            g.drawRect(
                    selectedPiece.col * tileSize,
                    selectedPiece.row * tileSize,
                    tileSize,
                    tileSize
            );
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 640, 640, 80);

        g.setColor(Color.BLACK);
        g.drawString("Current Turn: " + currentTurn, 20, 685);
    }
}