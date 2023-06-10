package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class ScaleOverlay extends JWindow {
    private Robot robot;
    private int stepSize;

    public ScaleOverlay() {
        super();

        setBackground(new Color(0, 0, 0, 0)); // Transparent
        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        setAlwaysOnTop(true);
        stepSize = 25; // Change as per requirement

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int screenWidth = getSize().width;
        int screenHeight = getSize().height;
        int markerLength = 10; // Length of the scale marker
        int textOffsetX = 30; // Offset of the text from the scale marker
        int textOffsetY = 20; // Offset of the text from the scale marker
        int secondRowOffset = 15; // Offset for the second row of numbers
        int textMargin = 5; // Margin between numbers on y-axis

        g.setColor(Color.RED); // For the lines

        // draw x scale
        for(int x = 0; x < screenWidth; x += stepSize) {
            // Top scale
            g.drawLine(x, 0, x, markerLength);
            // Bottom scale
            g.drawLine(x, screenHeight, x, screenHeight - markerLength);
            if (x % stepSize == 0) {
                g.setColor(Color.YELLOW); // For the text
                g.drawString(String.valueOf(x), x, textOffsetX + (x % (2*stepSize) == 0 ? 0 : secondRowOffset));
                g.drawString(String.valueOf(x), x, screenHeight - textOffsetX + (x % (2*stepSize) == 0 ? 0 : -secondRowOffset));
                g.setColor(Color.RED); // Change it back to red for the lines
            }
        }

        // draw y scale
        for(int y = 0; y < screenHeight; y += stepSize) {
            // Left scale
            g.drawLine(0, y, markerLength, y);
            // Right scale
            g.drawLine(screenWidth, y, screenWidth - markerLength, y);
            if (y % stepSize == 0) {
                g.setColor(Color.YELLOW); // For the text
                g.drawString(String.valueOf(y), textOffsetY, y + textMargin);
                g.drawString(String.valueOf(y), screenWidth - textOffsetY, y + textMargin);
                g.setColor(Color.RED); // Change it back to red for the lines
            }
        }
    }

    public void simulateClick(int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
