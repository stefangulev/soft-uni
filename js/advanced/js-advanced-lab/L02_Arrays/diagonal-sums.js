function diagonal(matrix) {
    let firstSum = 0;
    let secondSum = 0;
    for(i = 0; i < matrix.length; i++) {
       firstSum += matrix[i][i];
       secondSum += matrix[i][matrix.length - 1 - i];
    }

     return `${firstSum} ${secondSum}`;
}

console.log(diagonal([[20, 40],
    [10, 60]]
   ));
   console.log(diagonal([[3, 5, 17],
    [-1, 7, 14],
    [1, -8, 89]]   
   ));