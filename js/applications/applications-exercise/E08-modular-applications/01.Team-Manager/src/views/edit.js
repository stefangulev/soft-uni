import {html} from '../../node_modules/lit-html/lit-html.js';
import {getTeamById, updateTeam} from '../api/data.js';
import { loaderTemplate } from './common/loader.js';
import {until} from '../../node_modules/lit-html/directives/until.js';

const editTemplate = (team, onSubmit, errorMessage) => html`<section id="edit">
<article class="narrow">
    <header class="pad-med">
        <h1>Edit Team</h1>
    </header>
    <form id="edit-form" @submit=${onSubmit} class="main-form pad-large">
        ${errorMessage ? html`<div class="error">${errorMessage}</div>` : ""}
        <label>Team name: <input type="text" name="name" .value=${team.name}></label>
        <label>Logo URL: <input type="text" name="logoUrl" .value=${team.logoUrl}></label>
        <label>Description: <textarea name="description" .value=${team.description}></textarea></label>
        <input class="action cta" type="submit" value="Save Changes">
    </form>
</article>
</section>`

export async function createEdit(ctx) {
    const id = ctx.params.id;
    
    ctx.render(until(populateTemplate(id), loaderTemplate()));

    
    async function populateTemplate(teamId) {
        const team = await getTeamById(teamId);
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
                await updateTeam(id, {name, description, logoUrl})
                event.target.reset();
                ctx.page.redirect(`/team-home/${id}`)
            } catch (err) {
                ctx.render(editTemplate(team,onSubmit, err.message));
            } finally {
                [...event.target.querySelectorAll('input')].forEach(e => e.disalbed = false );
            }
        }
        return editTemplate(team, onSubmit);
    
    }

    }

    
