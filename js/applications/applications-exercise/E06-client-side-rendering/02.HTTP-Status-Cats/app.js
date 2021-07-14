import {cats} from './catSeeder.js';
import { html, render } 
	from '../node_modules/lit-html/lit-html.js';

    const section = document.getElementById('allCats');

    const liTemplate = (cat) => html `
    <li><img src="./images/${cat.imageLocation}.jpg" width="250" height="250" alt="Card image cap"></li>
    <div class="info">
                    <button class="showBtn" >Show status code</button>
                    <div class="status" style="display: none" id=${cat.imageLocation}>
                        <h4>Status Code: ${cat.statusCode}</h4>
                        <p>${cat.statusMessage}</p>
                    </div>
                </div>
    `

const template = (data) => html`
    <ul>
        ${data.map(liTemplate)}
    </ul>`;

    render(template(cats), section);

    section.addEventListener('click', (e) => {
        if (e.target.classList.contains('showBtn')) {
            const divInfo = e.target.parentNode;
        const divStatus = divInfo.querySelector('.status');
            if(divStatus.style.display === 'none') {
                divStatus.style.display = 'block';
                e.target.textContent = "Hide status code";
            } else {
                divStatus.style.display = 'none';
                e.target.textContent = "Show status code";
            }
        }
    })







