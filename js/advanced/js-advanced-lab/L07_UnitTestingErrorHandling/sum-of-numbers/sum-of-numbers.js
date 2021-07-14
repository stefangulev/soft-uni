function sum(arr) {
    let sum = 0;
    for (num of arr) {
        sum += Number(num);
    }
        
    return sum;
}
console.log(sum([1,2,3]));

module.exports = sum;
