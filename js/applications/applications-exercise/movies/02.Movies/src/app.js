import {render} from '../node_modules/lit-html/lit-html.js';
import page from '../node_modules/page/page.mjs'
import { showHome } from './views/home.js';
import { showLogin } from './views/login.js';
import { showRegister } from './views/register.js';
import { showCreate } from './views/create.js';
import { showDetails } from './views/details.js';
import { showEdit } from './views/edit.js';
import * as api from './api/data.js';
window.api = api;

const main = document.querySelector('main');
const logoutLink = document.getElementById('logoutLink');
logoutLink.addEventListener('click', async () => {
    await api.logout();
    setUserNav();
    page.redirect('/login');
   
})


page('/', decorateContext, showHome)
page('/register', decorateContext,  showRegister)
page('/login', decorateContext, showLogin)
page('/create', decorateContext, showCreate);
page('/details/:id',decorateContext, showDetails)
page('/edit/:id', decorateContext, showEdit);

page.start();
setUserNav();

function setUserNav() {
    const userEmail = sessionStorage.getItem('email');
    if (userEmail) {
        document.querySelectorAll('.guest').forEach(e => e.style.display = "none");
        document.querySelectorAll('.user').forEach(e => e.style.display = "block");
        document.getElementById('welcomeLink').textContent = `Welcome, ${userEmail}`
    } else {
        document.querySelectorAll('.user').forEach(e => e.style.display = "none");
        document.querySelectorAll('.guest').forEach(e => e.style.display = "block");
    }
}

function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
}