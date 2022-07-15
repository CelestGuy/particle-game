package fr.celestgames.partikle.particles;

import java.awt.*;

public enum ParticleType {
    GAS,
    FLUID,
    PLASMA,
    POWDER,
    SOLID;

    public Color getColor() {
        return switch (this) {
            case GAS -> Color.CYAN;
            case FLUID -> Color.BLUE;
            case POWDER -> Color.YELLOW;
            case PLASMA -> Color.ORANGE;
            case SOLID -> Color.GRAY;
        };
    }
}
