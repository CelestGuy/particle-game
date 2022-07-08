package fr.celestgames.partikle.particles;

import java.awt.*;

public enum ParticleType {
    VOID,
    WATER,
    SAND;

    public Color getColor() {
        return switch (this) {
            case VOID -> Color.BLACK;
            case WATER -> Color.BLUE;
            case SAND -> Color.orange;
        };
    }
}
