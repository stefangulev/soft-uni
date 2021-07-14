import {html} from '../../node_modules/lit-html/lit-html.js';
import {editMemeDetails, getMemeDetails} from '../api/data.js';
import {notify} from './notification.js';

const editTemplate = (meme, onSubmit) => html `<section id="edit-meme">
<form id="edit-form", @submit=${onSubmit}>
    <h1>Edit Meme</h1>
    <div class="container">
        <label for="title">Title</label>
        <input id="title" type="text" placeholder="Enter Title" name="title" .value=${meme.title}>
        <label for="description">Description</label>
        <textarea id="description" placeholder="Enter Description" name="description" .value=${meme.description}>
               
            </textarea>
        <label for="imageUrl">Image Url</label>
        <input id="imageUrl" type="text" placeholder="Enter Meme ImageUrl" name="imageUrl" .value=${meme.imageUrl}>
        <input type="submit" class="registerbtn button" value="Edit Meme">
    </div>
</form>
</section>`

export async function showEdit(ctx) {
    const id = ctx.params.id;
    const currentMeme =await getMemeDetails(id);
    ctx.render(editTemplate(currentMeme,onSubmit))

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const title = formData.get('title');
        const description = formData.get('description');
        const imageUrl = formData.get('imageUrl');

        try {
            if (title.trim() == "" || description.trim() == "" || imageUrl.trim() == "") {
                throw new Error('All fields are required!')
            }
    
            await editMemeDetails(id, title, description, imageUrl);
            ctx.page.redirect(`/details/${id}`)
        } catch(err) {
            notify(err.message);
        }

        

    }
}