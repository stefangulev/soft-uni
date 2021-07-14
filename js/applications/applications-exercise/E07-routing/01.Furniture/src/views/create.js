import {html} from '../../node_modules/lit-html/lit-html.js';
import {createFurniture} from '../api/data.js';

const createTemplate = (onSubmit, errorMessage) => html`<div class="row space-top">
<div class="col-md-12">
    <h1>Create New Furniture</h1>
    <p>Please fill all fields.</p>
</div>
</div>
<form @submit=${onSubmit}>
${errorMessage ? html`<div class="row space-top"><p>${errorMessage}</p></div>` : ""}
<div class="row space-top">
    <div class="col-md-4">
        <div class="form-group">
            <label class="form-control-label" for="new-make">Make</label>
            <input class="form-control valid" id="new-make" type="text" name="make">
        </div>
        <div class="form-group has-success">
            <label class="form-control-label" for="new-model">Model</label>
            <input class="form-control is-valid" id="new-model" type="text" name="model">
        </div>
        <div class="form-group has-danger">
            <label class="form-control-label" for="new-year">Year</label>
            <input class="form-control is-invalid" id="new-year" type="number" name="year">
        </div>
        <div class="form-group">
            <label class="form-control-label" for="new-description">Description</label>
            <input class="form-control" id="new-description" type="text" name="description">
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="form-control-label" for="new-price">Price</label>
            <input class="form-control" id="new-price" type="number" name="price">
        </div>
        <div class="form-group">
            <label class="form-control-label" for="new-image">Image</label>
            <input class="form-control" id="new-image" type="text" name="img">
        </div>
        <div class="form-group">
            <label class="form-control-label" for="new-material">Material (optional)</label>
            <input class="form-control" id="new-material" type="text" name="material">
        </div>
        <input type="submit" class="btn btn-primary" value="Create" />
    </div>
</div>
</form>`

export async function createPage(ctx) {
    ctx.render(createTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const furniture  = {
            make: formData.get('make'),
            model:formData.get('model'),
            year:formData.get('year'),
            description:formData.get('description'),
            price:formData.get('price'),
            img:formData.get('img'),
            material:formData.get('material')
        };
        //const data =[...formData.entries()].reduce((a, [k, v]) => Object.assign(a, {[k]: v}), {});
        if (furniture.make.length < 4 || furniture.model.length < 4) {
            return ctx.render(createTemplate(onSubmit, "Make/Model too short!"));
        }
        if (furniture.year < 1950 || furniture.year > 2050) {
            return ctx.render(createTemplate(onSubmit, "Invalid year!"));
        }
        if (furniture.description.length < 10) {
            return ctx.render(createTemplate(onSubmit, "Description too short!"));
        }
        if (furniture.price <= 0) {
            return ctx.render(createTemplate(onSubmit, "Price too low!"));
        }
        if (furniture.img.trim() == "") {
            return ctx.render(createTemplate(onSubmit, "URL is required!"));
        }
        await createFurniture(furniture);
        ctx.page.redirect('/');


    }
}