import {html} from '../../node_modules/lit-html/lit-html.js';
import {register} from '../api/data.js';

const registerTempalte = (onSubmit, error) => html `<section id="register">
<article class="narrow">
    <header class="pad-med">
        <h1>Register</h1>
    </header>
    <form @submit=${onSubmit} id="register-form" class="main-form pad-large">
        ${error ? html`<div class="error">${error}</div>` : ""}
        <label>E-mail: <input type="text" name="email"></label>
        <label>Username: <input type="text" name="username"></label>
        <label>Password: <input type="password" name="password"></label>
        <label>Repeat: <input type="password" name="repass"></label>
        <input class="action cta" type="submit" value="Create Account">
    </form>
    <footer class="pad-small">Already have an account? <a href="/login" class="invert">Sign in here</a>
    </footer>
</article>
</section>`

export function createRegister(ctx) {
   ctx.render(registerTempalte(onSubmit));
   ctx.setUserNav();

   async function onSubmit(event) {
       event.preventDefault();
       const formData = new FormData(event.target);
       const email = formData.get('email');
       const username = formData.get('username');
       const password = formData.get('password');
       const repass = formData.get('repass');

       if (!/.+\@.+/.test(email)) {
        return ctx.render(registerTempalte(onSubmit, "Invalid mail"));
       }
       if (username.length < 3 || password.length < 3) {
        return ctx.render(registerTempalte(onSubmit, "Username/password too short"));
       }
       if (password != repass) {
        return ctx.render(registerTempalte(onSubmit, "Passwords do not match"));
       }
      await register(email, username, password);
      ctx.setUserNav();
       ctx.page.redirect('/my-teams');
   }
}