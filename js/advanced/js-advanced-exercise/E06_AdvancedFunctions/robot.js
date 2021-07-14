function solution() {
    const recepies = {
        apple: {
            carbohydrate: 1,
            flavour: 2,
        },
        lemonade: {
            carbohydrate: 10,
            flavour: 20,
        },
        burger: {
            carbohydrate: 5,
            fat: 7,
            flavour: 3,

        },
        eggs: {
            protein: 5,
            fat: 1,
            flavour: 1,
        },
        turkey: {
            protein: 10,
            carbohydrate: 10,
            fat: 10,
            flavour: 10,
        }
    }

    let stock = {};

    return function controller(str) {
        let tokens = str.split(" ");
        let finalResult = "";

        let commands = {
            restock: (el, quant) => {
                if (stock[el] === undefined) {
                    stock[el] = 0;
                }
                stock[el] += Number(quant);
                finalResult =  "Success";
            },
            report: () => {
                finalResult= `protein=${stock['protein'] === undefined ? 0 : stock['protein']} carbohydrate=${stock['carbohydrate'] === undefined ? 0 : stock['carbohydrate']} fat=${stock['fat'] === undefined ? 0 : stock['fat'] } flavour=${stock['flavour'] === undefined ? 0 : stock['flavour']}`;
            },
            prepare: (product, quant) => {
                let result = "Success";
                let arr = Object.entries(recepies[product]);
                for (let el of arr) {
                    let [ingr, num] = el;
                    if(stock[ingr] === undefined || stock[ingr]  < num * quant) {
                        result = `Error: not enough ${ingr} in stock`;
                        break;
                    }
                }
                if (result === "Success") {
                    arr.forEach(([ingr, num]) => stock[ingr] -= num * quant);
                }


                finalResult= result;
            } 
        }
        commands[tokens[0]](tokens[1], Number(tokens[2]));
        return finalResult;
    }

}
let manager = solution();
console.log(manager('prepare turkey 1'));
manager('restock protein 10');
manager('prepare turkey 1');
manager('restock carbohydrate 10');
manager('prepare turkey 1');
manager('restock fat 10');
manager('prepare turkey 1');
manager('restock flavour 10');
manager('prepare turkey 1');
manager('report');





