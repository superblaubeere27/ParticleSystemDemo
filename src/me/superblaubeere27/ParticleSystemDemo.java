package me.superblaubeere27;

import me.superblaubeere27.particle.ParticleSystem;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

import javax.swing.*;
import java.awt.*;

public class ParticleSystemDemo extends BasicGame {
    private ParticleSystem particleSystem;
    private boolean mouse;
    private boolean rainbow;
    private int dist;

    public ParticleSystemDemo() {
        super("ParticleSystem");
    }

    public static void main(String args[]) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(6, 2));
        JSpinner width = new JSpinner();
        width.setValue(1280);
        JSpinner height = new JSpinner();
        height.setValue(720);
        JCheckBox fullScreen = new JCheckBox("");
        JCheckBox connectMouse = new JCheckBox("");
        JCheckBox rainBow = new JCheckBox("");
        JLabel distLabel = new JLabel("connectingDistance (150):");
        JSlider dist = new JSlider(SwingConstants.HORIZONTAL,100, 500, 150);
        dist.addChangeListener(e -> {

            distLabel.setText("connectingDistance (".concat(String.valueOf(dist.getValue()).concat("):")));

        });
        jPanel.add(new JLabel("Width:"));
        jPanel.add(width);
        jPanel.add(new JLabel("Height:"));
        jPanel.add(height);
        jPanel.add(new JLabel("Fullscreen:"));
        jPanel.add(fullScreen);
        jPanel.add(new JLabel("Rainbow:"));
        jPanel.add(rainBow);
        jPanel.add(new JLabel("ConnectMouse:"));
        jPanel.add(connectMouse);
        jPanel.add(distLabel);
        jPanel.add(dist);
        if (JOptionPane.showConfirmDialog(null, jPanel, "ParticleSystem settings", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            ParticleSystemDemo demo = new ParticleSystemDemo();
            AppGameContainer container = new AppGameContainer(demo);
            demo.mouse = connectMouse.isSelected();
            demo.dist = dist.getValue();
            demo.rainbow = rainBow.isSelected();
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
        particleSystem = new ParticleSystem(200, mouse, rainbow, dist);
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
