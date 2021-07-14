
//OTHER ONE IS BETTER, THANKS.
async function request(url, options) {
    const response = await fetch(url, options);
    if (!response.ok) {
        const error = await response.json;
        alert(error.message);
        throw new Error(error.message)
    }
    const data = await response.json();
    return data;
}
document.querySelector('#loadBooks').addEventListener('click', loadBooks);
document.querySelector('#createForm').addEventListener('submit', createBook);

async function createBook(e) {
    e.preventDefault();
    const data = new FormData(e.target);
    const title = data.get('title');
    const author = data.get('author');
    if (title.trim() === "" || author.trim() === "") {
        return alert("All fields are required!")
    }
    const response = request('http://localhost:3030/jsonstore/collections/books', {
        method: 'post',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({"author": author, "title": title})
    });
    loadBooks();
    e.target.reset();
        
}

async function loadBooks() {
    const table = document.querySelector('tbody');
    table.innerHTML = "";
    const data = await request('http://localhost:3030/jsonstore/collections/books');
    Object.entries(data).map(([key, value]) => createTableRow(key, value)).forEach(r => table.appendChild(r));
    
}



async function updateBook(e) {
    const id = e.target.id;
    document.getElementById('createForm').style.display = 'none';
    document.getElementById('editForm').style.display = 'block';
    const table = document.getElementById('editForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        let data = new FormData(event.target);
        let newTitle = data.get('title');
        let newAuthor = data.get('author')
        const response = await request('http://localhost:3030/jsonstore/collections/books/' + id, {
            method: 'put',
            headers: {"Content-Type": 'application/json'},
            body: JSON.stringify({"author": newAuthor, "title": newTitle})
        })
        document.getElementById('createForm').style.display = 'block';
    document.getElementById('editForm').style.display = 'none';
    event.target.reset();
    loadBooks();
    
    });
    
    
    
    
}

async function deleteBook(e) {
        const id = e.target.id;
        const data = await request("http://localhost:3030/jsonstore/collections/books/" + id, {
            method: 'delete'
        });
        loadBooks();
}

function createTableRow(key , entry) {
    const result = e('tr', {}, e('td', {}, entry.author),e('td', {}, entry.title),e('td', {}, e('button', {id: key, onclick: updateBook}, "Edit"), e('button', {id: key, onclick: deleteBook}, "Delete")));
    return result;
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