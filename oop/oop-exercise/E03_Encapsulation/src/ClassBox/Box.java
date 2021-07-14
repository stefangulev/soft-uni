package ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;


    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void setLength(double length) {
        sideValidator(length, "Length");
        this.length = length;
    }

    private void setHeight(double height) {
        sideValidator(height, "Height");
        this.height = height;
    }

    private void setWidth(double width) {
        sideValidator(width, "Width");
        this.width = width;
    }

    private void sideValidator(double side, String prefix) {
        if (side < 1) {
            throw new IllegalArgumentException(String.format("%s cannot be zero or negative.", prefix));
        }
    }

    public double calculateSurfaceArea() {
        return (2 * this.length * this.width) + (2 *this.length * this.height) + (2 * this.width * this.height);
    }

    public double calculateLateralSurfaceArea() {
        return (2 * this.length * this.height) + (2 * this.width * this.height);
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }


}
