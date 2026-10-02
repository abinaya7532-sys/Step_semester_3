abstract class Shape {
    static int count = 1;
    final String shapeId;
    Shape() {
        shapeId = "S" + count++;
    }
    abstract double calculateArea();
    void scale(double factor) {
        System.out.println("Scaled by " + factor);
    }
    void scale(double xFactor, double yFactor) {
        System.out.println("Scaled by " + xFactor + " and " + yFactor);
    }
    String getShapeId() {
        return shapeId;
    }
}
class CircleShape extends Shape {
    double radius;
    CircleShape(double radius) {
        this.radius = radius;
    }
    double calculateArea() {
        return Math.PI * radius * radius;
    }
    void scale(double factor) {
        radius *= factor;
    }
    void scale(double xFactor, double yFactor) {
        radius *= xFactor;
    }
}
class SquareShape extends Shape {
    double side;

    SquareShape(double side) {
        this.side = side;
    }
    double calculateArea() {
        return side * side;
    }
    void scale(double factor) {
        side *= factor;
    }
    void scale(double xFactor, double yFactor) {
        side *= xFactor;
    }
}
public class DrawingCanvas {
    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }
    public static void main(String[] args) {
        CircleShape c = new CircleShape(5);
        SquareShape sq = new SquareShape(4);
        System.out.println(c.calculateArea());
        System.out.println(sq.calculateArea());
        sq.scale(2.0);
        System.out.println(sq.calculateArea());
        printArea(c);
    }
}