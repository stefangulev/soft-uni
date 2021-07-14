function validate() {
    const textField = document.getElementById("email");
    textField.addEventListener("change", onBlur);

    function onBlur(ev) {
        if(!/[a-z]+@[a-z]+\.[a-z]+/.test(textField.value)) {
            textField.classList.add("error");
        } else {
            textField.classList.remove("error");  
            
            
        }
    }
}