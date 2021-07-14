import { html, render } 
	from '../node_modules/lit-html/lit-html.js';

    const section = document.getElementById('root');

    const template = (data) => html`
    <ul>
        ${data.map(t => html`<li>${t}</li>`)}
    </ul>
    `
  
  const button = document.getElementById('btnLoadTowns').addEventListener('click', (e) => {
      e.preventDefault();
      const value = document.getElementById('towns').value.split(', ').map(x => x.trim());
      render(template(value), section);
     })

    


    

    
    

