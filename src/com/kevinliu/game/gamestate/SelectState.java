package com.kevinliu.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Stack;

public class SelectState
        extends GameState
{
    private boolean escape = false;
    private int x = 0;
    Image img = Toolkit.getDefaultToolkit().createImage("res/background_select.png");
    Image imgMenu = Toolkit.getDefaultToolkit().createImage("res/background.png");

    public SelectState(GameStateManager gsm)
    {
        this.gsm = gsm;
    }

    public void tick() {}

    public void init() {}

    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(this.img, 0, 0, null);

        g2d.setColor(new Color(25, 25, 25));
        g2d.setFont(new Font("Century Gothic", 2, 30));
        g2d.drawString("level select", 30, 40);
        g2d.drawString("ESC", 1200, 40);

        this.gsm.states.push(new Level1State(this.gsm));
        if (this.escape)
        {
            g2d.drawImage(this.imgMenu, 0, 0, null);

            g2d.setColor(new Color(214, 61, 10));
            g2d.fillRect(0, 0, 1246 - this.x, 720);
            g2d.setColor(new Color(160, 46, 8));
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
        if (k == 27) {
            this.escape = true;
        }
    }

    public void keyReleased(int k) {}
}
