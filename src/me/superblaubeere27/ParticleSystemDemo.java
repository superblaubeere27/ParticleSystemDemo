package me.superblaubeere27;

import me.superblaubeere27.particle.ParticleSystem;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

import javax.swing.*;
import java.awt.*;

public class ParticleSystemDemo extends BasicGame {
    private ParticleSystem particleSystem;

    public ParticleSystemDemo() {
        super("ParticleSystem");
    }

    public static void main(String args[]) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 4));
        JSpinner width = new JSpinner();
        width.setValue(1280);
        JSpinner height = new JSpinner();
        height.setValue(720);
        JCheckBox fullScreen = new JCheckBox("Fullscreen");
        jPanel.add(new JLabel("Width:"));
        jPanel.add(width);
        jPanel.add(new JLabel("Height:"));
        jPanel.add(height);
        jPanel.add(new JLabel("Fullscreen:"));
        jPanel.add(fullScreen);
        if (JOptionPane.showConfirmDialog(null, jPanel, "ParticleSystem settings", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            AppGameContainer container = new AppGameContainer(new ParticleSystemDemo());
            container.setDisplayMode((int) width.getValue(), (int) height.getValue(), fullScreen.isSelected());
            container.setVSync(true);
            container.setShowFPS(true);
            container.start();
        } catch (SlickException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        particleSystem = new ParticleSystem();
        particleSystem.addParticles(200);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        particleSystem.tick(delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.clearAlphaMap();
        graphics.clear();
        particleSystem.render();
    }
}
