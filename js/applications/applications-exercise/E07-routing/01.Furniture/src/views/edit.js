import {html} from '../../node_modules/lit-html/lit-html.js';
import {updateFurniture, getFurnitureById} from '../api/data.js';

const editTemplate = (item, onSubmit, errorMessage) => html `<div class="row space-top">
<div class="col-md-12">
    <h1>Edit Furniture</h1>
    <p>Please fill all fields.</p>
</div>
</div>
<form @submit=${onSubmit}>
${errorMessage ? html`<div class="row space-top"><p>${errorMessage}</p></div>` : ""}
<div class="row space-top">
    <div class="col-md-4">
        <div class="form-group">
            <label class="form-control-label" for="new-make">Make</label>
            <input class="form-control" id="new-make" type="text" name="make" .value=${item.make}>
        </div>
        <div class="form-group has-success">
            <label class="form-control-label" for="new-model">Model</label>
            <input class="form-control is-valid" id="new-model" type="text" name="model" .value=${item.model}>
        </div>
        <div class="form-group has-danger">
            <label class="form-control-label" for="new-year">Year</label>
            <input class="form-control is-invalid" id="new-year" type="number" name="year" .value=${item.year}>
        </div>
        <div class="form-group">
            <label class="form-control-label" for="new-description">Description</label>
            <input class="form-control" id="new-description" type="text" name="description" .value=${item.description}>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="form-control-label" for="new-price">Price</label>
            <input class="form-control" id="new-price" type="number" name="price" .value=${item.price}>
        </div>
        <div class="form-group">
            <label class="form-control-label" for="new-image">Image</label>
            <input class="form-control" id="new-image" type="text" name="img" .value=${item.img}>
        </div>
        <div class="form-group">
            <label class="form-control-label" for="new-material">Material (optional)</label>
            <input class="form-control" id="new-material" type="text" name="material" .value=${item.material}>
        </div>
        <input type="submit" class="btn btn-info" value="Edit" />
    </div>
</div>
</form>`




export async function editPage(ctx) {
    const item = await getFurnitureById(ctx.params.id);
    ctx.render(editTemplate(item,onSubmit));

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
            return ctx.render(editTemplate(item, onSubmit, "Make/Model too short!"));
        }
        if (furniture.year < 1950 || furniture.year > 2050) {
            return ctx.render(editTemplate(item, onSubmit, "Invalid year!"));
        }
        if (furniture.description.length < 10) {
            return ctx.render(editTemplate(item, onSubmit, "Description too short!"));
        }
        if (furniture.price <= 0) {
            return ctx.render(editTemplate(item, onSubmit, "Price too low!"));
        }
        if (furniture.img.trim() == "") {
            return ctx.render(editTemplate(item, onSubmit, "URL is required!"));
        }
        await updateFurniture(item._id, furniture);
        ctx.page.redirect('/');

    }
    }