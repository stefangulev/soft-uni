import {html} from '../../node_modules/lit-html/lit-html.js';
import {createTeam} from '../api/data.js';

const createTemplate = (onSubmit, errorMessage) => html`<section id="create">
<article class="narrow">
    <header class="pad-med">
        <h1>New Team</h1>
    </header>
    <form id="create-form" @submit=${onSubmit} class="main-form pad-large">
        ${errorMessage ? html`<div class="error">${errorMessage}</div>` : ""}
        <label>Team name: <input type="text" name="name"></label>
        <label>Logo URL: <input type="text" name="logoUrl"></label>
        <label>Description: <textarea name="description"></textarea></label>
        <input class="action cta" type="submit" value="Create Team">
    </form>
</article>
</section>`

export function createCreate(ctx) {
    ctx.render(createTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
      
        const formData = new FormData(event.target);
        const name = formData.get('name');
        const logoUrl = formData.get('logoUrl');
        const description = formData.get('description');
        [...event.target.querySelectorAll('input')].forEach(e => e.disalbed = true);

        try {
            if (name == "" || logoUrl == "" || description == "") {
                throw new Error('All fields are required!');
            }
            if (name.length < 4) {
                throw new Error('Name is too short!');
            }
            if (description.length < 10) {
                throw new Error('Description is too short!');
            }
            const response = await createTeam({name, description, logoUrl})
            event.target.reset();
            //ADD CREATOR AS A MEMBER AND APPROVE REQUEST
            ctx.page.redirect(`/team-home/${response._id}`)
        } catch (err) {
            ctx.render(createTemplate(onSubmit, err.message));
        } finally {
            [...event.target.querySelectorAll('input')].forEach(e => e.disalbed = false );
        }
    }
}