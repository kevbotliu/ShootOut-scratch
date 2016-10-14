package com.kevinliu.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Stack;

public class HelpState
        extends GameState
{
    private String[] text = { "1", "2", "3", "4", "5" };
    private int currentPage = 0;
    private int currentArrow = 0;
    private boolean escape = false;
    private int x = 0;
    Image img = Toolkit.getDefaultToolkit().createImage("res/background_help.png");
    Image imgMenu = Toolkit.getDefaultToolkit().createImage("res/background.png");

    public HelpState(GameStateManager gsm)
    {
        this.gsm = gsm;
    }

    public void tick() {}

    public void init() {}

    public void draw(Graphics g)
    {
        int[] x1 = { 60, 90, 90 };
        int[] y1 = { 360, 390, 330 };
        int[] x2 = { 1220, 1190, 1190 };
        int[] y2 = { 360, 390, 330 };

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.drawImage(this.img, 0, 0, null);
        if (this.currentArrow == -1)
        {
            g2d.setColor(Color.yellow);
            g2d.fillPolygon(x1, y1, 3);
            g2d.setColor(new Color(25, 25, 25));
            g2d.fillPolygon(x2, y2, 3);
            this.currentArrow += 1;
            if (this.currentPage > 0) {
                this.currentPage -= 1;
            }
        }
        else if (this.currentArrow == 1)
        {
            g2d.setColor(new Color(25, 25, 25));
            g2d.fillPolygon(x1, y1, 3);
            g2d.setColor(Color.yellow);
            g2d.fillPolygon(x2, y2, 3);
            this.currentArrow -= 1;
            if (this.currentPage < this.text.length - 1) {
                this.currentPage += 1;
            }
        }
        else
        {
            g2d.setColor(new Color(25, 25, 25));
            g2d.fillPolygon(x1, y1, 3);
            g2d.fillPolygon(x2, y2, 3);
        }
        g2d.setFont(new Font("Century Gothic", 0, 48));
        g2d.setColor(new Color(25, 25, 25));
        g2d.drawString(this.text[this.currentPage], 640, 360);

        g2d.setColor(new Color(25, 25, 25));
        g2d.setFont(new Font("Century Gothic", 2, 30));
        g2d.drawString("how to play", 30, 40);
        g2d.drawString("ESC", 1200, 40);
        if (this.escape)
        {
            g2d.drawImage(this.imgMenu, 0, 0, null);

            g2d.setColor(new Color(123, 224, 0));
            g2d.fillRect(0, 0, 1246 - this.x, 720);
            g2d.setColor(new Color(93, 170, 0));
            g2d.fillRect(0, 0, 1246 - this.x, 60);
            this.x += 130;
            if (this.x > 1200)
            {
                this.x = 0;
                this.escape = false;
                this.gsm.states.pop();
            }
        }
    }

    public void keyPressed(int k)
    {
        if (k == 39) {
            this.currentArrow += 1;
        } else if (k == 37) {
            this.currentArrow -= 1;
        }
        if (k == 27) {
            this.escape = true;
        }
    }

    public void keyReleased(int k)
    {
        if (k != 39) {}
    }
}
