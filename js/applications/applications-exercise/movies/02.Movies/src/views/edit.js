import {html} from '../../node_modules/lit-html/lit-html.js';
import {getMovieById, updateMovie} from '../api/data.js';

const editTemplate = (movie, onSubmit) => html`<section id="edit-movie">
<form @submit=${onSubmit} class="text-center border border-light p-5" action="#" method="">
    <h1>Edit Movie</h1>
    <div class="form-group">
        <label for="title">Movie Title</label>
        <input type="text" class="form-control" placeholder="Movie Title" .value=${movie.title} name="title">
    </div>
    <div class="form-group">
        <label for="description">Movie Description</label>
        <textarea class="form-control" placeholder="Movie Description..." name="description" .value=${movie.description}></textarea>
    </div>
    <div class="form-group">
        <label for="imageUrl">Image url</label>
        <input type="text" class="form-control" placeholder="Image Url" .value=${movie.imageUrl} name="imageUrl">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</section>`

export async function showEdit(ctx) {
    const id = ctx.params.id;
    const currentMovie = await getMovieById(id);
    ctx.render(editTemplate(currentMovie, onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const title = formData.get('title');
        const description = formData.get('description');
        const imageUrl = formData.get('imageUrl');
        await updateMovie(id,{title, description,imageUrl});
        ctx.page.redirect(`/details/${currentMovie._id}`);
    }
}