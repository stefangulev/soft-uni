function findNeighbours(matrix) {
let pairs = 0;

for(i =0; i < matrix.length; i++) {
    for(j = 0; j < matrix[i].length; j++) {

        let current = matrix[i][j];

        if (i < matrix.length - 1 && current == matrix[i + 1][j]) {
            pairs++;
        }
        if (current == matrix[i][j + 1]) {
            pairs++;
        }
    }

}

return pairs;
}

console.log(findNeighbours(
[['2', '3', '4', '7', '0'],
['4', '0', '5', '3', '4'],
['2', '3', '5', '4', '2'],
['9', '8', '7', '5', '4']]
));
console.log(findNeighbours(
[['test', 'yes', 'yo', 'ho'],
['well', 'done', 'yo', '6'],
['not', 'done', 'yet', '5']]
));