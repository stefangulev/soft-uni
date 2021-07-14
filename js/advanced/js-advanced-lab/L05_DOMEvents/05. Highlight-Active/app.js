function focus() {
    let elements = document.getElementsByTagName("input");

    for (let el of elements) {
        el.addEventListener("focus", onFocus);
        el.addEventListener("blur", onBlur);
    }

    function onFocus(event) {
        event.target.parentElement.classList.add('focused');
    }
    function onBlur(event) {
        event.target.parentElement.classList.remove('focused');
    }






}