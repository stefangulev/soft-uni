function encodeAndDecodeMessages() {
    let container = document.getElementById("container").addEventListener("click", onClick);
    let textAreas = Array.from(document.getElementsByTagName("textarea"));

    function onClick(event) {
        let textContainer = textAreas.find(x => x.parentElement === event.target.parentElement);
        let message = textContainer.value;
        if (event.target.nodeName === "BUTTON" && event.target.textContent === "Encode and send it") {
            let newMessage = "";
            for(let i = 0; i < message.length; i++) {
                let currentChar = message[i].charCodeAt(0) + 1;
                newMessage += String.fromCharCode(currentChar);
            }
            let otherTextArea = textAreas.find(x => x != textContainer);
            otherTextArea.value = newMessage;
            textContainer.value = "";
        } else if (event.target.nodeName === "BUTTON" && event.target.textContent === "Decode and read it") {
            let newMessage = "";
            for(let i = 0; i < message.length; i++) {
                let currentChar = message[i].charCodeAt(0) - 1;
                newMessage += String.fromCharCode(currentChar);
            }
            textContainer.value = newMessage;
        }
    }

}