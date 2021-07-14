function stringLength(firstString, secondString, thirdString) {
let sum = firstString.length + secondString.length + thirdString.length;
let average = Math.floor(sum / 3);
console.log(sum);
console.log(average);
}

stringLength('chocolate', 'ice cream', 'cake');