import { html, render } 
	from '../node_modules/lit-html/lit-html.js';

    const selectTemplate = (arr) => html `
     <select id="menu">
         ${arr.map(optionParser)}
     </select>
     `

     const optionParser = (element) => html`
      <option value=${element._id}>${element.text}</option>
     `

const div = document.querySelector('div');
     async function update() {
         const response = await fetch('http://localhost:3030/jsonstore/advanced/dropdown');
         const data = Object.values(await response.json());
         render(selectTemplate(data), div);
     }

     update();
        
     const form = document.querySelector('form').addEventListener('submit', addItem);

async function addItem(e) {
    e.preventDefault();
    const input = document.getElementById('itemText').value;
    const response = await fetch('http://localhost:3030/jsonstore/advanced/dropdown', {
        method:'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({"text": input})
    })
    e.target.reset();
    update();
}