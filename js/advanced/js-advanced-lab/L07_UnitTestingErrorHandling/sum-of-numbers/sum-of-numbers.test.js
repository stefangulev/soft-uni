const sum = require('./sum-of-numbers');
let {expect} = require('chai');

describe('Test Sum', () => {
    it("Test if correct result with valid arg", () => {
        expect(sum([1])).to.equal(1);
    });
    it("Test if correct result with two arg", () => {
        expect(sum([1,2])).to.equal(3);
    });
    it("Test if correct result with multiple arg", () => {
        expect(sum([1,2,3])).to.equal(6);
    });
    it("Test if correct result with four arg", () => {
        expect(sum([1,2,3,1])).to.equal(7);
    });

});


