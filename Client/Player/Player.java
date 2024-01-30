package Client.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Client.Input.Key;

public class Player {

    public static Rectangle player;
    public static Rectangle otherPlayer;

    public static int playerPont = 0;
    public static int otherPlayerPont = 0;

    Key key;
    
    public Player(){
        //? CHANGED HERE
        player = new Rectangle(640 - 45, (480 / 2) - 75, 20, 75);
        otherPlayer = new Rectangle(-100, -100, 20, 75);
    }

    public void update(Key key){
        if(key.is_W_Pressed || key.is_Up_Pressed){
            player.y -= 4;
        }
        if(key.is_S_Pressed || key.is_Down_Pressed){
            player.y += 4;
        }

        if(player.y <= 0){
            player.y += 4;
        }
        if(player.y + (player.height + 37) >= 480){
            player.y -= 4;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.drawLine(315, 0, 315, 480);
        g2.setFont(new Font("", Font.PLAIN, 40));
        g2.drawString("" + playerPont, 320 - 30 + 200, 240 - 200);
        g2.drawString("" + otherPlayerPont, 320 - 30 - 200, 240 - 200);
        g2.fill(player);
        g2.fill(otherPlayer);
    }

}
