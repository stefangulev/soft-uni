function solve(firstNumber, secondNumber) {
   while(secondNumber != 0) {
       let temp = secondNumber;
       secondNumber = firstNumber % secondNumber;
       firstNumber = temp;
   }
   return firstNumber;
}
console.log(solve(2154, 458));