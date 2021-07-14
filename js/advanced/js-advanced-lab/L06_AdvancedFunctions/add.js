function solution(num) {
    number = num;
    return function add(newNum) {
        return number + newNum;
    }
}
let add5 = solution(5);
console.log(add5(2));
console.log(add5(3));



