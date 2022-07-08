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

        while (!shouldStop) {
            shouldStop = bucket.isFull();
            Bucket tempBucket = new Bucket(10);

            for (int i = 0; i < bucket.getCapacity(); i++) {
                for (int j = 0; j < bucket.getCapacity(); j++) {
                    Particle current = bucket.getParticle(i, j);

                    if (current.getType() == ParticleType.WATER) {
                        if (j < bucket.getCapacity() - 1 && bucket.getParticle(i, j + 1).getType() == ParticleType.VOID) {
                            tempBucket.setParticle(i, j + 1, current);
                        } else {
                            Particle next = bucket.getParticle(i + current.getDirection(), j);
                            Particle nextTemp = tempBucket.getParticle(i + current.getDirection(), j);

                            if (next != null && nextTemp != null && next.getType() == nextTemp.getType() && nextTemp.getType() == ParticleType.VOID) {
                                tempBucket.setParticle(i + current.getDirection(), j, current);
                            } else {
                                current.setDirection(current.getDirection() * -1);
                                next = bucket.getParticle(i + current.getDirection(), j);
                                nextTemp = tempBucket.getParticle(i + current.getDirection(), j);
                                if (next != null && nextTemp != null && next.getType() == nextTemp.getType() && nextTemp.getType() == ParticleType.VOID) {
                                    tempBucket.setParticle(i + current.getDirection(), j, current);
                                } else {
                                    tempBucket.setParticle(i, j, current);
                                }
                            }
                        }
                    }
                }
            }

            this.bucket = tempBucket;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main.getInstance().thread.start();
    }
}
