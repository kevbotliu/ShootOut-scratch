package com.kevinliu.game.entities;

import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Animation
{
    private int frameCount;
    private int frameDelay;
    private int currentFrame;
    private int animationDirection;
    private int totalFrames;
    private boolean stopped;
    private List<Frame> frames = new ArrayList();

    public Animation(BufferedImage[] frames, int frameDelay)
    {
        this.frameDelay = frameDelay;
        this.stopped = true;
        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }
        this.frameCount = 0;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();
    }

    public void start()
    {
        if (!this.stopped) {
            return;
        }
        if (this.frames.size() == 0) {
            return;
        }
        this.stopped = false;
    }

    public void stop()
    {
        if (this.frames.size() == 0) {
            return;
        }
        this.stopped = true;
    }

    public void restart()
    {
        if (this.frames.size() == 0) {
            return;
        }
        this.stopped = false;
        this.currentFrame = 0;
    }

    public void reset()
    {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    private void addFrame(BufferedImage frame, int duration)
    {
        if (duration <= 0)
        {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }
        this.frames.add(new Frame(frame, duration));
        this.currentFrame = 0;
    }

    public BufferedImage getSprite()
    {
        return (this.frames.get(this.currentFrame)).getFrame();
    }

    public void update()
    {
        if (!this.stopped)
        {
            this.frameCount += 1;
            if (this.frameCount > this.frameDelay)
            {
                this.frameCount = 0;
                this.currentFrame += this.animationDirection;
                if (this.currentFrame > this.totalFrames - 1) {
                    this.currentFrame = 0;
                } else if (this.currentFrame < 0) {
                    this.currentFrame = (this.totalFrames - 1);
                }
            }
        }
    }
}
