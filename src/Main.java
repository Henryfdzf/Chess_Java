import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Chess");

        // To show the frame
        frame.setVisible(true);
        // To set a size
        frame.setSize(800, 800);
        //set the frame in the middle of the screen
        frame.setLocationRelativeTo(null);

        Chessboard board = new Chessboard();
        frame.add(board);

    }
}
