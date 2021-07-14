function biggestNum (matrix) {
    let biggest = Number.MIN_SAFE_INTEGER;
    for(let array of matrix) {
        for(let num of array) {
            let currentNum = num;
            if (currentNum > biggest) {
                biggest = currentNum;
            }
        }
    }
    return biggest;

}

console.log(biggestNum([[3, 5, 7, 12],
    [-1, 4, 33, 2],
    [8, 3, 0, 4]]   
   ));