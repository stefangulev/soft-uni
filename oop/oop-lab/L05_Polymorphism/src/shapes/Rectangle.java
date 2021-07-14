package shapes;

import java.io.Reader;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        setHeight(height);
        setWidth(width);
    }

    public Double getHeight() {
        return this.height;
    }

    public Double getWidth() {
        return this.width;
    }

    private void setWidth(Double width) {
        this.width = width;
    }

    private void setHeight(Double height) {
        this.height = height;
    }

    @Override
    protected void calculatePerimeter() {
        super.setPerimeter(2 * (this.getHeight() + this.getWidth()));
    }

    @Override
    protected void calculateArea() {
        super.setArea(this.getHeight() * this.getWidth());
    }
}
