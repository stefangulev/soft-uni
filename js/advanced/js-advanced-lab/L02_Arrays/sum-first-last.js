function sumFirstLast(arr) {
    let firstNum = Number(arr.shift());
    let secondNum = Number(arr.pop());

    return firstNum + secondNum;
}

console.log(sumFirstLast(['20', '30', '40']));