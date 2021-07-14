function solution() {
    let addGiftsCard = document.getElementsByClassName('card')[0];
    let addBtn = addGiftsCard.getElementsByTagName('button')[0];
    addBtn.addEventListener('click', onClick);
    let giftsCard = document.getElementsByClassName('card')[1];
    let listofGifts = giftsCard.getElementsByTagName('ul')[0];
    giftsCard.addEventListener('click', giftAction);
    let sendCard = document.getElementsByClassName('card')[2];
    let sendList = sendCard.getElementsByTagName('ul')[0];
    let discardCard = document.getElementsByClassName('card')[3];
    let discardList = discardCard.getElementsByTagName('ul')[0];

    function giftAction(e) {
        if (e.target.nodeName === "BUTTON") {
            let giftName = e.target.parentNode.textContent.replace("SendDiscard", '');
            listofGifts.removeChild(e.target.parentNode);
            let newSentElement = document.createElement('li');
                newSentElement.textContent = giftName;
            if (e.target.textContent === 'Send') {
                sendList.appendChild(newSentElement);
            } else if (e.target.textContent === 'Discard') {
                discardList.appendChild(newSentElement);
            }
        }
    }

    function onClick(e) {

        let newGift = document.createElement('li');
        newGift.textContent = addGiftsCard.getElementsByTagName('input')[0].value;
        newGift.classList.add('gift');
        let sendBtn = document.createElement('button');
        sendBtn.textContent = "Send";
        sendBtn.classList.add('sendButton');
        let discardBtn = document.createElement('button');
        discardBtn.textContent = "Discard";
        discardBtn.classList.add('discardButton');
        newGift.appendChild(sendBtn);
        newGift.appendChild(discardBtn);
        listofGifts.appendChild(newGift);
        let arr = Array.from(listofGifts.children).sort((l,r) => l.textContent.replace("SendDiscard", '').localeCompare(r.textContent.replace("SendDiscard", '')));
        while(listofGifts.firstChild) {
            listofGifts.removeChild(listofGifts.lastChild);
        }
        arr.forEach(e => listofGifts.appendChild(e));
        addGiftsCard.getElementsByTagName('input')[0].value = "";
    }


    
}