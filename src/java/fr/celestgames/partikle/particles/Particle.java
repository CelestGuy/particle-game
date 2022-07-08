package fr.celestgames.partikle.particles;

public class Particle {
    private final String id;
    private ParticleType type;
    private int direction;
    private boolean falling;

    public Particle(String id, ParticleType type) {
        this.id = id;
        this.type = type;
        this.direction = 1;
    }

    public String getId() {
        return id;
    }

    public ParticleType getType() {
        return type;
    }

    public void setType(ParticleType type) {
        this.type = type;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
}
