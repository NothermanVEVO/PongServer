package Host.StarterPack;

import javax.swing.JFrame;

import Host.Input.Key;

public class Window extends JFrame {

    GamePanel gamePanel = new GamePanel();

    Thread gameThread;

    Key key = new Key();
    
    public Window(){
        this.setTitle("Pong");
        this.setSize(640, 480);
        this.setVisible(true);
        this.setResizable(false);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(key);
        this.add(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        gamePanel.importKey(key);

        gameThread = new Thread(gamePanel);
        gameThread.start();
    }

}
