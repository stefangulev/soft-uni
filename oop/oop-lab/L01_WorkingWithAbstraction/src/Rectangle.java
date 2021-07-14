public class Rectangle {
    Point bottomCorner;
    Point topCorner;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.bottomCorner = new Point(x1, y1);
        this.topCorner = new Point(x2, y2);
    }

    public boolean contains (Point point) {
        return point.getX() >= this.bottomCorner.getX() && point.getX() <= this.topCorner.getX() &&
                point.getY() >= this.bottomCorner.getY() && point.getY() <= topCorner.getY();

    }
}
