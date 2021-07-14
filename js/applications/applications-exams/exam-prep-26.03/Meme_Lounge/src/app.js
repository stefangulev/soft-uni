import page from '../node_modules/page/page.mjs'
import {render} from '../node_modules/lit-html/lit-html.js';
import {showHome} from './views/home.js';
import {showAllMemes} from './views/all-memes.js';
import {showLogin} from './views/login.js';
import {showRegister} from './views/register.js';
import {showCreate} from './views/create.js';
import {showDetails} from './views/details.js';
import {showEdit} from './views/edit.js';
import {showProfile} from './views/profile.js';
import * as api from './api/data.js';

window.api = api;
const main = document.querySelector('main');

page('/', decorateContext, sessionStorage.getItem('authToken') ? showAllMemes : showHome);
page('/login', decorateContext, showLogin);
page('/register', decorateContext, showRegister);
page('/create', decorateContext, showCreate)
page('/all-memes',decorateContext, showAllMemes);
page('/details/:id',decorateContext, showDetails);
page('/edit/:id',decorateContext, showEdit);
page('/profile',decorateContext, showProfile);

page.start();
setUserNav();
const logoutBtn = document.querySelector('.logoutBtn');
logoutBtn.addEventListener('click', async () => {
    await api.logout();
    setUserNav();
    page.redirect('/');
})

function setUserNav() {
    if(sessionStorage.getItem('authToken')) {
        document.querySelector('.user').style.display = "block";
        document.querySelector('.guest').style.display = "none";
        document.querySelector('.user span').textContent = `Welcome, ${sessionStorage.getItem('email')}`
    } else {
        document.querySelector('.user').style.display = "none";
        document.querySelector('.guest').style.display = "block";
    }
}

function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
}