import {html} from '../../node_modules/lit-html/lit-html.js';
import {getTeams} from '../api/data.js';


//FINISH GET MEMBER FUNCTIONALITY
const browseTemplate = (id, teams) => html `<section id="browse">
<article class="pad-med">
    <h1>Team Browser</h1>
</article>

${id ? html`<article class="layout narrow">
    <div class="pad-small"><a href="/create" class="action cta">Create Team</a></div>
</article>` : ""}

${teams.map(teamTemplate)}
</section>`

const teamTemplate = (team) => html`<article class="layout">
<img src=${team.logoUrl} class="team-logo left-col">
<div class="tm-preview">
    <h2>${team.name}</h2>
    <p>${team.description}</p>
    <span class="details">5000 Members</span>
    <div><a href="/team-home/${team._id}" class="action">See details</a></div>
</div>
</article>`



export async function createBrowse(ctx) {
    const id = sessionStorage.getItem('userId');
    const teams = await getTeams();
    ctx.render(browseTemplate(id, teams));
}