package shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        setRadius(radius);
    }

    public final Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    protected void calculatePerimeter() {
        super.setPerimeter(2 * Math.PI * getRadius());
    }

    @Override
    protected void calculateArea() {
        super.setArea(Math.PI * (this.getRadius() * this.getRadius()));
    }
}
