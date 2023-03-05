import javax.swing.*;
import java.awt.*;



public class Box extends AbstractObject{ // класс коробок
    private static final Image imageBox = new ImageIcon("src/sprites/boxes_sprites/boxes.png").getImage();
    private static final float HORIZONTAL_SPEED = -6.5f;
    private final int START_X;
    public Box(int x, int y) {
        super(x, y);
        START_X = x;
    }

    @Override
    public void draw(Graphics g) { //отрисовываем коробки
        fillBoxes();
        for(Box box: BoxController.getBoxes()){
            g.drawImage(imageBox,box.x,box.y,null);
            box.run();
        }
    }

    public void run(){ // задаем начальное положение коробок и запускаем их в персонажа
        if(x <= -100) {
            x = START_X;
            y = (int)(Math.random()*660);
        }
        x+=HORIZONTAL_SPEED;
    }



    public static void fillBoxes(){ // заполняем ArrayList коробками
        while(BoxController.getBoxes().size()!= 10){
            BoxController.getBoxes().add(generateBox());
        }
    }

    public static Box generateBox(){ // создаем коробку
        return new Box((int)(Math.random()*1000+1400),(int)(Math.random()*630));
    }
}
