import {html} from '../../node_modules/lit-html/lit-html.js';
import { register } from '../api/data.js'

const registerTemplate = (onSubmit) => html `<div class="container home wrapper  my-md-5 pl-md-5">
<div class="row-form d-md-flex flex-mb-equal ">
    <div class="col-md-4">
        <img class="responsive" src="./images/idea.png" alt="">
    </div>
    <form @submit=${onSubmit} class="form-user col-md-7" action="" method="">
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Register</h1>
        </div>
        <div class="form-label-group">
            <label for="inputEmail">Email</label>
            <input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email"
                autofocus="">
        </div>
        <div class="form-label-group">
            <label for="inputPassword">Password</label>
            <input type="password" id="inputPassword" name="password" class="form-control"
                placeholder="Password">
        </div>
        <div class="form-label-group">
            <label for="inputRepeatPassword">Repeat Password</label>
            <input type="password" id="inputRepeatPassword" name="repeatPassword" class="form-control"
                placeholder="Repeat Password">
        </div>
        <button class="btn btn-lg btn-dark btn-block" type="submit">Sign Up</button>
        <div class="text-center mb-4">
            <p class="alreadyUser"> Don't have account? Then just
                <a href="/login">Sign-In</a>!
            </p>
        </div>
        <p class="mt-5 mb-3 text-muted text-center">© SoftTerest - 2019.</p>
    </form>
</div>
</div>`

export async function showRegister(ctx) {
    ctx.render(registerTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const email = formData.get('email').trim();
        const password = formData.get('password').trim();
        const repeatPassword = formData.get('repeatPassword').trim();

        if (email == '' || password == '' || repeatPassword == '') {
            return window.alert("All fields are required!");
        }

        if (email.length < 3) {
            return window.alert('Email too short!');
        }
        if (password.length < 3) {
            return window.alert('Password too short!');
        }
        if (password != repeatPassword) {
            return window.alert("Passwords do not match!");
        }
        await register(email, password);
        ctx.setUserNav();
        ctx.page.redirect('/');
    }
}