const numberOperations = require('./03. Number Operations_Resources');
const {expect} = require('chai');

describe('Test num', () => {
    it('test pow', () => {
        expect(numberOperations.powNumber(2)).to.equal(4);
        expect(numberOperations.powNumber(1)).to.equal(1);
    })
    it('test numberChecker nan', () => {
        expect(() => numberOperations.numberChecker('str')).to.throw('The input is not a number!');
    })
    it('test numberChecker lower', () => {
        expect(numberOperations.numberChecker(1)).to.equal('The number is lower than 100!');
    })
    it('test numberChecker equal', () => {
        expect(numberOperations.numberChecker(100)).to.equal('The number is greater or equal to 100!');
    })
    it('test numberChecker high', () => {
        expect(numberOperations.numberChecker(101)).to.equal('The number is greater or equal to 100!');
    })
    it('test equal arr', () => {
        expect(numberOperations.sumArrays([1], [1])).to.deep.equal([2]);
    })
    it('test one empty', () => {
        expect(numberOperations.sumArrays([1], [])).to.deep.equal([1]);
    })
})

