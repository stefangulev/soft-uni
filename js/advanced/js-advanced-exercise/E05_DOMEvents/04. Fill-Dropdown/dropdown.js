function addItem() {
    const text = document.getElementById("newItemText");
    const value = document.getElementById("newItemValue");
    let selectItem = document.getElementById("menu");

    let newItem = document.createElement("option");
    newItem.textContent = text.value;
    newItem.value = value.value;
    selectItem.appendChild(newItem);
    text.value = "";
    value.value = "";

}