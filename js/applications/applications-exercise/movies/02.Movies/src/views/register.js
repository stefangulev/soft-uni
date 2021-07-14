import {html} from '../../node_modules/lit-html/lit-html.js';
import {register} from '../api/data.js';
import {notify} from './notification.js';

const registerTemplate = (onSubmit) => html `<section @submit=${onSubmit} id="form-sign-up">
<form class="text-center border border-light p-5" action="#" method="post">
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" placeholder="Email" name="email" value="">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Password" name="password" value="">
    </div>

    <div class="form-group">
        <label for="repeatPassword">Repeat Password</label>
        <input type="password" class="form-control" placeholder="Repeat-Password" name="repeatPassword" value="">
    </div>

    <button type="submit" class="btn btn-primary">Register</button>
</form>
</section>`


export async function showRegister(ctx) {
    ctx.render(registerTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const email = formData.get('email');
        const password = formData.get('password');
        const rePass = formData.get('repeatPassword');

        if (email.trim() == "" || password.trim() == "" || rePass.trim() =="") {
            return notify("All fields are required!");
        }
        if (password.trim().length < 6) {
            return notify('Password too short!');
        }
        if (password != rePass) {
            return notify('Passwords do not match');
        }
        await register(email, password);
        ctx.page.redirect('/');
        ctx.setUserNav();
    }
}