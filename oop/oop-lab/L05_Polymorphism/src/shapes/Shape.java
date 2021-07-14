package shapes;


abstract class Shape {
    private Double perimeter;
    private Double area;


    public Double getArea() {
        return area;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    protected abstract void calculatePerimeter();
    protected abstract void calculateArea();

}
