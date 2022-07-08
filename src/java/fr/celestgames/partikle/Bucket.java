package fr.celestgames.partikle;

import fr.celestgames.partikle.particles.Particle;
import fr.celestgames.partikle.particles.ParticleType;

public class Bucket {
    private final Particle[][] particles;
    private final int capacity;

    public Bucket(int capacity) {
        this.capacity = capacity;
        particles = new Particle[capacity][capacity];

        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                particles[i][j] = new Particle(i + "-" + j, ParticleType.VOID);
            }
        }
    }

    public void add(Particle particle) {
        if (particles[0][0].getType() == ParticleType.VOID) {
            particles[0][0] = particle;
        }
    }

    public void setParticle(int x, int y, Particle particle) {
        particles[y][x] = particle;
    }

    public void clearParticle(int x, int y) {
        particles[y][x] = new Particle(x + "-" + y, ParticleType.VOID);
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

    public boolean isFull() {
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                if (particles[i][j].getType() == ParticleType.VOID) {
                    return false;
                }
            }
        }
        return true;
    }
}
