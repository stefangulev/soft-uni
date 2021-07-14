import { html} from '../../node_modules/lit-html/lit-html.js';
import { getArticleDetails, deleteArticle } from '../api/data.js';

const detailsTemplate = (article, isOwner, onDelete) => html `<section id="details-page" class="content details">
<h1>${article.title}</h1>

<div class="details-content">
    <strong>Published in category ${article.category}</strong>
    <p>${article.content}</p>

    <div class="buttons">
        ${isOwner ? html`<a @click=${onDelete} href="javascript:void(0)" class="btn delete">Delete</a>
        <a href=${`/edit/${article._id}`} class="btn edit">Edit</a>` : ""}
        <a href="/" class="btn edit">Back</a>
    </div>
</div>
</section>`

export async function showDetails(ctx) {
    const id = ctx.params.id
    const currentArticle = await getArticleDetails(id)
    const isOwner = sessionStorage.getItem('userId') == currentArticle._ownerId;
    ctx.render(detailsTemplate(currentArticle, isOwner, onDelete))

    async function onDelete() {
        const confirmed = confirm('Are you sure you want to delete this article?')
        if(confirmed) {
            await deleteArticle(id);
            ctx.page.redirect('/')
        }
        
    }
}