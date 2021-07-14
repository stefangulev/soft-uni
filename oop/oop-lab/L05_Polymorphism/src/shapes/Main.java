package shapes;

public class Main {

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(2D ,4D);
        Shape circle = new Circle(5D);

        rectangle.calculateArea();
        rectangle.calculatePerimeter();
        System.out.println(rectangle.getPerimeter());
        System.out.println(rectangle.getArea());

        circle.calculateArea();
        circle.calculatePerimeter();
        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());

    }
}
