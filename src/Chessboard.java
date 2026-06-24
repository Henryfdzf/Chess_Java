import javax.swing.*;
import java.awt.*;

public class Chessboard extends JPanel {

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.fillRect(100,100,100,100);
    }


}
