import java.awt.*;

public abstract class AbstractObject { // сделан для выполнения условия
    int x,y;
    public AbstractObject(int x, int y){
        this.x = x;
        this.y = y;
    }
    public abstract void draw(Graphics g);


}
