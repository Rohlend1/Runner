import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class Panel extends JPanel {
    private static final JFrame frame = new JFrame();
    private static Component panel = getAnotherPanel();
    private static final MainCharacter hero = MainCharacter.getInstance();
    private static final Ground ground = new Ground();
    private static final Box box = new Box(0,0);
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1400;
    private static final Font font = new Font("Times New Roman", Font.BOLD,24);
    private static JButton jButton;

    public static Component getPanel() {
        return panel;
    }

    public static MainCharacter getHero(){
        return hero;
    }

    private Panel(){ // генерируем панель
        setLayout(null);
        revalidate();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jButton = new JButton("Заново");
        jButton.setBounds(WIDTH/2-75,HEIGHT/2,150,50);
        jButton.addActionListener(e-> reset());
        jButton.setVisible(false);
        add(jButton);


    }

    @Override
    protected void paintComponent(Graphics g){//отрисовываем героя, пол и остальные объекты(при отрисовке героя выставляем задержку)
        super.paintComponent(g);
        AttributedString score = new AttributedString("Ваш счёт: "+ hero.getScore());
        score.addAttribute(TextAttribute.FONT,font);
        if(hero.isAlive()) {
            ground.draw(g);
            g.drawString(score.getIterator(),10,25);
            hero.draw(g);
            box.draw(g);
            repaint();
        }
        else{
            jButton.setVisible(true);
            if(hero.getScore()>=1000){
                win(g, score);
            }
            else lose(g, score);
        }
    }
    public static void lose(Graphics g, AttributedString score){//вывод сообщения поражения
        AttributedString loseString = new AttributedString("Вы проиграли");
        loseString.addAttribute(TextAttribute.FONT, font);
        g.drawString(loseString.getIterator(),WIDTH/2-75,HEIGHT/2-100);
        g.drawString(score.getIterator(),WIDTH/2-75,HEIGHT/2-50);
    }

    public static void win(Graphics g, AttributedString score){ //вывод сообщения победы
        AttributedString winString = new AttributedString("Вы выиграли");
        winString.addAttribute(TextAttribute.FONT, font);
        g.drawString(winString.getIterator(),WIDTH/2-75,HEIGHT/2-100);
        g.drawString(score.getIterator(),WIDTH/2-75,HEIGHT/2-50);
    }

    private void reset(){ // рестарт программы
        frame.getContentPane().removeAll();
        panel = getAnotherPanel();
        frame.getContentPane().add(panel);
        frame.revalidate();
        hero.reset();
        jButton.setVisible(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }


    public static Panel getAnotherPanel(){ // получить новую панель
        return new Panel();
    }
    public static JFrame getFrame(){
        return frame;
    }

}
