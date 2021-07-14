import {towns} from './towns.js';
import { html, render } 
	from '../node_modules/lit-html/lit-html.js';

    const searchTemplate = (towns, match) => html `<article>
    <div id="towns">
        ${towns.map(t => itemTemplate(t, match))}
    </div>
    <input type="text" id="searchText" .value=${match} />
    <button @click=${search}>Search</button>
    <div id="result">${countMatches(towns, match)}</div>
</article>`

const itemTemplate = (name, match) => html`
<li class=${(match && name.toLowerCase().includes(match.toLowerCase())) ? 'active' : ""}>${name}</li>
`;

const main = document.body;
update();

function update(match = "") {
    const result = searchTemplate(towns, match);
    render(result, main);
}

    function search(event) {
    const match = event.target.parentNode.querySelector('input').value;
    update(match);
    }

    function countMatches(towns, match) {
        const result = towns.filter(t => match && t.toLowerCase().includes(match.toLowerCase())).length;
        if (result) { 
            return `${result} matches found.`
        } else {
            return "";
        }
    }
