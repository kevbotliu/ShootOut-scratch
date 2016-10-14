package com.kevinliu.game.main;

import com.kevinliu.game.gamestate.GameStateManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GamePanel
        extends JPanel
        implements Runnable, KeyListener
{
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private Thread thread;
    private boolean isRunning = false;
    private int FPS = 60;
    private long targetTime = 1000 / this.FPS;
    private GameStateManager gsm;

    public GamePanel()
    {
        setPreferredSize(new Dimension(1280, 720));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify()
    {
        super.addNotify();
        if (this.thread == null)
        {
            this.thread = new Thread(this);
            addKeyListener(this);
            this.thread.start();
        }
    }

    private void init()
    {
        this.isRunning = true;

        this.gsm = new GameStateManager();
    }

    public void run()
    {
        init();
        while (this.isRunning)
        {
            long start = System.nanoTime();

            tick();
            repaint();

            long elapsed = System.nanoTime() - start;
            long wait = this.targetTime - elapsed / 1000000L;
            if (wait < 0L) {
                wait = 5L;
            }
            try
            {
                Thread.sleep(wait);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void tick()
    {
        this.gsm.tick();
    }

    public void paintComponent(Graphics g) {
        this.gsm.draw(g);
    }

    public void keyPressed(KeyEvent e)
    {
        this.gsm.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e)
    {
        this.gsm.keyReleased(e.getKeyCode());
    }

    public void keyTyped(KeyEvent arg0) {}
}
