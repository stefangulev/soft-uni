function solve() {
    let onScreenButton = document.getElementsByTagName("button")[0];
    onScreenButton.addEventListener("click", onScreen);
    let div = document.getElementById("container");
    let moviesSection = document.getElementById("movies").querySelector('ul');
    moviesSection.addEventListener("click", archive);
    let archiveSection = document.getElementById("archive");
    let archivesList = archiveSection.querySelector('ul');
    archiveSection.addEventListener('click', onClick);

    function onClick(ev) {
        ev.preventDefault();
        if (ev.target.nodeName === "BUTTON") {
            if (ev.target.textContent === "Delete") {
                let li = ev.target.parentNode;
                archivesList.removeChild(li);
            } else if (ev.target.textContent === "Clear") {
                let liElementsInList = Array.from(archivesList.children).forEach(x => archivesList.removeChild(x));
            }
        }
    }

    function archive(ev) {
        ev.preventDefault();
        if (ev.target.nodeName === "BUTTON") {
            let li = ev.target.parentNode.parentNode;
            let textAreaValue = li.querySelector("input").value;
            let soloPrice = li.querySelector('div').querySelector('strong').textContent;
            let movieName = li.querySelector('span');
            if (textAreaValue != "" && !isNaN(Number(textAreaValue))) {
                let newPrice = soloPrice * textAreaValue;
                let newLi = document.createElement('li');
                newLi.appendChild(movieName);
                let totalPrice = document.createElement('strong');
                totalPrice.textContent = `Total amount: ${newPrice.toFixed(2)}`;
                newLi.appendChild(totalPrice);
                let deleteBtn = document.createElement('button');
                deleteBtn.textContent = "Delete";
                newLi.appendChild(deleteBtn);
                moviesSection.removeChild(li);
                archivesList.appendChild(newLi);


            }
            
        }
    }

    function onScreen(ev) {
        ev.preventDefault();
        let [name, hall, price] = Array.from(div.querySelectorAll("input[type=text]"));
        if (name.value != "" && hall.value != "" && price.value != "" && !isNaN(Number(price.value))) {
            let newElement = document.createElement('li');
            let span = document.createElement('span');
            span.textContent = name.value;
            let hallStrong = document.createElement('strong');
            hallStrong.textContent = `Hall: ${hall.value}`;
            newElement.appendChild(span);
            newElement.appendChild(hallStrong);
            let div = document.createElement("div");
            let priceStrong = document.createElement('strong');
            priceStrong.textContent = Number(price.value).toFixed(2);
            let input = document.createElement('input');
            input.placeholder = "Tickets Sold";
            let archive = document.createElement('button');
            archive.textContent = "Archive";
            div.appendChild(priceStrong);
            div.appendChild(input);
            div.appendChild(archive);
            newElement.appendChild(div);
            moviesSection.appendChild(newElement);
            // name.value = "";
            // hall.value = "";
            // price.value = "";
        }

    }
}