function sumAllNumbers(firstInput, secondInput) {
    let firstNumber = Number(firstInput);
    let secondNumber = Number(secondInput);

    let sum = 0;
    for (let index = firstNumber; index <= secondNumber; index++) {
        sum+= index;
    }

    return sum;
}

let result = sumAllNumbers('-8', '20');
console.log(result);