function addItem() {
    let list = document.getElementById("items");
    let newEntry = document.createElement("li");
    newEntry.textContent = document.getElementById("newItemText")
    .value;
    list.appendChild(newEntry);
}