const mathEnforcer = require("./math");
const {expect} = require("chai");



describe("Test Math", () => {
    describe("AddFive", () => {
        it("Test invalid input type", () => {
            expect(mathEnforcer.addFive("str")).to.be.undefined;
        });
        it("Test valid input integers", () => {
            expect(mathEnforcer.addFive(1)).to.equal(6);
        });
        it("Test valid input negative int", () => {
            expect(mathEnforcer.addFive(-1)).to.equal(4);
        });
        it("Test valid input float", () => {
            expect(mathEnforcer.addFive(1.5)).to.be.closeTo(6.5, 0.01);
        });
    });
    describe("SubtractTen", () => {
        it("Test invalid input type", () => {
            expect(mathEnforcer.subtractTen("string")).to.be.undefined;
        });
        it("Test valid input integer", () => {
            expect(mathEnforcer.subtractTen(10)).to.equal(0);
        });
        it("Test valid input negative", () => {
            expect(mathEnforcer.subtractTen(-1)).to.equal(-11);
        });
        it("Test valid input float", () => {
            expect(mathEnforcer.subtractTen(1.5)).to.be.closeTo(-8.5, 0.01);
        });
    });
    describe("Sum", () => {
        it("test with invalid first number", () => {
            expect(mathEnforcer.sum("str", 1)).to.be.undefined;
        });
        it("test with invalid second number", () => {
            expect(mathEnforcer.sum(1,"str")).to.be.undefined;
        });
        it("test with valid input integers", () => {
            expect(mathEnforcer.sum(1,1)).to.equal(2);
        });
        it("test with valid input negative", () => {
            expect(mathEnforcer.sum(-1,-1)).to.equal(-2);
        });
        it("test with valid input float", () => {
            expect(mathEnforcer.sum(1.1,1.1)).to.be.closeTo(2.2, 0.01);
        });
    } );
});