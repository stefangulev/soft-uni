import entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "cards")
public class CreditCard extends BillingDetails {
    private String cardType;
    private Month expirationMonth;
    private Year expirationYear;

    public CreditCard() {

    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }
}
