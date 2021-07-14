let {expect} = require("chai");
const rgbToHexColor = require("./rgb");

describe("Test Colors", () => {
    it("Test with invalid red type", () => {
        expect(rgbToHexColor("string", 155, 123)).to.equal(undefined);
    });
    it("Test with invalid blue type", () => {
        expect(rgbToHexColor(155, "string", 123)).to.equal(undefined);
    });
    it("Test with invalid green type", () => {
        expect(rgbToHexColor(166, 155, "string")).to.equal(undefined);
    });
    it("Test with red out of bounds", () => {
        expect(rgbToHexColor(350, 155, 123)).to.equal(undefined);
    });
    it("Test with blue out of bound", () => {
        expect(rgbToHexColor(155,350,123)).to.equal(undefined);
    });
    it("Test with green out of bound", () => {
        expect(rgbToHexColor(155,121,600)).to.equal(undefined);
    });
    it("Test with red out of bound negative", () => {
        expect(rgbToHexColor(-123,121,152)).to.equal(undefined);
    });
    it("Test with blue out of bound negative", () => {
        expect(rgbToHexColor(123,-121,125)).to.equal(undefined);
    });
    it("Test with green out of bound negative", () => {
        expect(rgbToHexColor(123,121,-125)).to.equal(undefined);
    });
    it("Test with valid parameter", () => {
        expect(rgbToHexColor(56,120,169)).to.equal("#3878A9");
    })
    it("Test with valid parameter overloading", () => {
        expect(rgbToHexColor(11,99,200)).to.equal("#0B63C8")
    })
    it("Test red with valid parameter", () => {
        expect(rgbToHexColor(255,0,0)).to.equal("#FF0000");
    })
    it("Test green with valid parameter", () => {
        expect(rgbToHexColor(0,255,0)).to.equal("#00FF00");
    })
    it("Test blue with valid parameter", () => {
        expect(rgbToHexColor(0,0,255)).to.equal("#0000FF");
    })

});
