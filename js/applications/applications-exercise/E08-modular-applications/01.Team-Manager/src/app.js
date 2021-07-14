import page from '../node_modules/page/page.mjs'
import {render} from '../node_modules/lit-html/lit-html.js';
import * as api from './api/data.js';
window.api = api;
import {createHome} from './views/home.js';
import {createBrowse} from './views/browse.js';
import {createEdit} from './views/edit.js';
import {createCreate} from './views/create.js';
import {createLogin} from './views/login.js';
import {createMyTeams} from './views/my-teams.js';
import {createRegister} from './views/register.js';
import {createTeamHome} from './views/team-home.js';
const main = document.querySelector('main');

page('/', decorateContent, createHome)
page('/browse', decorateContent, createBrowse)
page('/edit/:id', decorateContent, createEdit)
page('/create', decorateContent, createCreate)
page('/login', decorateContent, createLogin)
page('/my-teams', decorateContent, createMyTeams)
page('/register', decorateContent, createRegister)
page('/team-home/:id', decorateContent, createTeamHome)

page.start();
setUserNav();
document.getElementById('logoutLink').addEventListener('click', async () => {
    await api.logout();
    setUserNav();
    page.redirect('/');
})

function setUserNav() {
    const id = sessionStorage.getItem('userId');
    if (id === null) {
        Array.from(document.querySelectorAll('nav > a.guest')).forEach(e => e.style.display = 'inline-block');
        Array.from(document.querySelectorAll('nav > a.user')).forEach(e => e.style.display = 'none');
    } else {
        Array.from(document.querySelectorAll('nav > a.user')).forEach(e => e.style.display = 'inline-block');
        Array.from(document.querySelectorAll('nav > a.guest')).forEach(e => e.style.display = 'none');
    }
}



function decorateContent(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
}

