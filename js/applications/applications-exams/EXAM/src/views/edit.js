import {html} from '../../node_modules/lit-html/lit-html.js';
import { editArticle, getArticleDetails } from '../api/data.js';

const editTemplate = (onSubmit, currentArticle) => html `<section id="edit-page" class="content">
<h1>Edit Article</h1>

<form @submit=${onSubmit} id="edit" action="#" method="">
    <fieldset>
        <p class="field title">
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" placeholder="Enter article title" .value=${currentArticle.title}>
        </p>

        <p class="field category">
            <label for="category">Category:</label>
            <input type="text" name="category" id="category" placeholder="Enter article category" .value=${currentArticle.category}>
        </p>
        <p class="field">
            <label for="content">Content:</label>
            <textarea name="content" id="content" .value=${currentArticle.content}></textarea>
        </p>

        <p class="field submit">
            <input class="btn submit" type="submit" value="Save Changes">
        </p>

    </fieldset>
</form>
</section>`

export async function showEdit(ctx) {
    const id = ctx.params.id;
    const currentArticle = await getArticleDetails(id);
    ctx.render(editTemplate(onSubmit, currentArticle));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const title = formData.get('title').trim();
        const category = formData.get('category').trim();
        const content = formData.get('content').trim();

        if(title == '' || category == "" || content == '') {
            return window.alert("All fields are required!");
        }
        const categories = ["JavaScript","C#","Java","Python"];

            if (!categories.includes(category)) {
                return window.alert("Invalid category!");
            }
            const article = {
                title,
                category,
                content
            }

            await editArticle(id, article);
            ctx.page.redirect(`/details/${id}`);
    }
}