package Host.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Host.Input.Key;
import Host.Server.Server;

public class Player {

    public static Rectangle player;
    public static Rectangle otherPlayer;

    public static int playerPont = 0;
    public static int otherPlayerPont = 0;

    public static boolean upOtherPlayerPressed;
    public static boolean downOtherPlayerPressed;

    public static String pinga = "MARCO";

    int frames;

    Key key;
    
    public Player(){
        //? CHANGED HERE
        player = new Rectangle(10, (480 / 2) - 75, 20, 75);
        otherPlayer = new Rectangle(-100, -100, 20, 75);
    }

    public void update(Key key, int frames){
        this.frames = frames;
        if(key.upPressed){
            player.y -= 4;
        }
        if(key.downPressed){
            player.y += 4;
        }

        if(upOtherPlayerPressed){
            otherPlayer.y -= 4;
        }
        if(downOtherPlayerPressed){
            otherPlayer.y += 4;
        }

        if(player.y <= 0){
            player.y += 4;
        }
        if(player.y + (player.height + 37) >= 480){
            player.y -= 4;
        }
        // System.out.println(otherPlayer.y);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.drawLine(315, 0, 315, 480);
        g2.setFont(new Font("", Font.PLAIN, 40));
        g2.drawString("" + playerPont, 320 - 30 - 200, 240 - 200);
        g2.drawString("" + otherPlayerPont, 320 - 30 + 200, 240 - 200);
        g2.setFont(new Font("", Font.PLAIN, 10));
        g2.drawString("FPS: " + frames + " LATENCY: " + Server.latency, 150, 20);
        g2.fill(otherPlayer);
        g2.fill(player);
    }

}
