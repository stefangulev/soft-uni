function solve(){
   let container = document.querySelector(".site-content");
   const [posts, create, archive] = Array.from(container.querySelectorAll('section'));
   const [author, title, category] = Array.from(create.querySelectorAll('input'));
   const content = create.querySelector('textarea');
   let createBtn = create.querySelector('button');
   createBtn.addEventListener('click', createFnc);

   function deleteArticle(e) {
      e.target.parentNode.parentNode.remove();
   }
   function archiveArticle(e) {
      let currentArticle = e.target.parentNode.parentNode;
      let title = currentArticle.querySelector('h1').textContent;
      let listToAdd = archive.querySelector('ol');
      let newLi = createElement('li', title);
      listToAdd.appendChild(newLi);
      currentArticle.remove();
     let arr = Array.from(listToAdd.children).sort((l,r) => l.textContent.localeCompare(r.textContent));
     for (let el of arr) {
        listToAdd.appendChild(el);
     }

   }

   function createFnc(e) {
      e.preventDefault();
      if (author.value != "" && title.value != "" && category.value != "" && content.value != "") {
         let newArticle = createElement('article');
         let heading = createElement('h1', title.value);
         newArticle.appendChild(heading);
         let categoryP = createElement('p', 'Category:');
         let categoryStrong = createElement('strong', category.value);
         categoryP.appendChild(categoryStrong);
         newArticle.appendChild(categoryP);
         let creatorP = createElement('p', 'Creator:');
         let creatorStrong = createElement('strong', author.value);
         creatorP.appendChild(creatorStrong);
         newArticle.appendChild(creatorP);
         let contentP = createElement('p', content.value);
         newArticle.appendChild(contentP);
         let buttonsDiv = document.createElement('div');
         buttonsDiv.classList.add('buttons');
         let deleteBtn = document.createElement('button');
         deleteBtn.textContent = 'Delete';
         deleteBtn.classList.add('btn', 'delete');
         deleteBtn.addEventListener('click', deleteArticle);
         let archiveBtn = document.createElement('button');
         archiveBtn.textContent = 'Archive';
         archiveBtn.classList.add('btn', 'archive');
         archiveBtn.addEventListener('click', archiveArticle);
         buttonsDiv.appendChild(deleteBtn);
         buttonsDiv.appendChild(archiveBtn);
         newArticle.appendChild(buttonsDiv);
         posts.appendChild(newArticle);
         author.value = "";
         title.value = "";
         category.value = "";
         content.value = "";
      }
   }


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
  }