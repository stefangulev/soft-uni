const PaymentPackage = require('./pack');
const {expect} = require('chai');

describe("PaymentPackage tests", () => {
    let instance = undefined;
    beforeEach(() => {
        instance = new PaymentPackage("levski", 1);
    });
    it("constructor", () => {
        expect(instance._name).to.equal("levski");
        expect(instance._value).to.equal(1);
        expect(instance._VAT).to.equal(20);
        expect(instance.active).to.equal(true);
    });
    it("get name", () => {
        expect(instance.name).to.equal("levski");
    })
    it('set name', () => {
        expect(() => {instance.name = 1}).to.throw('Name must be a non-empty string');
        expect(() => {instance.name = ''}).to.throw('Name must be a non-empty string');
        instance.name = "Ivan";
        expect(instance.name).to.equal("Ivan");
    });
    it("get value", () => {
        expect(instance.value).to.equal(1);
    })
    it("set value", () => {
        expect(() => {instance.value = "str"}).to.throw('Value must be a non-negative number');
        expect(() => {instance.value = -1}).to.throw('Value must be a non-negative number');
        instance.value = 0;
        expect(instance.value).to.equal(0);
    });
    it("get VAT", () => {
        expect(instance.VAT).to.equal(20);
    })
    it("set VAT", () => {
        expect(() => instance.VAT = "str").to.throw('VAT must be a non-negative number');
        expect(() => instance.VAT = -1).to.throw('VAT must be a non-negative number');
        instance.VAT = 1;
        expect(instance.VAT).to.equal(1);
    });
    it("get active", () => {
        expect(instance.active).to.equal(true);
    })
    it("set active", () => {
        expect(() => instance.active = "str").to.throw('Active status must be a boolean');
        expect(() => instance.active = 1).to.throw('Active status must be a boolean');
        instance.active = false;
        expect(instance.active).to.equal(false);
    });
    it("toString active" , () => {
        const output = [
            `Package: ${instance.name}`,
            `- Value (excl. VAT): ${instance.value}`,
            `- Value (VAT ${instance.VAT}%): ${instance.value * (1 + instance.VAT / 100)}`
          ];
          let result =  output.join('\n');
          expect(instance.toString()).to.equal(result);
    });
    it("toString inactive", () => {
        instance.active = false;
        const output = [
            `Package: ${instance.name}` + ' (inactive)',
            `- Value (excl. VAT): ${instance.value}`,
            `- Value (VAT ${instance.VAT}%): ${instance.value * (1 + instance.VAT / 100)}`
          ];
          let result =  output.join('\n');
          expect(instance.toString()).to.equal(result);
    })

});