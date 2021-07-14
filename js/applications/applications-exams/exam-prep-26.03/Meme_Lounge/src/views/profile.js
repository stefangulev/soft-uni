import {html} from '../../node_modules/lit-html/lit-html.js';
import {getUserMemes} from '../api/data.js';


const profileTemplate = (user, data) => html ` <section id="user-profile-page" class="user-profile">
<article class="user-info">
    <img id="user-avatar-url" alt="user-profile" src=${user.gender == "female" ? "/images/female.png" : "/images/male.png"}>
    <div class="user-content">
        <p>Username: ${user.username}</p>
        <p>Email: ${user.email}</p>
        <p>My memes count: ${user.memeCount}</p>
    </div>
</article>
<h1 id="user-listings-title">User Memes</h1>
<div class="user-meme-listings">
   ${data.length >0 ? data.map(cardTemplate) : html`<p class="no-memes">No memes in database.</p>`} 
    
</div>
</section>`

const cardTemplate = (meme) => html`<div class="user-meme">
<p class="user-meme-title">${meme.title}</p>
<img class="userProfileImage" alt="meme-img" src=${meme.imageUrl}>
<a class="button" href=${`/details/${meme._id}`}>Details</a>
</div>`

export async function showProfile(ctx) {
    const userMemes = await getUserMemes(sessionStorage.getItem('userId'));
    const user = {
        username: sessionStorage.getItem('username'),
        email: sessionStorage.getItem('email'),
        gender: sessionStorage.getItem('gender'),
        id: sessionStorage.getItem('userId'),
        memeCount: userMemes.length,

    }
    ctx.render(profileTemplate(user, userMemes));
}