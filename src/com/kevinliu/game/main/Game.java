package com.kevinliu.game.main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Game
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("shootOUT");

        frame.add(new GamePanel(), "Center");

        frame.setContentPane(new GamePanel());
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
