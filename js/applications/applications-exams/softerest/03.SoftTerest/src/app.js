import {render} from '../node_modules/lit-html/lit-html.js';
import page from '../node_modules/page/page.mjs';
import * as api from "./api/data.js";
import { showLogin } from './views/login.js';
import { showHome } from './views/home.js';
import { showRegister } from './views/register.js';
import { showDashboard } from './views/dashboard.js';
import { showCreate } from './views/create.js';
import { showDetails } from './views/details.js';
import { logout } from './api/api.js';
window.api = api;

const main = document.querySelector('main');
const logoutLink = document.getElementById('logoutLink').addEventListener('click', async () => {
    await logout();
    setUserNav();
    page.redirect('/');
})

page('/', decorateContext, showHome);
page('/login', decorateContext, showLogin)
page('/register', decorateContext, showRegister);
page('/dashboard', decorateContext, showDashboard);
page('/create', decorateContext, showCreate);
page('/details/:id', decorateContext, showDetails);

page.start();
setUserNav();

function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
}

function setUserNav() {
    const user = sessionStorage.getItem('email');
    if (user) {
        document.getElementById('guest').style.display = 'none'; 
        document.getElementById('user').style.display = 'inline'; 
    } else {
        document.getElementById('user').style.display = 'none'; 
        document.getElementById('guest').style.display = 'inline'; 
    }
}

