package fr.celestgames.partikle;

import fr.celestgames.partikle.particles.Particle;
import fr.celestgames.partikle.particles.ParticleType;

public class Bucket {
    private Particle[][] particles;
    private final int capacity;

    public Bucket(int capacity) {
        this.capacity = capacity / 2;
        this.particles = new Particle[capacity][capacity];

        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                particles[i][j] = new Particle("air", ParticleType.GAS);
            }
        }
    }

    public void setParticle(int x, int y, Particle particle) {
        this.particles[y][x] = particle;
    }

    public Particle getParticle(int x, int y) {
        if (x > capacity - 1 || y > capacity - 1 || x < 0 || y < 0) {
            return null;
        } else {
            return particles[y][x];
        }
    }
    public int getCapacity() {
        return capacity;
    }

    public void update() {
        updateSolids();
        updateFluids();
        updateGases();
    }

    private void updateGases() {
    }

    private void updateFluids() {
        Bucket temp = new Bucket((capacity * 2));

        for (int y = 0; y < capacity; y++) {
            for (int x = 0; x < capacity; x++) {
                Particle current = getParticle(x, y);

                if (current.getType() == ParticleType.FLUID) {
                    Particle down = getParticle(x, y + 1);

                    if (down != null && down.getType() == ParticleType.GAS) {
                        temp.setParticle(x, y + 1, current);
                        temp.setParticle(x, y, down);
                    } else {
                        Particle left = getParticle(x - 1, y);
                        Particle right = getParticle(x + 1, y);

                        if (left == null) {
                            current.setDirection(1);
                        } else if (right == null) {
                            current.setDirection(-1);
                        }

                        if (temp.getParticle(x + current.getDirection(), y) != null
                                && temp.getParticle(x + current.getDirection(), y).getType() != ParticleType.FLUID) {
                            Particle next = getParticle(x + current.getDirection(), y);

                            temp.setParticle(x, y, next);
                            temp.setParticle(x + current.getDirection(), y, current);
                        } else {
                            temp.setParticle(x, y, current);
                        }
                    }
                }

            }
        }

        this.particles = temp.particles;
    }

    private void updateSolids() {
    }
}
