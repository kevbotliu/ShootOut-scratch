package com.kevinliu.game.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Player
{
    private float x = 640.0F;
    private float y = 0.0F;
    private int width = 80;
    private int height = 80;
    private int health = 1000;
    private float gravity = 0.7F;
    private boolean left;
    private boolean right;
    private float dx = 0.0F;
    private float dy = 0.0F;
    private boolean falling = true;
    private boolean duck = false;
    private boolean jumping = false;
    private boolean inAir = false;
    private boolean facingRight = true;
    private boolean facingLeft = false;
    private boolean attacking = false;
    private boolean isShooting = false;
    private boolean readyToFire = true;
    private boolean right1 = true;
    private boolean left1 = false;
    private boolean isAlive = true;
    private int id;
    private Rectangle b;
    private double t;
    private double q;
    private final Set<Integer> pressed = new HashSet();
    private static final float MAX_SPEED = 15.0F;
    private BufferedImage[] walkingLeft = { Sprite.getSprite(0, 0), Sprite.getSprite(1, 0) };
    private BufferedImage[] walkingRight = { Sprite.getSprite(0, 1), Sprite.getSprite(1, 1) };
    private BufferedImage[] standingRight = { Sprite.getSprite(0, 2) };
    private BufferedImage[] standingLeft = { Sprite.getSprite(1, 2) };
    private BufferedImage[] attackingRight = { Sprite.getSprite(0, 3), Sprite.getSprite(1, 3) };
    private BufferedImage[] attackingLeft = { Sprite.getSprite(0, 4), Sprite.getSprite(1, 4) };
    private BufferedImage[] attackingRightWalking = { Sprite.getSprite(2, 3), Sprite.getSprite(3, 3) };
    private BufferedImage[] attackingLeftWalking = { Sprite.getSprite(2, 4), Sprite.getSprite(3, 4) };
    private BufferedImage[] duckingRight = { Sprite.getSprite(1, 5) };
    private BufferedImage[] duckingLeft = { Sprite.getSprite(0, 5) };
    private Animation walkLeft = new Animation(this.walkingLeft, 10);
    private Animation walkRight = new Animation(this.walkingRight, 10);
    private Animation standRight = new Animation(this.standingRight, 10);
    private Animation standLeft = new Animation(this.standingLeft, 10);
    private Animation attackRight = new Animation(this.attackingRight, 10);
    private Animation attackLeft = new Animation(this.attackingLeft, 10);
    private Animation attackRightWalk = new Animation(this.attackingRightWalking, 10);
    private Animation attackLeftWalk = new Animation(this.attackingLeftWalking, 10);
    private Animation duckRight = new Animation(this.duckingRight, 10);
    private Animation duckLeft = new Animation(this.duckingLeft, 10);
    private Animation animation = this.standRight;

    public Player(float x, float y, int id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void tick()
    {
        this.animation.start();
        this.x += this.dx;

        this.y += this.dy;
        if ((this.falling) || (this.jumping))
        {
            this.dy += this.gravity;
            if (this.dy > 15.0F) {
                this.dy = 15.0F;
            }
        }
        if (this.right) {
            this.x += 5.0F;
        }
        if (this.left) {
            this.x -= 5.0F;
        }
        this.animation.update();
        if (this.health < 0) {
            this.isAlive = false;
        }
    }

    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        if (this.id == 1) {
            g2d.setColor(Color.ORANGE);
        } else if (this.id == 2) {
            g2d.setColor(Color.BLUE);
        }
        g2d.drawImage(this.animation.getSprite(), (int)this.x, (int)this.y, null);

        g2d.setFont(new Font("Century Gothic", 1, 14));
        if (this.id == 1) {
            g2d.drawString("Player 1 +" + this.health, this.x + 15.0F, this.y - 15.0F);
        } else {
            g2d.drawString("Player 2 +" + this.health, this.x + 15.0F, this.y - 15.0F);
        }
        if (this.isShooting)
        {
            g2d.setColor(Color.BLACK);
            g2d.fillRect((int)this.b.getX(), (int)this.b.getY(), (int)this.b.getWidth(), (int)this.b.getHeight());
            fired();
            if (Math.abs((int)this.b.getX() - this.x) > 100.0F) {
                this.readyToFire = true;
            }
        }
    }

    public void fired()
    {
        if (this.right1) {
            this.b.x += 20;
        }
        if (this.left1) {
            this.b.x -= 20;
        }
    }

    public void keyPressed(int k)
    {
        this.pressed.add(Integer.valueOf(k));
        for (Iterator localIterator = this.pressed.iterator(); localIterator.hasNext();)
        {
            int s = ((Integer)localIterator.next()).intValue();
            if (this.id == 1)
            {
                if (s == 68)
                {
                    this.right = true;
                    this.facingLeft = false;
                    this.facingRight = true;
                    this.animation = this.walkRight;
                }
                if (s == 65)
                {
                    this.left = true;
                    this.facingRight = false;
                    this.facingLeft = true;
                    this.animation = this.walkLeft;
                }
                if ((s == 87) && (!this.inAir))
                {
                    this.jumping = true;
                    this.inAir = true;
                    setDy(-15.0F);
                }
                if (s == 83)
                {
                    this.duck = true;
                    if (this.facingRight) {
                        this.animation = this.duckRight;
                    } else {
                        this.animation = this.duckLeft;
                    }
                }
                if ((s == 71) &&
                        (this.readyToFire))
                {
                    this.isShooting = true;
                    this.b = new Rectangle((int)this.x + 40, (int)this.y + 45, 10, 8);
                    this.readyToFire = false;
                    if (this.facingRight)
                    {
                        this.left1 = false;
                        this.right1 = true;
                    }
                    else
                    {
                        this.right1 = false;
                        this.left1 = true;
                    }
                }
                if (s == 72)
                {
                    setAttacking(true);
                    if (this.facingRight != this.right) {
                        this.animation = this.attackRight;
                    } else if (this.facingLeft != this.left) {
                        this.animation = this.attackLeft;
                    } else if (this.right) {
                        this.animation = this.attackRightWalk;
                    } else {
                        this.animation = this.attackLeftWalk;
                    }
                }
            }
            if (this.id == 2)
            {
                if (s == 39)
                {
                    this.right = true;
                    this.facingLeft = false;
                    this.facingRight = true;
                    this.animation = this.walkRight;
                }
                if (s == 37)
                {
                    this.left = true;
                    this.facingRight = false;
                    this.facingLeft = true;
                    this.animation = this.walkLeft;
                }
                if ((s == 38) && (!this.inAir))
                {
                    this.jumping = true;
                    this.inAir = true;
                    setDy(-15.0F);
                }
                if (s == 40)
                {
                    this.duck = true;
                    if (this.facingRight) {
                        this.animation = this.duckRight;
                    } else {
                        this.animation = this.duckLeft;
                    }
                }
                if ((s == 76) &&
                        (this.readyToFire))
                {
                    this.isShooting = true;
                    this.b = new Rectangle((int)this.x + 40, (int)this.y + 45, 10, 8);
                    this.readyToFire = false;
                    if (this.facingRight)
                    {
                        this.left1 = false;
                        this.right1 = true;
                    }
                    else
                    {
                        this.right1 = false;
                        this.left1 = true;
                    }
                }
                if (s == 80)
                {
                    setAttacking(true);
                    if (this.facingRight != this.right) {
                        this.animation = this.attackRight;
                    } else if (this.facingLeft != this.left) {
                        this.animation = this.attackLeft;
                    } else if (this.right) {
                        this.animation = this.attackRightWalk;
                    } else {
                        this.animation = this.attackLeftWalk;
                    }
                }
            }
        }
    }

    public void keyReleased(int k)
    {
        this.pressed.remove(Integer.valueOf(k));
        this.animation.stop();
        this.animation.reset();
        if (this.id == 1)
        {
            if (k == 68)
            {
                this.right = false;
                this.animation = this.standRight;
            }
            if (k == 65)
            {
                this.left = false;
                this.right = false;
                this.animation = this.standLeft;
            }
            if (k == 87) {
                this.jumping = false;
            }
            if (k == 83)
            {
                this.duck = false;
                if (this.facingRight) {
                    this.animation = this.standRight;
                } else {
                    this.animation = this.standLeft;
                }
            }
            if (k == 72)
            {
                setAttacking(false);
                if (this.facingRight) {
                    this.animation = this.standRight;
                } else {
                    this.animation = this.standLeft;
                }
            }
        }
        else if (this.id == 2)
        {
            if (k == 39)
            {
                this.right = false;
                this.animation = this.standRight;
            }
            if (k == 37)
            {
                this.left = false;
                this.right = false;
                this.animation = this.standLeft;
            }
            if (k == 38) {
                this.jumping = false;
            }
            if (k == 40)
            {
                this.duck = false;
                if (this.facingRight) {
                    this.animation = this.standRight;
                } else {
                    this.animation = this.standLeft;
                }
            }
            if (k == 80)
            {
                setAttacking(false);
                if (this.facingRight) {
                    this.animation = this.standRight;
                } else {
                    this.animation = this.standLeft;
                }
            }
        }
    }

    public boolean getRight()
    {
        return this.right;
    }

    public boolean getLeft()
    {
        return this.left;
    }

    public void setRight(boolean right)
    {
        this.right = right;
    }

    public void setLeft(boolean left)
    {
        this.left = left;
    }

    public float getX()
    {
        return this.x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return this.y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getDx()
    {
        return this.dx;
    }

    public void setDx(float dx)
    {
        this.dx = dx;
    }

    public float getDy()
    {
        return this.dy;
    }

    public void setDy(float dy)
    {
        this.dy = dy;
    }

    public double getWidth()
    {
        return this.width;
    }

    public double getHeight()
    {
        return this.height;
    }

    public boolean isJumping()
    {
        return this.jumping;
    }

    public void setJumping(boolean jumping)
    {
        this.jumping = jumping;
    }

    public boolean isFalling()
    {
        return this.falling;
    }

    public void setFalling(boolean falling)
    {
        this.falling = falling;
    }

    public boolean isInAir()
    {
        return this.inAir;
    }

    public int getId()
    {
        return this.id;
    }

    public void setInAir(boolean inAir)
    {
        this.inAir = inAir;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)this.x, (int)this.y + 2, 80, this.height - 4);
    }

    public Rectangle getBoundsTop()
    {
        return new Rectangle((int)this.x, (int)this.y, 80, 10);
    }

    public Rectangle getBoundsBottom()
    {
        return new Rectangle((int)this.x, (int)this.y + this.height - 10, 80, 10);
    }

    public int getHealth()
    {
        return this.health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public boolean isAttacking()
    {
        return this.attacking;
    }

    public void setAttacking(boolean attacking)
    {
        this.attacking = attacking;
    }

    public boolean isShooting()
    {
        return this.isShooting;
    }

    public void setShooting(boolean isShooting)
    {
        this.isShooting = isShooting;
    }

    public boolean isFacingRight()
    {
        return this.facingRight;
    }

    public boolean isFacingLeft()
    {
        return this.facingLeft;
    }

    public Rectangle getBullet()
    {
        return this.b;
    }

    public boolean isAlive()
    {
        return this.isAlive;
    }
}
