const HolidayPackage = require("./package");
const {expect} = require('chai');

describe("HolidayPackage tests", () => {
    let instance = undefined;
    let springInstance = undefined;
    beforeEach(() => {
        instance = new HolidayPackage("Sofia", "Summer");
        springInstance = new HolidayPackage("London", "Spring");
    });

    it("Test addVacationer with invalid input", () => {
        expect(() => instance.addVacationer(" ")).to.throw("Vacationer name must be a non-empty string");
        expect(() =>instance.addVacationer(0)).to.throw("Vacationer name must be a non-empty string");
        expect(() => instance.addVacationer("")).to.throw("Name must consist of first name and last name");
        expect(() => instance.addVacationer("a")).to.throw("Name must consist of first name and last name");
        expect(() => instance.addVacationer("a b c")).to.throw("Name must consist of first name and last name");
    })
    it("Test addVacationer", () => {
        expect(() => instance.addVacationer("a ")).to.not.throw("Name must consist of first name and last name");
        expect(instance.vacationers.length).to.equal(1);
        instance.addVacationer("a b");
        expect(instance.vacationers.length).to.equal(2);
    })
    it("Test showVacationers with 0 vacationers", () => {
        expect(instance.showVacationers()).to.equal('No vacationers are added yet');
    })
    it("Test showVacationers", () => {
        instance.addVacationer("F S");
        expect(instance.showVacationers()).to.equal("Vacationers:\n" + "F S");
        instance.addVacationer("E S");
        expect(instance.showVacationers()).to.equal("Vacationers:\n" + "F S\n" + "E S");
    });
    it("test insurance invalid input", () => {
        expect(() => instance.insuranceIncluded = 0).to.throw("Insurance status must be a boolean");
        expect(() => instance.insuranceIncluded = 'a').to.throw("Insurance status must be a boolean");
    })
    it("Test insuranceIncluded validation", () => {
        expect(instance.insuranceIncluded).to.be.false;
        instance.insuranceIncluded = true;
        expect(instance.insuranceIncluded).to.be.true;
    })
    it("test generate without toursirts", () => {
        expect(() => instance.generateHolidayPackage()).to.throw("There must be at least 1 vacationer added");
    })
    it("Test summer/winter generateHolidayPackage", () => {
        instance.addVacationer("a b");
        expect((instance.generateHolidayPackage())).to.equal("Holiday Package Generated\n" +
        "Destination: " + "Sofia" + "\n" +
        instance.showVacationers() + "\n" +
        "Price: " + 600);
        instance.addVacationer("c d");
        expect((instance.generateHolidayPackage())).to.equal("Holiday Package Generated\n" +
        "Destination: " + "Sofia" + "\n" +
        instance.showVacationers() + "\n" +
        "Price: " + 1000);

        instance.insuranceIncluded = true;
        expect((instance.generateHolidayPackage())).to.equal("Holiday Package Generated\n" +
        "Destination: " + "Sofia" + "\n" +
        instance.showVacationers() + "\n" +
        "Price: " + 1100);

    })
    it("Test sprint generateHolidayPackage", () => {
        springInstance.addVacationer("c d");
        expect((springInstance.generateHolidayPackage())).to.equal("Holiday Package Generated\n" +
        "Destination: " + "London" + "\n" +
        springInstance.showVacationers() + "\n" +
        "Price: " + 400);
        springInstance.addVacationer("e d");
        expect((springInstance.generateHolidayPackage())).to.equal("Holiday Package Generated\n" +
        "Destination: " + "London" + "\n" +
        springInstance.showVacationers() + "\n" +
        "Price: " + 800);
        springInstance.insuranceIncluded = true;
        expect((springInstance.generateHolidayPackage())).to.equal("Holiday Package Generated\n" +
        "Destination: " + "London" + "\n" +
        springInstance.showVacationers() + "\n" +
        "Price: " + 900);
    })
    
    
})