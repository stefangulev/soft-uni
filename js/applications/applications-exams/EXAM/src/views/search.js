import {html} from '../../node_modules/lit-html/lit-html.js';
import { searchByTitle } from '../api/data.js';

const searchTemplate = (cards, onSearch, title) => html `<section id="search-page" class="content">
<h1>Search</h1>
<form @submit=${onSearch} id="search-form">
    <p class="field search">
        <input type="text" placeholder="Search by article title" name="search" .value=${title || ""}>
    </p>
    <p class="field submit">
        <input class="btn submit" type="submit" value="Search">
    </p>
</form>
<div class="search-container">
    ${cards.length > 0 ? cards.map(articleTemplate) : html `<h3 class="no-articles">No matching articles</h3>`}
</div>
</section>`

const articleTemplate = (article) => html `<a class="article-preview" href=${`/details/${article._id}`}>
<article>
    <h3>Topic: <span>${article.title}</span></h3>
    <p>Category: <span>${article.category}</span></p>
</article>
</a>`

export async function showSearch(ctx) {
    const title = ctx.querystring.split('=')[1];
    const cards = title ? await searchByTitle(title) : [];
    ctx.render(searchTemplate(cards, onSearch, title));

     function onSearch(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const query = formData.get('search');
        ctx.page.redirect('/search?query=' + query);
 
    }
    
}