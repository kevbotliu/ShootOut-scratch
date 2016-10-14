package com.kevinliu.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.util.Stack;

public class MenuState
        extends GameState
{
    private String[] options = { "START GAME", "HOW TO PLAY", "OPTIONS", "QUIT" };
    private int currentSelection = 0;
    private boolean enter0 = false;
    private boolean enter1 = false;
    private boolean enter2 = false;
    private int x = 0;
    Image img = Toolkit.getDefaultToolkit().createImage("res/background.png");

    public MenuState(GameStateManager gsm)
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

        Font f = getFont(160);
        g2d.setColor(new Color(224, 192, 0));
        g2d.fillRect(30, 212, 490, 170);
        g2d.setFont(f);
        g2d.setColor(new Color(25, 25, 25));
        g2d.drawString("shoot ", 52, 360);
        g2d.drawString("OUT", 544, 360);

        g2d.setColor(new Color(37, 85, 89));
        g2d.fillRect(30, 480, 1220, 210);

        f = getFont(40);
        for (int i = 0; i < this.options.length; i++) {
            if (i == this.currentSelection)
            {
                if (i == 0)
                {
                    g2d.setColor(new Color(25, 25, 25));
                    g2d.fillRoundRect(50, 508 + i * 45, 16, 16, 8, 8);
                }
                if (i == 1)
                {
                    g2d.setColor(new Color(25, 25, 25));
                    g2d.fillRoundRect(50, 508 + i * 45, 16, 16, 8, 8);
                }
                if (i == 2)
                {
                    g2d.setColor(new Color(25, 25, 25));
                    g2d.fillRoundRect(50, 508 + i * 45, 16, 16, 8, 8);
                }
                if (i == 3)
                {
                    g2d.setColor(new Color(25, 25, 25));
                    g2d.fillRoundRect(50, 508 + i * 45, 16, 16, 8, 8);
                }
                g2d.setColor(new Color(25, 25, 25));
                g2d.setFont(f);
                g2d.drawString(this.options[i], 86, 532 + i * 45);
            }
            else
            {
                g2d.setColor(new Color(25, 25, 25));
                g2d.setFont(f);
                g2d.drawString(this.options[i], 86, 532 + i * 45);
            }
        }
        f = getFont(24);
        g2d.setFont(f);
        g2d.drawString("MENU", 30, 30);

        f = getFont(40);
        if (this.enter0)
        {
            g2d.drawImage(this.img, 0, 0, null);

            g2d.setColor(new Color(37, 85, 89));
            g2d.fillRect(30 - this.x, 480, 1220, 210);

            g2d.setColor(new Color(25, 25, 25));
            g2d.fillRect(50 - this.x, 508, 16, 16);
            g2d.setFont(f);
            g2d.drawString("START GAME", 86 - this.x, 532);
            g2d.setFont(f);
            g2d.drawString("HOW TO PLAY", 86 - this.x, 577);
            g2d.drawString("OPTIONS", 86 - this.x, 622);
            g2d.drawString("QUIT", 86 - this.x, 667);

            g2d.setColor(new Color(237, 85, 15));
            if (720.0D - 1.6D * this.x > 0.0D) {
                g2d.fillRect(0, 720 - (int)(1.6D * this.x), 1280, 720);
            } else {
                g2d.fillRect(0, 0, 1280, 720);
            }
            g2d.setColor(new Color(237, 85, 15));
            if (this.x < 360) {
                g2d.fillRect(0, 0, 1280, this.x / 6);
            } else {
                g2d.fillRect(0, 0, 1280, 60);
            }
            this.x += 60;
            if (this.x > 800)
            {
                this.x = 0;
                this.enter0 = false;
                this.gsm.states.push(new SelectState(this.gsm));
            }
        }
        if (this.enter1)
        {
            g2d.drawImage(this.img, 0, 0, null);

            g2d.setColor(new Color(0, 173, 130));
            g2d.fillRect(30 - this.x, 480, 1220, 210);

            g2d.setColor(new Color(25, 25, 25));
            g2d.fillRect(50 - this.x, 553, 16, 16);
            g2d.setFont(f);
            g2d.drawString("HOW TO PLAY", 80 - this.x, 577);
            g2d.setFont(f);
            g2d.drawString("Start Game", 80 - this.x, 532);
            g2d.drawString("OPTIONS", 80 - this.x, 622);
            g2d.drawString("QUIT", 80 - this.x, 667);

            g2d.setColor(new Color(123, 224, 0));
            if (720.0D - 1.6D * this.x > 0.0D) {
                g2d.fillRect(0, 720 - (int)(1.6D * this.x), 1280, 720);
            } else {
                g2d.fillRect(0, 0, 1280, 720);
            }
            if (this.x < 360)
            {
                g2d.setColor(new Color(93, 170, 0));
                g2d.fillRect(0, 0, 1280, this.x / 6);
            }
            else
            {
                g2d.setColor(new Color(93, 170, 0));
                g2d.fillRect(0, 0, 1280, 60);
            }
            this.x += 60;
            if (this.x > 800)
            {
                this.x = 0;
                this.enter1 = false;
                this.gsm.states.push(new HelpState(this.gsm));
            }
        }
        if (this.enter2)
        {
            g2d.drawImage(this.img, 0, 0, null);

            g2d.setColor(new Color(0, 173, 130));
            g2d.fillRect(30 - this.x, 480, 1220, 210);

            g2d.setColor(new Color(25, 25, 25));
            g2d.fillOval(50 - this.x, 598, 16, 16);
            g2d.setFont(new Font("Century Gothic", 1, 40));
            g2d.drawString("OPTIONS", 80 - this.x, 622);
            g2d.setFont(f);
            g2d.drawString("HOW TO PLAY", 80 - this.x, 577);
            g2d.drawString("Start Game", 80 - this.x, 532);
            g2d.drawString("QUIT", 80 - this.x, 667);

            g2d.setColor(new Color(244, 216, 10));
            if (720.0D - 1.6D * this.x > 0.0D) {
                g2d.fillRect(0, 720 - (int)(1.6D * this.x), 1280, 720);
            } else {
                g2d.fillRect(0, 0, 1280, 720);
            }
            g2d.setColor(new Color(193, 171, 0));
            if (this.x < 360) {
                g2d.fillRect(0, 0, 1280, this.x / 6);
            } else {
                g2d.fillRect(0, 0, 1280, 60);
            }
            this.x += 60;
            if (this.x > 800)
            {
                this.x = 0;
                this.enter2 = false;
                this.gsm.states.push(new OptionState(this.gsm));
            }
        }
    }

    public void keyPressed(int k)
    {
        if (k == 40)
        {
            this.currentSelection += 1;
            if (this.currentSelection >= this.options.length) {
                this.currentSelection -= 1;
            }
        }
        else if (k == 38)
        {
            this.currentSelection -= 1;
            if (this.currentSelection < 0) {
                this.currentSelection += 1;
            }
        }
        if (k == 10) {
            if (this.currentSelection == 0) {
                this.enter0 = true;
            } else if (this.currentSelection == 1) {
                this.enter1 = true;
            } else if (this.currentSelection == 2) {
                this.enter2 = true;
            } else if (this.currentSelection == 3) {
                System.exit(0);
            }
        }
    }

    public void keyReleased(int k) {}

    public static Font getFont(int size)
    {
        try
        {
            return Font.createFont(0, new FileInputStream(new File("res/04B_19__.TTF"))).deriveFont(0, size);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
