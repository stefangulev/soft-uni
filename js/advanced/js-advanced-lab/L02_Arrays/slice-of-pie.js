function findFlavour(arr, firstFlavour, secondFlavour) {
    let result = arr.slice(arr.indexOf(firstFlavour), arr.indexOf(secondFlavour) + 1);
    return result;
}
console.log(findFlavour(['Pumpkin Pie',
'Key Lime Pie',
'Cherry Pie',
'Lemon Meringue Pie',
'Sugar Cream Pie'],
'Key Lime Pie',
'Lemon Meringue Pie'
));