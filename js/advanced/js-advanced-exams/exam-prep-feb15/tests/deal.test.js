const dealership = require("./deal");
const {expect} = require('chai');


describe("Test dealership obj", () => {
    it("Test newCarCost with valid input", () => {
        expect(dealership.newCarCost('Audi A4 B8', 20000)).to.equal(5000);
    });
    it("Test newCarCost with invalid input", () => {
        expect(dealership.newCarCost('non-existent car', 20000)).to.equal(20000);
    });
    it("Test carEquipment with one el", () => {
        expect(dealership.carEquipment(['leather'], [0])).to.have.members(['leather']);
    })
    it("Test carEquipment with more el", () => {
        expect(dealership.carEquipment(['leather', 'seats', 'wheel'], [0,2])).to.have.members(['leather', 'wheel']);
    })
    it("Test low euro category", () => {
        expect(dealership.euroCategory(0)).to.equal('Your euro category is low, so there is no discount from the final price!');
    })
    it("Test high euro category", () => {
        expect(dealership.euroCategory(4)).to.equal('We have added 5% discount to the final price: 14250.')
    })
})