import {html} from '../../node_modules/lit-html/lit-html.js';
import {until} from '../../node_modules/lit-html/directives/until.js';
import {getRequestByTeamId, getTeamById, requestToJoin, cancelMembership} from '../api/data.js';
import { loaderTemplate } from './common/loader.js';

const detailsTemplate = (team, createControls) => html `<section id="team-home">
<article class="layout">
    <img src=${team.logoUrl} class="team-logo left-col">
    <div class="tm-preview">
        <h2>${team.name}</h2>
        <p>${team.description}</p>
        <span class="details">3 Members</span>
        <div>
           ${createControls()}
        </div>
    </div>
    <div class="pad-large">
        <h3>Members</h3>
        <ul class="tm-members">
            <li>My Username</li>
            <li>James<a href="#" class="tm-control action">Remove from team</a></li>
            <li>Meowth<a href="#" class="tm-control action">Remove from team</a></li>
        </ul>
    </div>
    <div class="pad-large">
        <h3>Membership Requests</h3>
        <ul class="tm-members">
            <li>John<a href="#" class="tm-control action">Approve</a><a href="#"
                    class="tm-control action">Decline</a></li>
            <li>Preya<a href="#" class="tm-control action">Approve</a><a href="#"
                    class="tm-control action">Decline</a></li>
        </ul>
    </div>
</article>
</section>`

export async function createTeamHome(ctx) {
    const id = ctx.params.id;
    ctx.render(until(populateTemplate(id),loaderTemplate()))

    async function populateTemplate(teamId) {
        const userId = sessionStorage.getItem('userId');
        const [team, requests] = await Promise.all ([
            getTeamById(teamId),
            getRequestByTeamId(teamId)
        ])
        
        
        //TODO LOAD MEMBER
        return detailsTemplate(team, createControls);
    
        function createControls() {
            if (userId != null) {
                if (userId == team._ownerId) {
                   return  html` <a href=${`/edit/${team._id}`} class="action">Edit team</a>`;
                 } else if (requests.find(e => e._ownerId == userId && e.status == 'member')) {
                    return  html`<a href="javascript:void(0)" class="action invert">Leave team</a>`;
                 } else if (requests.find(e => e._ownerId == userId && e.status == 'pending')) {
                    return html `Membership pending. <a href="javascript:void(0)">Cancel request</a>`;
                 } else {
                    return html`<a href="javascript:void(0)" @click=${join} class="action">Join team</a>`;
                 }
            } else {
                return "";
            }
    
        }
    
        async function join(event) {
            event.target.remove();
           await requestToJoin(teamId) 
           ctx.render(until(populateTemplate(id),loaderTemplate()))
        }
        async function leave() {
            const confirmed = confirm("Are you sure?");
            if (confirmed) {
                await cancelMembership()
            }
           
        }
    }
    
}

