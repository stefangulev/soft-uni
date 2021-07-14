function solve() {
    let elements = {};
    return {
        init: (selector1, selector2, resultSelector) => {
            elements['selector1'] = document.querySelector(selector1);
            elements['selector2'] = document.querySelector(selector2);
           elements['resultSelector'] = document.querySelector(resultSelector);
        },
        add: () => {
           let firstValue =  elements['selector1'].value;
           let secondValue = elements['selector2'].value;
           elements['resultSelector'].value = Number(firstValue) + Number(secondValue);
        },
        subtract: () => {
            let firstValue =  elements['selector1'].value;
           let secondValue = elements['selector2'].value;
           elements['resultSelector'].value = Number(firstValue) - Number(secondValue);
        }
    }
}