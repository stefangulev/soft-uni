import * as api from "./api/data.js";
import {render} from "../node_modules/lit-html/lit-html.js";
import page from '../node_modules/page/page.mjs'
import { showHome } from "./views/home.js";
import { showLogin } from "./views/login.js";
import { showRegister } from "./views/register.js";
import { showCreate } from "./views/create.js";
import { showProfile } from "./views/profile.js";
import { showCatalog } from "./views/catalog.js";
import { showDetails } from "./views/details.js";
import { showEdit } from "./views/edit.js";
import { showSearch } from "./views/search.js";
import {logout} from './api/data.js';

window.api = api;

const main = document.getElementById("site-content");

page('/', decorateContext, showHome);
page('/login',decorateContext, showLogin);
page('/register',decorateContext, showRegister);
page('/create',decorateContext, showCreate);
page('/profile',decorateContext, showProfile);
page('/catalog',decorateContext, showCatalog);
page('/details/:id',decorateContext, showDetails);
page('/edit/:id',decorateContext, showEdit);
page('/search',decorateContext, showSearch);

page.start();
setUserNav();

document.getElementById('logoutLink').addEventListener('click', async () => {
    await logout();
    setUserNav();
    page.redirect('/home');
})

function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
}

function setUserNav() {
    const user = sessionStorage.getItem('user');
    if (user) {
        document.getElementById('guest').style.display = 'none';
        document.getElementById('profile').style.display = 'block';
        document.getElementById('welcome').textContent = `Welcome ${user}`;
    } else {
        document.getElementById('guest').style.display = 'block';
        document.getElementById('profile').style.display = 'none';
    }
    
}