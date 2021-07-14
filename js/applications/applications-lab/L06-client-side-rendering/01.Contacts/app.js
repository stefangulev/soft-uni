import { html, render } 
	from './node_modules/lit-html/lit-html.js';

    import {contacts} from './contacts.js';

    const template = (data) => html`<div class="contact card">
    <div>
        <i class="far fa-user-circle gravatar"></i>
    </div>
    <div class="info">
        <h2>Name: ${data.name}</h2>
        <button class="detailsBtn">Details</button>
        <div class="details" id=${data.id}>
            <p>Phone number: ${data.phoneNumber}</p>
            <p>Email: ${data.email}</p>
        </div>
    </div>
</div>`;

const section = document.getElementById('contacts');
const cards = contacts.map(template);
render(cards, section);

section.addEventListener('click', onClick)
function onClick(e) {
    const details = e.target.parentNode.querySelector('.details');
    details.style.display = details.style.display === 'none' ? 'block' : 'none'; 

}

