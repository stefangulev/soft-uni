function calculate(arr) {

    let sum = 0;
    let inverseSum = 0;
    let concatResult = "";
    for (let index = 0; index < arr.length; index++) {
        sum += arr[index];
        inverseSum += 1/arr[index];
        concatResult = concatResult.concat(String(arr[index]));
    }

    console.log(sum);
    console.log(inverseSum);
    console.log(concatResult);
}

calculate([2, 4, 8, 16]);