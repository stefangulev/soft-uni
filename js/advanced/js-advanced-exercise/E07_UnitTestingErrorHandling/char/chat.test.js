const lookupChar = require("./char");
const {expect} = require("chai");

describe("Testing chars", () => {
    it("non string input", () =>{
        expect(lookupChar(1, 4)).to.equal(undefined);
    });
    it("non num input", () => {
        expect(lookupChar("string", [1])).to.equal(undefined);
    });
    it("non integer input", () => {
        expect(lookupChar("string", 2.45)).to.equal(undefined);
    });
    it("test negative index", () => {
        expect(lookupChar("string", -3)).to.equal("Incorrect index");
    });
    it("test too big index", () => {
        expect(lookupChar("string", 12)).to.equal("Incorrect index");
    });
    it("test empty string", () => {
        expect(lookupChar("", 0)).to.equal("Incorrect index");
    });
    it("test with valid input", () => {
        expect(lookupChar("str", 0)).to.equal("s");
    });
    it("test overloading #1", () => {
        expect(lookupChar("stringify", 4)).to.equal("n");
    });
});