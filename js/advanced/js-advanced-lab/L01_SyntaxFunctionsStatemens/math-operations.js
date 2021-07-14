function calculator(firstNumber, secondNumber, symbol) {
    let result;
    if (symbol == '+') {
        result = firstNumber + secondNumber;
    } else if (symbol == '-') {
        result = firstNumber - secondNumber;
    } else if (symbol == '*') {
        result = firstNumber * secondNumber;
    } else if (symbol == '/') {
        result = firstNumber / secondNumber;
    } else if (symbol == '%') {
        result = firstNumber % secondNumber;
    } else if (symbol == '**') {
        result = firstNumber ** secondNumber;
    }
    console.log(result);
}

calculator(2, 2, '**');