const form = document.querySelector('form').addEventListener('submit', createArticle);

async function createArticle(e) {
    e.preventDefault();
    const token = sessionStorage.getItem('authToken');
    let data = new FormData(e.target);
    let name = data.get('name');
    let imgUrl = data.get('img');
    let ingredients = data.get('ingredients').split('\n').map(l => l.trim()).filter(l => l != " ");
    let steps = data.get('steps').split('\n').map(l => l.trim()).filter(l => l != " ");

    const response = await fetch('http://localhost:3030/data/recipes', {
        method: 'post',
        headers: {"X-Authorization": token},
        body: JSON.stringify({name, imgUrl, ingredients, steps})
    });
    if (!response.ok) {
        const error = await response.json();
        return alert(error.message);
    }
    window.location.pathname = 'index.html';
}