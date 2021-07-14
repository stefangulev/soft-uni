const isSymmetric = require('./symetry');
const {expect} = require('chai');

describe("Testing Symmetry", () => {
    it("Test Incorrect Input", () => {
        expect(isSymmetric('string')).to.equal(false);
    });
    it("Test symmetry", () => {
        expect(isSymmetric([1,1])).to.equal(true);
    });
    it("Test symmetry with multiple args", () => {
        expect(isSymmetric([1,2,2,1])).to.equal(true);
    })
    it("Test asymetry", () => {
        expect(isSymmetric([1,3,5,7])).to.equal(false);
    });
    it("Test symetry different types", () => {
        expect(isSymmetric([1, '1'])).to.equal(false);
    });
    

});