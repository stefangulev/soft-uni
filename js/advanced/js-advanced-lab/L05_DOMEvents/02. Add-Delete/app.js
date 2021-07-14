function addItem() {
    let list = document.getElementById("items");
    let text = document.getElementById("newText");
    let newEntry = document.createElement('li');
    newEntry.textContent = text.value;
    list.appendChild(newEntry);

    let deleteOption = document.createElement('a');
    deleteOption.textContent = '[Delete]'
    deleteOption.href = "#";
    deleteOption.addEventListener("click", onClick);

    function onClick(ev) {
        let parentElement = ev.target.parentElement;
        list.removeChild(parentElement);
    }

    newEntry.appendChild(deleteOption);
    text.value = "";

   

    // function createElement(type, content) {
    //     let element = document.createElement(type);
    //     element.textContent = content;
    //     return element;
    // }

}