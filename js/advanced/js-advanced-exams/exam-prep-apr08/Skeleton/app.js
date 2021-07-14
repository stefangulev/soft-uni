function solve() {
    const [addTask, open, inProgress, complete] = document.querySelectorAll('section');
    const [taskInput, dateInput] = addTask.querySelectorAll('input');
    const descriptionInput = addTask.querySelector('textarea');
    const addBtn = addTask.querySelector('button');
    addBtn.addEventListener('click', add);

    function add(e) {
        e.preventDefault();
        if (taskInput.value != "" && dateInput.value != "" && descriptionInput.value != "") {
            let newArticle = createElement('article');
            let heading = createElement('h3', taskInput.value);
            let description = createElement('p', `Description: ${descriptionInput.value}`);
            let date = createElement('p', `Due Date: ${dateInput.value}`);
            let div = createElement('div', null, 'flex');
            let startBtn = createElement('button', 'Start', 'green');
            let deleteBtn = createElement('button', 'Delete', 'red');
            newArticle.appendChild(heading);
            newArticle.appendChild(description);
            newArticle.appendChild(date);
            div.appendChild(startBtn);
            div.appendChild(deleteBtn);
            newArticle.appendChild(div);
            newArticle.addEventListener('click', moveArticlesFromOpen);
            open.querySelectorAll('div')[1].appendChild(newArticle);
        }
    }

   function moveArticlesFromOpen(e) {
        if (e.target.nodeName === "BUTTON") {
            if (e.target.textContent === "Delete") {
                e.target.parentNode.parentNode.remove();
            } else if (e.target.textContent === 'Start') {
               let newArticle = e.target.parentNode.parentNode.cloneNode(true);
               let newButton = newArticle.querySelector('.green');
               newButton.classList.remove('green');
               newButton.classList.add('orange');
               newButton.textContent = 'Finish';
               newArticle.querySelector('div').appendChild(newButton);
               newArticle.addEventListener('click', moveArticlesFromInProgress);
               inProgress.querySelectorAll('div')[1].appendChild(newArticle);
               e.target.parentNode.parentNode.remove();
            } 
        }
    }
    function moveArticlesFromInProgress(e) {
        if (e.target.nodeName === "BUTTON") {
            if (e.target.textContent === "Delete") {
                e.target.parentNode.parentNode.remove();
            } else if (e.target.textContent === "Finish") {
                let newArticle = e.target.parentNode.parentNode.cloneNode(true);
                let currentDiv = newArticle.querySelector('div');
                newArticle.removeChild(currentDiv);
                complete.querySelectorAll('div')[1].appendChild(newArticle);
                e.target.parentNode.parentNode.remove();
            }
    }
}



    function createElement(type, content, className) {
        const element = document.createElement(type);
        if (content) {
            element.textContent = content;
        }
        if (className) {
            element.classList.add(className);
        }
        return element;
    }
}