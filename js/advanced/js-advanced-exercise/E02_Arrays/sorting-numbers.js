function sortNums(arr) {
  arr.sort((l,r) => l-r);
let result = [];

while(arr.length != 0) {
    result.push(arr.shift());
    result.push(arr.pop());
}
return result;
    
}
console.log(sortNums([1, 65, 3, 52, 48, 63, 31, -3, 18, 56]));