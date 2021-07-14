import * as api from "./api/data.js";
import {render} from "../node_modules/lit-html/lit-html.js";
import page from '../node_modules/page/page.mjs'
import { showHome } from "./views/home.js";
import { showCatalog } from "./views/catalog.js";
import { showLogin } from "./views/login.js";
import { showRegister } from "./views/register.js";
import { showCreate } from "./views/create.js";
import { showSearch } from "./views/search.js";
import { showDetails } from "./views/details.js";
import { showEdit } from "./views/edit.js";
window.api = api;

const main = document.getElementById('main-content');

page('/', decorateContext, showHome);
page('/catalog',decorateContext, showCatalog);
page('/login',decorateContext, showLogin);
page('/register',decorateContext, showRegister);
page('/create',decorateContext, showCreate);
page('/search',decorateContext, showSearch);
page('/details/:id',decorateContext, showDetails);
page('/edit/:id',decorateContext, showEdit);
page.start();
setUserNav();

const logoutBtn = document.getElementById('logoutBtn').addEventListener('click', async () => {
    await api.logout();
    setUserNav();
    page.redirect('/');
})

function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
} 

function setUserNav() {
    const user = sessionStorage.getItem('userId');
    if (user) {
        document.getElementById('user').style.display = 'block';
        document.getElementById('guest').style.display = 'none';
    } else {
        document.getElementById('guest').style.display = 'block';
        document.getElementById('user').style.display = 'none';
    }
}
