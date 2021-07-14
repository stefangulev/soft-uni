function attachEvents() {
    document.getElementById('btnLoad').addEventListener('click', getNumbers);
    document.getElementById('btnCreate').addEventListener('click', createNumber);
    async function createNumber() {
        const person = document.getElementById('person');
        const phone = document.getElementById('phone');
        const response = await fetch('http://localhost:3030/jsonstore/phonebook', {
            method: 'post',
            headers: {"Content-type": 'application/json'},
            body: JSON.stringify({"person": person.value, "phone": phone.value})
        });
        person.value = "";
        phone.value = "";
        getNumbers();
    }
   async function getNumbers() {
       const phonebook = document.getElementById('phonebook');
       phonebook.innerHTML = "";
       const response = await fetch('http://localhost:3030/jsonstore/phonebook');
       let data = await response.json();
     Object.values(data).map(p =>e('li', {}, `${p.person}: ${p.phone}`, e('button', {className: 'button', id: p._id, onclick: deleteNum}, "Delete"))).forEach(e => phonebook.appendChild(e));
       
    }
    async function deleteNum(e) {
        const id = e.target.id;
        const response = await fetch("http://localhost:3030/jsonstore/phonebook/" + id, {
            method: 'delete'
        });
        e.target.parentNode.remove();
    }




}
function e(type, attributes, ...content) {
    const result = document.createElement(type);

    for (let [attr, value] of Object.entries(attributes || {})) {
        if (attr.substring(0, 2) == 'on') {
            result.addEventListener(attr.substring(2).toLocaleLowerCase(), value);
        } else {
            result[attr] = value;
        }
    }

    content = content.reduce((a, c) => a.concat(Array.isArray(c) ? c : [c]), []);

    content.forEach(e => {
        if (typeof e == 'string' || typeof e == 'number') {
            const node = document.createTextNode(e);
            result.appendChild(node);
        } else {
            result.appendChild(e);
        }
    });

    return result;
}

attachEvents();