import {html} from '../../node_modules/lit-html/lit-html.js'
import {getCarDetails, updateCar} from '../api/data.js';


const editTemplate = (car, onSubmit) => html `<section id="edit-listing">
<div class="container">

    <form id="edit-form" @submit=${onSubmit}>
        <h1>Edit Car Listing</h1>
        <p>Please fill in this form to edit an listing.</p>
        <hr>

        <p>Car Brand</p>
        <input type="text" placeholder="Enter Car Brand" name="brand" .value=${car.brand}>

        <p>Car Model</p>
        <input type="text" placeholder="Enter Car Model" name="model" .value=${car.model}>

        <p>Description</p>
        <input type="text" placeholder="Enter Description" name="description" .value=${car.description}>

        <p>Car Year</p>
        <input type="number" placeholder="Enter Car Year" name="year" .value=${car.year}>

        <p>Car Image</p>
        <input type="text" placeholder="Enter Car Image" name="imageUrl" .value=${car.imageUrl}>

        <p>Car Price</p>
        <input type="number" placeholder="Enter Car Price" name="price" .value=${car.price}>

        <hr>
        <input type="submit" class="registerbtn" value="Edit Listing">
    </form>
</div>
</section>`

export async function showEdit(ctx) {
    const id = ctx.params.id;
    const currentCar = await getCarDetails(id);
    ctx.render(editTemplate(currentCar, onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const brand = formData.get('brand');
        const model = formData.get('model');
        const description = formData.get('description');
        const year = formData.get('year');
        const imageUrl = formData.get('imageUrl');
        const price = formData.get('price');

        if (brand.trim() == '' || model.trim() == '' || description.trim() == '' || year.trim() == '' || imageUrl.trim() == '' || price.trim() == '') {
            return window.alert('All fields are required!');
        }

        const car = {
            brand,
            model,
            description,
            year:Number(year),
            imageUrl,
            price:Number(price)
        }
        await updateCar(id, car);
        ctx.page.redirect('/catalog');
    }
}