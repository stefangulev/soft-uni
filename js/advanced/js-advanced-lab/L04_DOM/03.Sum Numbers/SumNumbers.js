function calc() {
    let firstNumber = document.querySelector("#num1");
    let secondNumber = document.querySelector("#num2");
    let resultField = document.querySelector("#sum");
    resultField.value = Number(firstNumber.value) + Number(secondNumber.value);
}
