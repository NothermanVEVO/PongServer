package Client.StarterPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Client.Ball.Ball;
import Client.Input.Key;
import Client.Player.Player;

public class GamePanel extends JPanel implements Runnable{

    int fps = 60;
    long sleepTime = 1000/fps;

    Player player = new Player();
    Ball ball = new Ball();

    Key key = new Key();

    GamePanel(){
        this.setBounds(0, 0, 640, 480);
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.addKeyListener(key);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
    }

    public void importKey(Key key){
        this.key = key;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        int frames = 0;
        
        while (true) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta >= 1){

                update();
                repaint();
                delta--;
                drawCount++;

            }

            frames++;

            if(timer >= 1000000000){
                System.out.println("TICK: " + drawCount + " FPS: " + frames);
                drawCount = 0;
                timer = 0;
                frames = 0;
            }
        }
    }

    public void update(){
        player.update(key);
        ball.update(Player.player);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        ball.draw(g2);
        g2.dispose();
    }

}
