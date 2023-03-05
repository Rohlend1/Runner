
import javax.swing.*;
import java.awt.*;

public class MainCharacter extends AbstractObject{ // реализация игрового персонажа с помощью паттерна синглтон (невозможно создать более 1 персонажа в игре)
    private static final float HORIZONTAL_SPEED = 5.5f;
    private static final float VERTICAL_SPEED = 10.0f;
    private static final float jumpLimit = 150f;
    private static int jumpPosY;
    private static int START_Y;
    private static boolean onJump = false;
    private static int frameCounter = 1; //счётчик кадров для отрисовки(анимации) с 1 по 24 кадр включительно
    private static MainCharacter instance;
    private static boolean onBox = false;
    private static final int HEIGHT = 150;
    private static final int WIDTH = 90;
    private static boolean Alive = false;
    private int score = 0;

    private MainCharacter(int x, int y) {
        super(x, y);
        START_Y = y;
        Alive = true;
    }
    public static MainCharacter getInstance(){
        if(instance == null) instance = new MainCharacter(0,635);
        return instance;
    }

    public int getScore() {
        return score;
    }

    public void run() {// перемещаем персонажа по x, при активированном прыжке по y и x
        if(Alive){// моментальный выход из метода при смерти персонажа
            isDead();
            isOnBox();
            if (x < 1380) {
                x += HORIZONTAL_SPEED;
                if (onJump && y > 0) {
                    if (y > jumpPosY - jumpLimit && y - VERTICAL_SPEED > 0) {
                        y -= VERTICAL_SPEED;
                        if (y == jumpPosY - jumpLimit) onJump = false;
                    } else if (y - VERTICAL_SPEED < 0) {
                        onJump = false;
                        y += VERTICAL_SPEED;
                    }
                } else if ((y != jumpPosY && y != START_Y && !onBox) && !onJump) y += VERTICAL_SPEED;
                else if (!onBox && y != START_Y) y += VERTICAL_SPEED;
            } else {
                x = -50;
                score += 250;
            }

        }
    }

    public void isDead(){   //проверка на столкновение с коробкой
        for(Box box : BoxController.getBoxes()){
            if((( this.x <= box.x + BoxController.getWidth() - 45 && box.x + 30 <= this.x)||
                    (box.x + 30 <= this.x + WIDTH - 40 && box.x + BoxController.getWidth() - 45 >= this.x+WIDTH - 40)) &&
                    ((box.y <= this.y && box.y + BoxController.getHeight() >=this.y)||
                    (box.y + BoxController.getHeight() >= this.y + HEIGHT - 20 &&
                            box.y <= this.y + HEIGHT - 20))) {
                Alive = false;
                return;
            }
        }
    }
    public void reset(){
        BoxController.getBoxes().clear();
        Alive = true;
        score = 0;
        y = START_Y;
        x = 0;
    }
    public boolean isAlive() {
        return Alive;
    }

    public void isOnBox(){ // проверка на коллизию, находится ли персонаж сверху коробки, подбери свои значения width-30, height -15 и т.д.(чтоб выглядело более менее)
        for(Box box : BoxController.getBoxes()){
            if(((box.x <= this.x+WIDTH-30 && box.x+BoxController.getWidth() >= this.x+WIDTH-30)||
                    (box.x <= this.x-15 && box.x+BoxController.getWidth() >= this.x-15)) &&
                    (box.y <= this.y+HEIGHT-15 && this.y+HEIGHT <= box.y+25)) {
                onBox = true;
                return;
            }
        }
        onBox = false;
    }

    public void jump(){//реализуем логику прыжка
//        if((y == START_Y || onBox) && Alive) {
            onJump = true;
            jumpPosY = y;
            y += VERTICAL_SPEED;
//        }
    }


    public void teleport(){ // телепорт, сделан чисто по приколу, чтобы как-то реализовать приседания персонажа (у меня просто не было анимации)
        x += 350;
    }

    @Override
    public void draw(Graphics g) { //отрисовываем персонажа
        run();
        if(frameCounter == 25) frameCounter =1;                                                                            // поочередно перебираем кадры спрайтов
        Image hero1 = new ImageIcon(String.format("src/sprites/hero_sprites/hero%d.png", frameCounter)).getImage();
        g.drawImage(hero1, x, y, null);
        frameCounter++;
        try{
            Thread.sleep(20);
        }
        catch (InterruptedException e){
            System.out.println("Ошибка отрисовки объекта!");
        }

    }
}
