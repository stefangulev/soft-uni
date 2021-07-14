import entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
public class Gringotts extends BaseEntity {

    private String firstName;
    private String lastName;
    private String notes;
    private int age;
    private String magicWandCreator;
    private short magicWandSize;
    private String depositGroup;
   private LocalDateTime depositStartDate;
    private double depositAmount;
    private double depositInterest;
    private double depositCharge;
    private LocalDateTime depositExpirationDate;
    boolean isDepositExpired;

    public Gringotts() {
    }


    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return firstName;
    }
    @Column(name = "last_name", length = 60, nullable = false)
    public String getLastName() {
        return lastName;
    }
    @Column(columnDefinition = "TEXT(1000)")
    public String getNotes() {
        return notes;
    }
    @Column(nullable = false)
    public int getAge() {
        return age;
    }
    @Column(name = "magic_wand_creator", length = 100)
    public String getMagicWandCreator() {
        return magicWandCreator;
    }
    @Column(name = "magic_wand_size")
    public short getMagicWandSize() {
        return magicWandSize;
    }
    @Column(name="deposit_group", length = 20)
    public String getDepositGroup() {
        return depositGroup;
    }
    @Column(name ="deposit_start_date")
    public LocalDateTime getDepositStartDate() {
        return depositStartDate;
    }
    @Column(name ="deposit_amount")
    public double getDepositAmount() {
        return depositAmount;
    }
    @Column(name ="deposit_interest")
    public double getDepositInterest() {
        return depositInterest;
    }
    @Column(name ="deposit_charge")
    public double getDepositCharge() {
        return depositCharge;
    }
    @Column(name ="deposit_expiration_date")
    public LocalDateTime getDepositExpirationDate() {
        return depositExpirationDate;
    }
    @Column(name="is_deposit_expired")
    public boolean isDepositExpired() {
        return isDepositExpired;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public void setMagicWandSize(short magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public void setDepositStartDate(LocalDateTime depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setDepositInterest(double depositInterest) {
        this.depositInterest = depositInterest;
    }

    public void setDepositCharge(double depositCharge) {
        this.depositCharge = depositCharge;
    }

    public void setDepositExpirationDate(LocalDateTime depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    public void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }
}
