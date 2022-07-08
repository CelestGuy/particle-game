package fr.celestgames.partikle.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Keyboard implements KeyListener {
    private static Keyboard instance;
    private final boolean[] keys;

    private Keyboard() {
        keys = new boolean[256];
        Arrays.fill(keys, false);
    }

    public static Keyboard getInstance() {
        if (instance == null) {
            instance = new Keyboard();
        }
        return instance;
    }

    public boolean isKeyDown(int key) {
        return keys[key];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
        }
    }
}
