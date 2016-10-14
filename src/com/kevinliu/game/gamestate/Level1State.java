package com.kevinliu.game.gamestate;

import com.kevinliu.game.entities.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Level1State
        extends GameState
{
    Image img = Toolkit.getDefaultToolkit().createImage("res/level1.png");
    private float x = 640.0F;
    private float y = 0.0F;
    private int p1Score = 0;
    private int p2Score = 0;
    private Player p1;
    private Player p2;
    ArrayList<Rectangle> r = new ArrayList();
    Rectangle r1 = new Rectangle(464, 576, 336, 144);
    Rectangle r2 = new Rectangle(224, 544, 240, 176);
    Rectangle r3 = new Rectangle(160, 496, 64, 224);
    Rectangle r4 = new Rectangle(0, 416, 160, 304);
    Rectangle r5 = new Rectangle(800, 544, 256, 176);
    Rectangle r6 = new Rectangle(1056, 496, 64, 224);
    Rectangle r7 = new Rectangle(1120, 416, 160, 304);
    Rectangle r8 = new Rectangle(224, 256, 144, 48);
    Rectangle r9 = new Rectangle(368, 224, 544, 80);
    Rectangle r10 = new Rectangle(912, 256, 144, 48);
    Rectangle r11 = new Rectangle(0, 0, 1, 720);
    Rectangle r12 = new Rectangle(1280, 0, 1, 720);
    Rectangle bullet1;
    Rectangle bullet2;

    public Level1State(GameStateManager gsm)
    {
        this.p1 = new Player(this.x, this.y, 1);
        this.p2 = new Player(this.x, this.y + 400.0F, 2);

        this.bullet1 = this.p1.getBullet();
        this.bullet2 = this.p2.getBullet();

        this.gsm = gsm;
        this.r.add(this.r1);
        this.r.add(this.r2);
        this.r.add(this.r3);
        this.r.add(this.r4);
        this.r.add(this.r5);
        this.r.add(this.r6);
        this.r.add(this.r7);
        this.r.add(this.r7);
        this.r.add(this.r8);
        this.r.add(this.r9);
        this.r.add(this.r10);
        this.r.add(this.r11);
        this.r.add(this.r12);
    }

    public void tick()
    {
        this.p1.tick();
        this.p2.tick();
        for (int i = 0; i < this.r.size(); i++)
        {
            if (this.p1.getBoundsBottom().intersects(this.r.get(i)))
            {
                this.p1.setY((float)((this.r.get(i)).getY() - this.p1.getHeight()));
                this.p1.setInAir(false);
                this.p1.setFalling(false);
                this.p1.setJumping(false);
            }
            else
            {
                this.p1.setFalling(true);
            }
            if (this.p1.getBoundsTop().intersects(this.r.get(i)))
            {
                this.p1.setY((float)((this.r.get(i)).getY() + (this.r.get(i)).getHeight()));
                this.p1.setDy(1.0F);
                this.p1.setInAir(true);
                this.p1.setFalling(true);
                this.p1.setJumping(true);
            }
            if (this.p2.getBoundsBottom().intersects(this.r.get(i)))
            {
                this.p2.setY((float)((this.r.get(i)).getY() - this.p2.getHeight()));
                this.p2.setInAir(false);
                this.p2.setFalling(false);
                this.p2.setJumping(false);
            }
            else
            {
                this.p2.setFalling(true);
            }
            if (this.p2.getBoundsTop().intersects(this.r.get(i)))
            {
                this.p2.setY((float)((this.r.get(i)).getY() + (this.r.get(i)).getHeight()));
                this.p2.setDy(1.0F);
                this.p2.setInAir(true);
                this.p2.setFalling(true);
                this.p2.setJumping(true);
            }
        }
        if (testCollision(this.p1))
        {
            if (this.p1.getDx() > 0.0F) {
                this.p1.setX(this.p1.getX() - 5.0F);
            }
            if (this.p1.getDx() < 0.0F) {
                this.p1.setX(this.p1.getX() + 5.0F);
            }
        }
        if (testCollision(this.p2))
        {
            if (this.p2.getDx() > 0.0F) {
                this.p2.setX(this.p2.getX() - 5.0F);
            }
            if (this.p2.getDx() < 0.0F) {
                this.p2.setX(this.p2.getX() + 5.0F);
            }
        }
        long lastPressProcessed = 0L;
        if ((Math.abs(this.p1.getY() - this.p2.getY()) < 10.0F) && (Math.abs(this.p1.getX() - this.p2.getX()) < 40.0F) &&
                (System.currentTimeMillis() - lastPressProcessed > 1000L))
        {
            if (this.p1.isAttacking()) {
                this.p2.setHealth(this.p2.getHealth() - 2);
            }
            if (this.p2.isAttacking()) {
                this.p1.setHealth(this.p1.getHealth() - 2);
            }
            lastPressProcessed = System.currentTimeMillis();
        }
        Player localPlayer;
        if (!this.p2.isAlive())
        {
            this.p1Score += 1;
            this.p2 = null;
            localPlayer = new Player(this.x, this.y, 2);
        }
        if (!this.p1.isAlive())
        {
            this.p2Score += 1;
            this.p1 = null;
            localPlayer = new Player(this.x, this.y, 1);
        }
    }

    public void init() {}

    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;

        g.clearRect(0, 0, 1280, 720);

        g2d.setColor(new Color(44, 192, 18));
        g2d.drawRect(0, 0, 1280, 720);
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.valueOf(this.p1Score), 320, 360);

        g.drawImage(this.img, 0, 0, null);
        this.p1.draw(g);
        this.p2.draw(g);
        for (int i = 0; i < this.r.size(); i++) {}
    }

    public void keyPressed(int k)
    {
        this.p1.keyPressed(k);
        this.p2.keyPressed(k);
    }

    public void keyReleased(int k)
    {
        this.p1.keyReleased(k);
        this.p2.keyReleased(k);
    }

    public boolean testCollision(Player p)
    {
        for (int i = 0; i < this.r.size(); i++) {
            if (p.getBounds().intersects(this.r.get(i))) {
                return true;
            }
        }
        return false;
    }
}
