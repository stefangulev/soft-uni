function attachEvents() {
    const loadButton = document.querySelector("#btnLoadPosts");
    loadButton.addEventListener('click', load);
    const posts = document.querySelector('#posts');
    const viewButton = document.querySelector('#btnViewPost');
    viewButton.addEventListener('click', view);

    async function view() {
        const postUrl = `http://localhost:3030/jsonstore/blog/posts/${posts.value}`
        const commentsUrl = `http://localhost:3030/jsonstore/blog/comments`;
        const [postResponse, commentsResponse] = await Promise.all([
            fetch(postUrl),
            fetch(commentsUrl)
        ]);
        const postData = await postResponse.json();
        const commentsData = await commentsResponse.json();
        let filteredComments = Object.values(commentsData).filter(c => c.postId === postData.id);
        createBlogPost(postData, filteredComments);

        
    }
    function createBlogPost(postData, comments) {
        const heading = document.querySelector('#post-title');
        heading.textContent = postData.title.toUpperCase();
        const postBody = document.querySelector('#post-body');
        postBody.textContent = postData.body;
        const commentsList = document.querySelector('#post-comments');
        commentsList.innerHTML = "";
        comments.map(c => e('li', {id: c.id}, c.text)).forEach(c => commentsList.appendChild(c));

    }
    async function load() {
        const url = `http://localhost:3030/jsonstore/blog/posts`;
        const response = await fetch(url);
        const data = await response.json();
        Object.entries(data).map(([key, value]) =>e('option', {value: key}, value.title.toUpperCase())).forEach(e => posts.appendChild(e));
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