import {html} from '../../node_modules/lit-html/lit-html.js';
import {getMemeDetails, deleteMeme} from '../api/data.js';


const detailsTemplate = (meme, isCreator, onDelete) => html ` <section id="meme-details">
<h1>Meme Title: ${meme.title}

</h1>
<div class="meme-details">
    <div class="meme-img">
        <img alt="meme-alt" src=${meme.imageUrl}>
    </div>
    <div class="meme-description">
        <h2>Meme Description</h2>
        <p>
            ${meme.description}
        </p>

       ${isCreator ? html`<a class="button warning" href=${`/edit/${meme._id}`}>Edit</a>
        <button class="button danger" @click=${onDelete}>Delete</button>` : "" }
        
        
    </div>
</div>
</section>`

export async function showDetails(ctx) {
    const id = ctx.params.id;
    const currentMeme = await getMemeDetails(id);
    const isCreator = currentMeme._ownerId == sessionStorage.getItem('userId');
    ctx.render(detailsTemplate(currentMeme, isCreator, onDelete));

    async function onDelete() {
        const confirmed = confirm('Are you sure you want to delete this meme?');
        if(confirmed) {
            await deleteMeme(currentMeme._id);
            ctx.page.redirect('/all-memes');
        }
    }
}