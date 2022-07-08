package fr.celestgames.partikle.io;

import fr.celestgames.partikle.Bucket;
import fr.celestgames.partikle.Main;
import fr.celestgames.partikle.particles.Particle;
import fr.celestgames.partikle.particles.ParticleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window implements Runnable {
    private static Window instance;
    private final Thread thread;

    private final JPanel panel;
    private final JFrame frame;
    private int width;
    private int height;

    private Window() {
        thread = new Thread(this);
        panel = new JPanel();
        frame = new JFrame("Partikle");

        width = 800;
        height = 600;

        panel.setPreferredSize(new Dimension(width, height));
        panel.setFocusable(true);
        panel.setBackground(Color.BLACK);

        panel.addKeyListener(Keyboard.getInstance());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
    }

    public static Window getInstance() {
        if (instance == null) {
            instance = new Window();
        }
        return instance;
    }

    @Override
    public void run() {
        boolean shouldClose = false;
        boolean rKeyPressed = false;
        boolean aKeyPressed = false;
        frame.setVisible(true);

        while (!shouldClose) {
            width = frame.getWidth();
            height = frame.getHeight();

            if (Keyboard.getInstance().isKeyDown(KeyEvent.VK_R)) {
                if (!rKeyPressed) {
                    rKeyPressed = true;
                    Main.getInstance().newBucket();
                }
            } else {
                rKeyPressed = false;
            }

            aKeyPressed = Keyboard.getInstance().isKeyDown(KeyEvent.VK_A);
            if (aKeyPressed) {
                Main.getInstance().getBucket().add(new Particle("water", ParticleType.WATER));
            }

            shouldClose = Keyboard.getInstance().isKeyDown(KeyEvent.VK_ESCAPE);

            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void repaint() {
        Graphics2D g2 = (Graphics2D) panel.getGraphics();

        if (Main.getInstance().getBucket() != null) {
            Bucket bucket = Main.getInstance().getBucket();

            for (int i = 0; i < bucket.getCapacity(); i++) {
                for (int j = 0; j < bucket.getCapacity(); j++) {
                    Particle particle = bucket.getParticle(i, j);
                    if (particle != null) {
                        g2.setColor(particle.getType().getColor());
                        g2.fillRect(i * 32, j * 32, 32, 32);
                    }
                    g2.setColor(Color.white);
                    g2.drawRect(i * 32, j * 32, 32, 32);
                }
            }
        }

        g2.dispose();
    }

    public Thread getThread() {
        return thread;
    }
}
