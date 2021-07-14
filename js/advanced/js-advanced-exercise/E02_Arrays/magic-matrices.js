function checkMagic(matrix) {

    let sums = [];

    for (let arr of matrix) {
        let sumRow = 0;
        for (let cell of arr) {
            sumRow += cell;
        }
        sums.push(sumRow);
    }
   
    for(col =0; col< matrix.length; col++) {
        let sumCol =0;
        for (row =0; row<matrix.length; row++) {
            sumCol += matrix[row][col];
        }
        sums.push(sumCol);
    }

   return sums.every((el, i, arr) => el === arr[0]);

    
}

console.log(checkMagic([[4, 5, 6],
    [6, 5, 4],
    [5, 5, 5]]
   ));
   console.log(checkMagic([[11, 32, 45],
    [21, 0, 1],
    [21, 1, 1]]
   ));
   console.log(checkMagic([[1, 0, 0],
    [0, 0, 1],
    [0, 1, 0]]
   ));