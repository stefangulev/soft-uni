function solve(number) {
   let sumOfNumbers = 0;
    let stringValue = String(number);
    let isTheSame = true;
    for(index = 0; index < stringValue.length - 1; index++) {
        if (stringValue[index] != stringValue[index + 1]) {
            isTheSame = false;
        }
        sumOfNumbers+= Number(stringValue[index]);
    }
    sumOfNumbers+= Number(stringValue[stringValue.length - 1]);
    return `${isTheSame}\n${sumOfNumbers}`

}

console.log(solve(2222222));

