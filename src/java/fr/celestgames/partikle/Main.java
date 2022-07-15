package fr.celestgames.partikle;

import fr.celestgames.partikle.io.Window;
import fr.celestgames.partikle.particles.Particle;
import fr.celestgames.partikle.particles.ParticleType;

public class Main implements Runnable {
    private static Main instance;
    private final Thread thread;

    private Bucket bucket;

    private Main() {
        thread = new Thread(this);
        bucket = new Bucket(10);
    }

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void newBucket() {
        bucket = new Bucket(10);
    }

    @Override
    public void run() {
        boolean shouldStop = false;
        Window.getInstance().getThread().start();
        bucket.setParticle(0, 0, new Particle("water", ParticleType.FLUID));

        while (!shouldStop) {
            shouldStop = Window.getInstance().shouldClose;

            bucket.update();

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main.getInstance().thread.start();
    }
}
