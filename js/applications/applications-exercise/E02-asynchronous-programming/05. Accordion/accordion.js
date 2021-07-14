async function solution() {
   const main = document.querySelector('#main');
   let titlesUrl = `http://localhost:3030/jsonstore/advanced/articles/list`;
   let response = await fetch(titlesUrl);
   let data = await response.json();
   let articles = [];
   for(let id of data.map(e=> e._id)) {
    let articleUrl = `http://localhost:3030/jsonstore/advanced/articles/details/${id}`
    let articleResponse = await fetch(articleUrl);
    let articleData = await articleResponse.json();
    articles.push(articleData);
   } 
   articles.map(createElement).forEach(e => main.appendChild(e));
   function createElement(article) {
       const result = e('div', {className:'accordion'}, e('div', {className: 'head'}, e('span', {}, `${article.title}`), e('button', {className:'button', id: article._id, onclick: act}, "More")),e('div', {className:'extra'}, e('p', {}, article.content)));
       return result;
   }

   function act(e) {
      const currentCard = e.target.parentNode.parentNode;
      const button = e.target;
      if (button.textContent === 'More') {
          button.textContent = 'Less';
          currentCard.querySelector('.extra').style.display = 'block';
      } else if (button.textContent === 'Less') {
          button.textContent = 'More';
          currentCard.querySelector('.extra').style.display = 'none';
      }
   }

}
function e(type, attributes, ...content) {
    const result = document.createElement(type);

    for (let [attr, value] of Object.entries(attributes || {})) {
        if (attr.substring(0, 2) == 'on') {
            result.addEventListener(attr.substring(2).toLocaleLowerCase(), value);
        } else {
            result[attr] = value;
        }
    }

    content = content.reduce((a, c) => a.concat(Array.isArray(c) ? c : [c]), []);

    content.forEach(e => {
        if (typeof e == 'string' || typeof e == 'number') {
            const node = document.createTextNode(e);
            result.appendChild(node);
        } else {
            result.appendChild(e);
        }
    });

    return result;
}

window.addEventListener('load', solution());