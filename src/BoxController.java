import java.util.ArrayList;

public final class BoxController { // класс который управляет коробками
    private static final ArrayList<Box> boxes = new ArrayList<>();
    private static final int WIDTH = 137;
    private static final int HEIGHT = 75;
    private BoxController(){
    }

    public static ArrayList<Box> getBoxes(){
        return boxes;
    }
    public static int getWidth(){
        return WIDTH;
    }
    public static int getHeight(){
        return HEIGHT;
    }
}
