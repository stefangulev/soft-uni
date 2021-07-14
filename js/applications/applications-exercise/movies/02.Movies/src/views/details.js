import {html} from '../../node_modules/lit-html/lit-html.js';
import {getMovieById, deleteMovie, getNumberOfLikes, like, getLikeFromUser, revokeLike} from '../api/data.js';

const detailsTemplate = (movie, isOwner, onDelete, likes, currentUserLike, onLike, onRevoke) => html `<section id="movie-example">
<div class="container">
    <div class="row bg-light text-dark">
        <h1>${movie.title}</h1>

        <div class="col-md-8">
            <img class="img-thumbnail" src=${movie.img}
                 alt="Movie">
        </div>
        <div class="col-md-4 text-center">
            <h3 class="my-3 ">Movie Description</h3>
            <p>${movie.description}</p>
            ${isOwner ?
            html`<a @click=${onDelete} class="btn btn-danger" href="javascript:void(0)">Delete</a>
            <a class="btn btn-warning" href=${`/edit/${movie._id}`}>Edit</a>` : ""}
            ${isOwner || currentUserLike.length > 0 ? html`<span @click=${onRevoke} class="enrolled-span">Liked ${likes}</span>` : html`
            <button @click=${onLike} class="btn btn-primary">Like</button>`}
        </div>
    </div>
</div>
</section>`

export async function showDetails(ctx) {
    const id = ctx.params.id;
    const currentMovie = await getMovieById(id);
    ctx.render(detailsTemplate(await getMovieById(id), sessionStorage.getItem('userId') == currentMovie._ownerId, onDelete,  await getNumberOfLikes(id), await getLikeFromUser(id, sessionStorage.getItem('userId')), onLike, onRevoke));

    async function onDelete() {
        const confirmed = confirm("Are you sure you want to delete this movie?");
        if (confirmed) {
            await deleteMovie(id);
            ctx.page.redirect('/');
        }
        
    }
    async function onLike() {
        await like(id);
        ctx.render(detailsTemplate(await getMovieById(id), sessionStorage.getItem('userId') == currentMovie._ownerId, onDelete,  await getNumberOfLikes(id), await getLikeFromUser(id, sessionStorage.getItem('userId')), onLike, onRevoke));
    }

    async function onRevoke() {
        const currentLike = await getLikeFromUser(id,sessionStorage.getItem('userId'));
        if(currentLike[0]) {
            await revokeLike(currentLike[0]._id);
            ctx.render(detailsTemplate(await getMovieById(id), sessionStorage.getItem('userId') == currentMovie._ownerId, onDelete,  await getNumberOfLikes(id), await getLikeFromUser(id, sessionStorage.getItem('userId')), onLike, onRevoke));
        }
       
    }
}