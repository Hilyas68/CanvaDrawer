package com.citi;

import java.util.Scanner;

public class Main {

    private static final String CANVAS_REQUIRED = "No canvas created, please create one";
    private static final String UNKNOWN_COMMAND = "Unknown command";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CanvasDrawer canvas = null;

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            canvas = executeCommands(canvas, command);
        }
    }

    public static CanvasDrawer executeCommands(CanvasDrawer canvas, String command) {
        String[] args = command.split("\\s+");
        try {
            switch (args[0]) {
                case "C":
                    int w = Integer.parseInt(args[1]);
                    int h = Integer.parseInt(args[2]);
                    canvas = new CanvasDrawer(w, h);
                    canvas.printCanvas();
                    break;
                case "L":
                    int x1 = Integer.parseInt(args[1]);
                    int y1 = Integer.parseInt(args[2]);
                    int x2 = Integer.parseInt(args[3]);
                    int y2 = Integer.parseInt(args[4]);
                    if (canvas == null) {
                        throw new IllegalStateException(CANVAS_REQUIRED);
                    }
                    canvas.drawLine(x1, y1, x2, y2);
                    canvas.printCanvas();
                    break;
                case "R":
                    int rx1 = Integer.parseInt(args[1]);
                    int ry1 = Integer.parseInt(args[2]);
                    int rx2 = Integer.parseInt(args[3]);
                    int ry2 = Integer.parseInt(args[4]);
                    if (canvas == null) {
                        throw new IllegalStateException(CANVAS_REQUIRED);
                    }
                    canvas.drawRectangle(rx1, ry1, rx2, ry2);
                    canvas.printCanvas();
                    break;
                case "Q":
                    System.exit(0);
                default:
                    throw new IllegalArgumentException(UNKNOWN_COMMAND);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return canvas;
    }
}
