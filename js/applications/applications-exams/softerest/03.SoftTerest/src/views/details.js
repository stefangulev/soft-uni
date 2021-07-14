import {html} from '../../node_modules/lit-html/lit-html.js';
import { deleteIdea, getIdeaDetails } from '../api/data.js';

const detailsTemplate = (idea, isOwner, onDelete) => html `<div class="container home some">
<img class="det-img" src=${idea.img} />
<div class="desc">
    <h2 class="display-5">${idea.title}</h2>
    <p class="infoType">Description:</p>
    <p class="idea-description">${idea.description}</p>
</div>
<div class="text-center">
    ${isOwner ? html`<a @click=${onDelete} class="btn detb" href="javascript:void(0)">Delete</a>` : ""}
</div>
</div>`
export async function showDetails(ctx) {
    const id = ctx.params.id;
    const currentIdea = await getIdeaDetails(id);
    const isOwner = sessionStorage.getItem('userId') == currentIdea._ownerId;
    ctx.render(detailsTemplate(currentIdea, isOwner, onDelete));

    async function onDelete() {
        await deleteIdea(id);
        ctx.page.redirect('/dashboard')
    }
}