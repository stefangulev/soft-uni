const pizzUni = require('./pizza');
const {expect} = require('chai');

describe("Test pizza", () => {
    let fullOrder = undefined;
    let noPizza = undefined;
    let noDrink = undefined
    let mixedWork = undefined;
    let allDone = undefined
    beforeEach(() => {
        fullOrder = {
            orderedPizza: "Polo",
            orderedDrink: "Cola"
        }
        noPizza = {
            orderedDrink: "Cola"
        }
        noDrink = {
            orderedPizza: "Polo"
        }
        mixedWork = [{pizzaName: 'Polo', status: "ready"}, {pizzaName: 'Bianca', status: "preparing"}];
        allDone = [{pizzaName: 'Polo', status: "ready"}]
    });
    it("Test no pizza order", () => {
        expect(() => pizzUni.makeAnOrder(noPizza)).to.throw('You must order at least 1 Pizza to finish the order.');
    });
    it("Test only pizza order", () => {
        expect(pizzUni.makeAnOrder(noDrink)).to.equal(`You just ordered ${noDrink.orderedPizza}`);
    })
    it("Test only full order", () => {
        expect(pizzUni.makeAnOrder(fullOrder)).to.equal(`You just ordered ${fullOrder.orderedPizza} and ${fullOrder.orderedDrink}.`)
    })
    it("Test mixed remaining work", () => {
        expect(pizzUni.getRemainingWork(mixedWork)).to.equal(`The following pizzas are still preparing: Bianca.`);
    })
    it("Test all done", () => {
        expect(pizzUni.getRemainingWork(allDone)).to.equal('All orders are complete!');
    })
    it("Test carry out order", () => {
        expect(pizzUni.orderType(1, 'Carry Out')).to.equal(0.9);
    });
    it("Test delivery", () => {
        expect(pizzUni.orderType(1, 'Delivery')).to.equal(1);
    })
})