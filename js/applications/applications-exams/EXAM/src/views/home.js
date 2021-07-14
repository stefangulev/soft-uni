import {html} from '../../node_modules/lit-html/lit-html.js';
import { getMostRecentArticles } from '../api/data.js';

const homeTemplate = (js,java,python,csharp) => html `<section id="home-page" class="content">
<h1>Recent Articles</h1>
<section class="recent js">
    <h2>JavaScript</h2>
    ${js[0] ? js.map(articleTemplate) :html `<h3 class="no-articles">No articles yet</h3>`}
</section>
<section class="recent csharp">
    <h2>C#</h2>
    ${csharp[0] ? csharp.map(articleTemplate) :html `<h3 class="no-articles">No articles yet</h3>`}
</section>
<section class="recent java">
    <h2>Java</h2>
    ${java[0] ? java.map(articleTemplate) :html `<h3 class="no-articles">No articles yet</h3>`}
</section>
<section class="recent python">
    <h2>Python</h2>
    ${python[0] ? python.map(articleTemplate) :html `<h3 class="no-articles">No articles yet</h3>`}
</section>
</section>`

const articleTemplate = (card) => html `<section class="recent js">
<article>
    <h3>${card.title}</h3>
    <p>${card.content}</p>
    <a href=${`/details/${card._id}`} class="btn details-btn">Details</a>
</article>
</section>`

export async function showHome(ctx) {
    const data = await getMostRecentArticles();
    const js = data.filter(e => e.category == 'JavaScript');
    const java = data.filter(e => e.category == 'Java');
    const python = data.filter(e => e.category == 'Python');
    const csharp = data.filter(e => e.category == 'C#');
    ctx.render(homeTemplate(js,java,python,csharp));
}