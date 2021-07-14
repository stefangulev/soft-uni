function sortNames(arr) {
   return arr.sort((a,b) => a.localeCompare(b)).map((el, i) => `${(i+1)}.${el}`).join('\n');
}
console.log(sortNames(["John", "Bob", "Christina", "Ema"]))