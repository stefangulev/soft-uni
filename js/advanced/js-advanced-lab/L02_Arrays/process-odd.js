function processOdd(arr) {
  return arr.filter((x ,i) => i % 2 != 0).reverse().map(x => x*2).join(' ');
}
console.log(processOdd([3, 0, 10, 4, 7, 3]));