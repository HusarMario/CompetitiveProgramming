package OnlineJudge.p12684;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Point[] points;
    static int numberOfPoints;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line;
        boolean firstLine = true;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                if (!firstLine) {
                    color(1);

                    for (int i = 1; i <= numberOfPoints; i++) {
                        System.out.println(points[i].number + " " + points[i].color);
                    }

                    firstLine = true;
                }

                continue;
            }

            if (firstLine) {
                numberOfPoints = Integer.parseInt(line);
                points = new Point[numberOfPoints + 1];

                for (int i = 1; i <= numberOfPoints; i++) {
                    points[i] = new Point(i);
                }
                firstLine = false;
            } else {
                String[] input = line.split("-");
                int point1 = Integer.parseInt(input[0]);
                int point2 = Integer.parseInt(input[1]);
                points[point1].points.add(points[point2]);
                points[point2].points.add(points[point1]);
            }
        }

        if (!firstLine) {
            color(1);

            for (int i = 1; i <= numberOfPoints; i++) {
                System.out.println(points[i].number + " " + points[i].color);
            }
        }
    }

    static boolean color(int point) {
        if (point > numberOfPoints) {
            return true;
        }

        for (int color = 1; color <= 4; color++) {
            if (canColor(points[point], color)) {
                points[point].color = color;

                if (color(point + 1)) {
                    return true;
                }

                points[point].color = -1;
            }
        }

        return false;
    }

    static boolean canColor(Point point, int color) {
        for (Point otherPoint : point.points) {
            if (otherPoint.color == color) {
                return false;
            }
        }

        return true;
    }

    static class Point {
        ArrayList<Point> points = new ArrayList<>();
        int number;
        int color = -1;

        Point(int number) {
            this.number = number;
        }
    }
}
