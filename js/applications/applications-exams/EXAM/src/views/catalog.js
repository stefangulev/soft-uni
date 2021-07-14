import {html} from '../../node_modules/lit-html/lit-html.js';
import { getAllArticles } from '../api/data.js';

const catalogTemplate = (data) => html `<section id="catalog-page" class="content catalogue">
<h1>All Articles</h1>
${data.length > 0 ? data.map(cardTemplate) :
html`<h3 class="no-articles">No articles yet</h3>
</section>`}`

const cardTemplate = (card) => html `<a class="article-preview" href=${`/details/${card._id}`}>
<article>
    <h3>Topic: <span>${card.title}</span></h3>
    <p>Category: <span>${card.category}</span></p>
</article>
</a>`

export async function showCatalog(ctx) {
    const data = await getAllArticles();
    ctx.render(catalogTemplate(data));
}