import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Chessboard extends JPanel {

    private final int tileSize = 80;

    ArrayList<Piece> pieces = new ArrayList<>();

    public Chessboard() {

        pieces.add(new Piece("Rook", 7, 0));
        pieces.add(new Piece("Knight", 7, 1));
        pieces.add(new Piece("Bishop", 7, 2));
        pieces.add(new Piece("Queen", 7, 3));
        pieces.add(new Piece("King", 7, 4));
        pieces.add(new Piece("Bishop", 7, 5));
        pieces.add(new Piece("Knight", 7, 6));
        pieces.add(new Piece("Rook", 7, 7));

        for (int col = 0; col < 8; col++) {
            pieces.add(new Piece("Pawn", 6, col));
        }
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

        g.setColor(Color.BLACK);

        for (Piece piece : pieces) {

            int x = piece.col * tileSize + 10;
            int y = piece.row * tileSize + 40;

            g.drawString(piece.name, x, y);
        }
    }
}