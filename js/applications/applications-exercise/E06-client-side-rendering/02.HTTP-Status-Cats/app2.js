import {cats} from './catSeeder.js';
import { html, render } 
	from '../node_modules/lit-html/lit-html.js';
import {styleMap} from '../node_modules/lit-html/directives/style-map.js';

    const section = document.getElementById('allCats');

    const liTemplate = (cat) => html `
    <li><img src="./images/${cat.imageLocation}.jpg" width="250" height="250" alt="Card image cap"></li>
    <div class="info">
                    <button class="showBtn" >${cat.info ? 'Hide' : "Show"} status code</button>
                    <div class="status" style=${styleMap(cat.info ? {} : {display: 'none'})} id=${cat.id}>
                        <h4>Status Code: ${cat.statusCode}</h4>
                        <p>${cat.statusMessage}</p>
                    </div>
                </div>
    `
cats.forEach(c => c.info === false);
update();
function update() {
    const template = (data) => html`
    <ul @click=${toggleInfo}>
        ${data.map(liTemplate)}
    </ul>`;

    render(template(cats), section);
}


     function toggleInfo(e) {

            const divInfo = e.target.parentNode;
        const divStatusId = divInfo.querySelector('.status').id;
        const cat = cats.find(c => c.id == divStatusId);
        cat.info = !cat.info;
        update();
        
    };







