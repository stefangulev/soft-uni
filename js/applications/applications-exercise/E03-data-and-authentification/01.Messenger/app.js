function attachEvents() {
    let [author, content] = Array.from(document.querySelectorAll('input'));
    let [submitBtn, refreshBtn] = Array.from(document.querySelectorAll('input[type=button]'));
    let textArea = document.getElementById('messages');
    submitBtn.addEventListener('click', submitMsg);
    refreshBtn.addEventListener('click', showMsg);
    

    async function submitMsg() {
        const response = await fetch('http://localhost:3030/jsonstore/messenger', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({author: author.value, content: content.value})
        })
        if (!response.ok) {
            const error = await response.json();
            alert(error.message);
        }
        author.value = "";
    content.value = "";
    }
    async function showMsg() {
        const response = await fetch('http://localhost:3030/jsonstore/messenger');
        if (!response.ok) {
            const error = await response.json();
            alert(error.message);
        }
        const data = await response.json();
        let messages = Object.values(data).map(m => `${m.author}: ${m.content}`).join('\n');
        textArea.value = messages;
    }



}

attachEvents();