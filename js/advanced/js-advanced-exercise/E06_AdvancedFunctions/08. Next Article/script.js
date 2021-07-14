function getArticleGenerator(articles) {

    return function display() {
        if (articles.length != 0) {
        let newElement = document.createElement("article");
        newElement.textContent = articles.shift();
        let div = document.getElementById("content");
        div.appendChild(newElement);
        }
    }
}
