import javax.swing.*;
import java.awt.*;

public class Ground extends AbstractObject{ //задний фон и по совместительству земля
    private static final String pathToImage = "src/sprites/ground_sprites/ground1.jpg";

    public Ground() {
        super(0, 0);
    }

    @Override
    public void draw(Graphics g) { //отрисовываем задний фон
        Image groundImage = new ImageIcon(pathToImage).getImage();
        g.drawImage(groundImage,x,y,null);
    }
}
