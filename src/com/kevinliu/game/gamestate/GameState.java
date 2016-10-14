package com.kevinliu.game.gamestate;

import java.awt.Graphics;

public abstract class GameState
{
    protected GameStateManager gsm;

    public abstract void tick();

    public abstract void init();

    public abstract void draw(Graphics paramGraphics);

    public abstract void keyPressed(int paramInt);

    public abstract void keyReleased(int paramInt);
}
