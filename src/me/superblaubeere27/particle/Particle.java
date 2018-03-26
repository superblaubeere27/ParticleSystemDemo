package me.superblaubeere27.particle;

import me.superblaubeere27.util.MathUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import java.util.Random;

public class Particle {
    private static final Random random = new Random();

    private Vector2f velocity;
    private float x;
    private float y;
    private float size;


    public Particle(Vector2f velocity, float x, float y, float size) {
        this.velocity = velocity;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public static Particle generateParticle() {
        Vector2f velocity = new Vector2f((float) (Math.random() * 2.0f - 1.0f), (float) (Math.random() * 2.0f - 1.0f));
        float x = random.nextInt(Display.getWidth());
        float y = random.nextInt(Display.getHeight());
        float size = (float) (Math.random() * 4.0f) + 1.0f;
        return new Particle(velocity, x, y, size);
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void tick(int delta, float speed) {
        x += velocity.getX() * delta * speed;
        y += velocity.getY() * delta * speed;

        if (x > Display.getWidth()) x = 0;
        if (x < 0) x = Display.getWidth();

        if (y > Display.getHeight()) y = 0;
        if (y < 0) y = Display.getHeight();
    }

    public float getDistanceTo(Particle particle1) {
        return getDistanceTo(particle1.getX(), particle1.getY());
    }

    public float getDistanceTo(float x, float y) {
        return (float) MathUtil.distance(getX(), getY(), x, y);
    }
}
