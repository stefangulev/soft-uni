const isOddOrEven = require("./odd-even");
const {expect} = require("chai");


describe("Test string length", () => {
    it("Test invalid type", ()=>{
        expect(isOddOrEven([1])).to.equal(undefined);
    });
    it("Test invalid type", ()=>{
        expect(isOddOrEven(5)).to.equal(undefined);
    });
    it("Test odd string", () => {
        expect(isOddOrEven("o")).to.equal("odd");
    });
    it("Test even string", () => {
        expect(isOddOrEven("ev")).to.equal("even");
    });
    it("Test overloading#1", () => {
        expect(isOddOrEven("oddstri")).to.equal("odd");
    });
    it("Test overloading#2", () => {
        expect(isOddOrEven("evenst")).to.equal("even");
    })
});