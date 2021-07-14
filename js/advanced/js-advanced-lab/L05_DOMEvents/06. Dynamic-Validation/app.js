function validate() {
    let inputArea = document.getElementById("email");
    inputArea.addEventListener("change", onChange);

    let regex = /[a-z]+@[a-z]+\.[a-z]+/


    function onChange(event) {
        if (!regex.test(inputArea.value)) {
            inputArea.classList.add('error');
        } else {
            inputArea.classList.remove('error');
        }
    }


}