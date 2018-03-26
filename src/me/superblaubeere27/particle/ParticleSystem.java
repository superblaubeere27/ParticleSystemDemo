package me.superblaubeere27.particle;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystem {
    private static final float SPEED = 0.1f;
    private static final float MIN_DISTANCE = 150f;
    private List<Particle> particleList = new ArrayList<>();

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

            for (Particle particle1 : particleList) {
                float distance = particle.getDistanceTo(particle1);
                if (distance < MIN_DISTANCE && (particle.getDistanceTo(Mouse.getX(), Display.getHeight() - Mouse.getY()) < MIN_DISTANCE || particle1.getDistanceTo(Mouse.getX(), Display.getHeight() - Mouse.getY()) < MIN_DISTANCE)) {
                    float alpha = Math.min(1.0f, Math.min(1.0f, 1.0f - distance / MIN_DISTANCE));

                    GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
                    GL11.glLineWidth(0.5f);
                    GL11.glBegin(GL11.GL_LINES);

                    GL11.glVertex2f(particle.getX(), particle.getY());
                    GL11.glVertex2f(particle1.getX(), particle1.getY());
                    GL11.glEnd();
                }
            }
        }
    }
}
