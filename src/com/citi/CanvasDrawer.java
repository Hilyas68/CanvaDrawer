package com.citi;

public class CanvasDrawer {

    private static final String INVALID_COORDINATES = "Invalid co-ordinates";
    private static final String INVALID_CANVAS_SIZE = "Invalid canvas size";
    private static final String UNSUPPORTED_LINE_TYPE = "Only horizontal or vertical lines are supported";

    private char[][] canvas;

    // The width and height of the canvas
    private int width;
    private int height;

    // The default character for empty pixels
    private static final char EMPTY = ' ';

    private static final char DRAW = 'x';

    public CanvasDrawer(int width, int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(INVALID_CANVAS_SIZE);
        }

        this.width = width;
        this.height = height;
        this.canvas = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = EMPTY;
            }
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        // Assume we can't have a negative coordinates and it within the width x height
        if (x1 < 1 || x1 > width || x2 < 1 || x2 > width || y1 < 1 || y1 > height || y2 < 1 || y2 > height) {
            throw new IllegalArgumentException(INVALID_COORDINATES);
        }
        // Check if the line is horizontal or vertical
        if (x1 == x2) {
            // Vertical line
            // Swap y1 and y2 if y1 is larger than y2
            if (y1 > y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }
            // Loop from y1 to y2 and fill the canvas with the draw character
            for (int i = y1 - 1; i < y2; i++) {
                canvas[i][x1 - 1] = DRAW;
            }
        } else if (y1 == y2) {
            // Horizontal line
            // Swap x1 and x2 if x1 is larger than x2
            if (x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            // Loop from x1 to x2 and fill the canvas with the draw character
            for (int j = x1 - 1; j < x2; j++) {
                canvas[y1 - 1][j] = DRAW;
            }
        } else {
            // Neither horizontal nor vertical line
            throw new IllegalArgumentException(UNSUPPORTED_LINE_TYPE);
        }
    }

    public void drawRectangle(int x1, int y1, int x2, int y2) {
        // Check if the coordinates are valid and within the canvas bounds
        if (x1 < 1 || x1 > width || x2 < 1 || x2 > width || y1 < 1 || y1 > height || y2 < 1 || y2 > height) {
            throw new IllegalArgumentException(INVALID_COORDINATES);
        }
        // Draw the four sides of the rectangle using the drawLine method
        drawLine(x1, y1, x2, y1); // Top side
        drawLine(x1, y2, x2, y2); // Bottom side
        drawLine(x1, y1, x1, y2); // Left side
        drawLine(x2, y1, x2, y2); // Right side
    }

    public void printCanvas() {
        // Print the top border of the canvas
        System.out.print("-");
        for (int j = 0; j < width; j++) {
            System.out.print("-");
        }
        System.out.println("-");
        // Print the pixels of the canvas with a border on each side
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                System.out.print(canvas[i][j]);
            }
            System.out.println("|");
        }
        // Print the bottom border of the canvas
        System.out.print("-");
        for (int j = 0; j < width; j++) {
            System.out.print("-");
        }
        System.out.println("-");
    }
}
