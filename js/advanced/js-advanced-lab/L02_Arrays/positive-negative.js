function positiveNegativeSort(arr) {
let sortedArr = [];

for(let element of arr) {
    if (element <0) {
        sortedArr.unshift(element);
    } else {
        sortedArr.push(element);
    }
}
return sortedArr.join('\n');
}

console.log(positiveNegativeSort([7, -2, 8, 9]));