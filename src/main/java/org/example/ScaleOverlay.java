package org.example;

import java.awt.*;
import java.awt.event.InputEvent;
import javax.swing.*;

public class ScaleOverlay extends JFrame {
    private Robot robot;
    private int stepSize;

    public ScaleOverlay() {
        super();

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparent
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        setAlwaysOnTop(true);
        setType(Type.POPUP);
        stepSize = 100; // Change as per requirement

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int screenWidth = getSize().width;
        int screenHeight = getSize().height;
        int markerLength = 10; // Length of the scale marker

        g.setColor(Color.RED);

        // draw x scale
        for(int x = 0; x < screenWidth; x += stepSize) {
            g.drawLine(x, 0, x, markerLength);
            g.drawString(String.valueOf(x), x, markerLength + 20);
        }

        // draw y scale
        for(int y = 0; y < screenHeight; y += stepSize) {
            g.drawLine(0, y, markerLength, y);
            g.drawString(String.valueOf(y), markerLength + 10, y + 20);
        }
    }

    public void simulateClick(int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
