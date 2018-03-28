package me.superblaubeere27.particle;

import me.superblaubeere27.util.MathUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ParticleSystem {
    private static final float SPEED = 0.1f;
    private List<Particle> particleList = new ArrayList<>();
    private boolean mouse;
    private int dist;

    public ParticleSystem(int initAmount, boolean mouse, int dist) {

        addParticles(initAmount);
        this.mouse = mouse;
        this.dist = dist;

    }

    public void addParticles(int amount) {
        for (int i = 0; i < amount; i++) {
            particleList.add(Particle.generateParticle());
        }
    }


    public void tick(int delta) {
        for (Particle particle : particleList) {
            particle.tick(delta, SPEED);
        }
    }

    public void render() {
        for (Particle particle : particleList) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPointSize(particle.getSize());
            GL11.glBegin(GL11.GL_POINTS);

            GL11.glVertex2f(particle.getX(), particle.getY());
            GL11.glEnd();

            if (mouse) {

                float distance = (float) MathUtil.distance(particle.getX(), particle.getY(), Mouse.getX(), Display.getHeight() - Mouse.getY());
                if (distance < dist) {
                    float alpha = Math.min(1.0f, Math.min(1.0f, 1.0f - distance / dist));
                    drawLine(particle.getX(), particle.getY(), Mouse.getX(), Display.getHeight() - Mouse.getY(), alpha);
                }

            } else {
                for (Particle particle1 : particleList) {
                    float distance = particle.getDistanceTo(particle1);
                    if (distance < dist
                            && (particle.getDistanceTo(Mouse.getX(), Display.getHeight() - Mouse.getY()) < dist
                            || particle1.getDistanceTo(Mouse.getX(), Display.getHeight() - Mouse.getY()) < dist)) {
                        float alpha = Math.min(1.0f, Math.min(1.0f, 1.0f - distance / dist));
                        drawLine(particle.getX(), particle.getY(), particle1.getX(), particle1.getY(), alpha);

                    }
                }
            }
        }
    }

    private void drawLine(float x, float y, float x1, float y1, float alpha) {

        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        GL11.glLineWidth(0.5f);
        GL11.glBegin(GL11.GL_LINES);

        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x1, y1);
        GL11.glEnd();

    }

}
