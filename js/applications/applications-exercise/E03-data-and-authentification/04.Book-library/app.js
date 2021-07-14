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

async function getAllBooks() {
    const books = await request('http://localhost:3030/jsonstore/collections/books');
    let rows = Object.entries(books).map(createTableRow).join('');
    document.querySelector('tbody').innerHTML = rows;
}

function createTableRow([id, book]) {
   const result =`
   <tr data-id="${id}">
        <td>${book.title}</td>
        <td>${book.author}</td>
        <td>
        <button class ="editBtn">Edit</button>
        <button class ="deleteBtn">Delete</button>
        </td>
    </tr>`;
    return result
}

async function createNewBook(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const result = await request('http://localhost:3030/jsonstore/collections/books', {
        method: 'post',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            title: data.get('title'),
            author: data.get('author')
        })
    });
    event.target.reset();
    getAllBooks();
}

async function updateBook(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const id = formData.get('id');
    const book = {
        title: formData.get('title'),
        author: formData.get('author'),
    }
    await request('http://localhost:3030/jsonstore/collections/books/' + id , {
        method: 'put',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(book)
    });

    document.getElementById('createForm').style.display = 'block';
        document.getElementById('editForm').style.display = 'none';
        event.target.reset();
        getAllBooks();
}
async function deleteBook(id) {
    const result = await request('http://localhost:3030/jsonstore/collections/books/' + id , {
        method: 'delete',
    });
    getAllBooks()
}

function start() {
    document.getElementById('loadBooks').addEventListener('click', getAllBooks);
    document.getElementById('createForm').addEventListener('submit', createNewBook);
    document.getElementById('editForm').addEventListener('submit', updateBook);
    document.querySelector('table').addEventListener('click', handleTableClick);
}

start();

function handleTableClick(event) {
    if (event.target.classList.contains('editBtn')) {
        document.getElementById('createForm').style.display = 'none';
        document.getElementById('editForm').style.display = 'block';
        const bookId = event.target.parentNode.parentNode.dataset.id;
        loadBookForEditting(bookId);
    } else if (event.target.classList.contains('deleteBtn')) {
        const confirmed = confirm("Are you sure you want to delete this book?");
        if (confirm) {
            const bookId = event.target.parentNode.parentNode.dataset.id;
            deleteBook(bookId);
        }
        
    }
}

async function loadBookForEditting(id) {
     const book = await request('http://localhost:3030/jsonstore/collections/books/' + id);
     document.querySelector('#editForm [name="title"]').value = book.title;
     document.querySelector('#editForm [name="author"]').value = book.author;
     document.querySelector('#editForm [name="id"]').value = id;
}
