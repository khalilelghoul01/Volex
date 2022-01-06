package Volex;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {

    private static MouseListener instance;
    private double scrollX, scrollY;
    private double posX, posY, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        scrollX = scrollY = 0.0;
        posX = posY = lastX = lastY = 0.0;
        isDragging = false;
    }

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
    }

    public static void cursorPosCallback(long window, double x, double y) {
        get().lastX = get().posX;
        get().lastY = get().posY;
        get().posX = x;
        get().posY = y;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if(button < get().mouseButtonPressed.length)
                get().mouseButtonPressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            get().mouseButtonPressed[button] = false;
            get().isDragging = false;
        }
    }

    public static void mouseScrollCallback(long window, double x, double y) {
        get().scrollX = x;
        get().scrollY = y;
    }

    public static void  endFrame() {
        get().scrollX = get().scrollY = 0.0;
        get().lastX = get().posX;
        get().lastY = get().posY;
    }

    //getters

    public static float getX() {
        return (float) get().posX;
    }

    public static float getY() {
        return (float) get().posY;
    }

    public static float getLastX() {
        return (float) get().lastX;
    }

    public static float getLastY() {
        return (float) get().lastY;
    }

    public static float getDx() {
        return (float) (get().posX - get().lastX);
    }

    public static float getDy() {
        return (float) (get().posY - get().lastY);
    }

    public static float getScrollX() {
        return (float) get().scrollX;
    }

    public static float getScrollY() {
        return (float) get().scrollY;
    }

    public static boolean isButtonDown(int button) {
        if (button < get().mouseButtonPressed.length)
            return get().mouseButtonPressed[button];
        return false;
    }



    public static boolean isDragging() {
        return get().isDragging;
    }


}

