package com.kevinliu.game.gamestate;

import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager
{
    public Stack<GameState> states;

    public GameStateManager()
    {
        this.states = new Stack();
        this.states.push(new MenuState(this));
    }

    public void tick()
    {
        (this.states.peek()).tick();
    }

    public void draw(Graphics g)
    {
        (this.states.peek()).draw(g);
    }

    public void keyPressed(int k)
    {
        (this.states.peek()).keyPressed(k);
    }

    public void keyReleased(int k)
    {
        (this.states.peek()).keyReleased(k);
    }
}
