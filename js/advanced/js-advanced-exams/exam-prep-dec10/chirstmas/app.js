function solution() {
    const [giftsList, sendList, discardList] = document.querySelectorAll("section ul");
    const addGiftBtn = document.querySelector('div button');
    addGiftBtn.addEventListener("click", addGift);
    let inputField = document.querySelector('input');
    giftsList.addEventListener('click', moveGift);

    function moveGift(e) {
        if(e.target.nodeName === 'BUTTON') {
            let giftName = e.target.parentNode.textContent.replace("SendDiscard", "");
            e.target.id ==="sendButton" ?sendList.appendChild(createElement('li',giftName, 'gift')) :discardList.appendChild(createElement('li',giftName,'gift'));
            e.target.parentNode.remove();
        }
    }
    
    function addGift(e) {
        let element = createElement('li', inputField.value, "gift");
        let sendBtn = createElement('button', "Send");
        sendBtn.id = "sendButton";
        let discardBtn = createElement('button', "Discard");
        discardBtn.id = "discardButton";
        element.appendChild(sendBtn);
        element.appendChild(discardBtn);
        giftsList.appendChild(element);
        inputField.value = "";
        let gifts = Array.from(giftsList.childNodes).sort((l,r) => l.textContent.localeCompare(r.textContent)).forEach(e => giftsList.appendChild(e));
    }


    function createElement(type, content, className) {
        let element = document.createElement(type);
        element.textContent = content;
        if (className) {
            element.classList.add(className);
        }
        return element;
    }
}