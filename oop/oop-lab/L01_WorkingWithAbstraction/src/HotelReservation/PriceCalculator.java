package HotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private int numberOfDays;
    Season season;
    DiscountType discountType;

    public PriceCalculator(double pricePerDay, int numberOfDays, String season, String discountType) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = Season.valueOf(season.toUpperCase());
        this.discountType = DiscountType.valueOf(discountType.toUpperCase());

    }

    public double calculate(double pricePerDay, int numberOfDays, Season season, DiscountType discountType) {
        return  pricePerDay * numberOfDays * season.getMultiplier() * discountType.getPercentage();
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.calculate(this.pricePerDay, this.numberOfDays, this.season, this.discountType));
    }
}
