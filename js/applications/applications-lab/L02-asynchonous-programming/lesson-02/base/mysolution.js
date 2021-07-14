async function solve() {
    const main  = document.querySelector('main');
    main.addEventListener('click', onClick);
    main.innerHTML = "";
    const url = `http://localhost:3030/jsonstore/cookbook/recipes`;
    const response = await fetch(url);
    const data = await response.json();
    Object.values(data).forEach(e => {
       let newArticle = createElement('article', "", 'preview');
       let headingDiv = createElement('div', "", 'title');
       let heading = createElement('h2', `${e.name}`);
       let picturDiv = createElement('div', "", 'small');
       let picture = createElement('img');
       picture.src = `${e.img}`;
       headingDiv.appendChild(heading);
       picturDiv.appendChild(picture);
       newArticle.appendChild(headingDiv);
       newArticle.appendChild(picturDiv);
       main.appendChild(newArticle);
    });
    function createElement(type, content, className) {
        let element = document.createElement(type);
        if (content) {
           element.textContent = content;
        }
        if(className) {
           element.classList.add(className);
        }
        return element;
     }
     async function onClick(e) {
         if (e.target.nodeName === "ARTICLE") {
             const url = `http://localhost:3030/jsonstore/cookbook/details/0${e.target.querySelector('h2').textContent.split(' ')[1]}`
             const response = await fetch(url);
             const data = await response.json();
             console.log(data);
             let newArticle = createElement('article');
             let title = createElement('h2', `Recipe ${e.target.querySelector('h2').textContent.split(' ')[1]}`)
             let firstDiv = createElement('div', "", 'band');
             let secondDiv = createElement('div', "", "thumb");
             let picture = createElement('img');
             picture.src = `${data.img}`
             let ingredients = createElement('div', "", 'ingredients');
             let ingredientsHeading = createElement('h3', "Ingredients:");
             let newUl = createElement('ul');
             data.ingredients.forEach(i => {
                 newUl.appendChild(createElement('li', i));
             });
             let divPreparation = createElement('div', "" ,'description');
             let prepHeading = createElement('h3', 'Preparation:');
             divPreparation.appendChild(prepHeading);
             data.steps.forEach(s => {
                 divPreparation.appendChild(createElement('p', s));
             });
             newArticle.appendChild(title);
             secondDiv.appendChild(picture);
             firstDiv.appendChild(secondDiv);
             ingredients.appendChild(ingredientsHeading);
             ingredients.appendChild(newUl);
             firstDiv.appendChild(ingredients);
             newArticle.appendChild(firstDiv);
             newArticle.appendChild(divPreparation);
             e.target.innerHTML = "";
             e.target.appendChild(newArticle);

         }
     }
}
window.addEventListener('load', () => {
    solve();
})