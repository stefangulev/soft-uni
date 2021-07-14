package HotelReservation;

public enum DiscountType {
    VIP(0.8),
    SECONDVISIT(0.9),
    NONE(1.0);

    private double percentage;

    DiscountType(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }
}
