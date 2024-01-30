package Host.Ball;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Host.Player.Player;

public class Ball {

    public static int x = 320 - 30;
    public static int y = 240 - 30;
    public static int r = 15;

    public static boolean intersectOtherPlayer;

    public static int speedX = 5;
    public static int speedY = 5;
    
    public Ball(){

    }

    public void update(Player player){

        x += speedX;
        y += speedY;

        //? CHANGED HERE
        if(ballIntersectPlayer(Player.player) && speedX < 0){
            speedX *= -1;
        }

        if((intersectOtherPlayer || ballIntersectPlayer(Player.otherPlayer)) && speedX > 0){
            speedX *= -1;
        }

        if(y <= 0 && speedY < 0){
            speedY *= -1;
        }
        if(y >= 480 - 55 && speedY > 0){
            speedY *= -1;
        }
        if(x >= 640 - 30 && speedX > 0){
            speedX *= -1;
            x = 320 - 30 + 200;
            y = 240 - 30;
            Player.playerPont++;
        }
        if(x <= 0 && speedX < 0){
            speedX *= -1;
            x = 320 - 30 - 200;
            y = 240 - 30;
            Player.otherPlayerPont++;
        }
    }

    public void draw(Graphics2D g2){
        g2.fillOval(x, y, r, r);
    }

    public boolean ballIntersectPlayer(Rectangle player){

        // https://www.jeffreythompson.org/collision-detection/circle-rect.php

        float testX = x;
        float testY = y;

        // which edge is closest?
        if (x < player.x)         testX = player.x;      // test left edge
        else if (x > player.x+player.width) testX = player.x+player.width - 15;   // right edge
        if (y < player.y)         testY = player.y;      // top edge
        else if (y > player.y+player.height) testY = player.y+player.height;   // bottom edge

        // get distance from closest edges
        float distX = x-testX;
        float distY = y-testY;
        float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );

        // if the distance is less than the radius, collision!
        if (distance <= r) {
            return true;
        }
        return false;

    }

}
