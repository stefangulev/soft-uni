function solve() {
   const btn = document.getElementsByTagName("button")[0];
   let inputs = Array.from(document.querySelectorAll("input"));
   let nameInput = inputs[0];
   let dateInput = inputs[1];
   let selectInput = document.getElementsByTagName("select")[0];
   let modules = document.querySelector(".modules");
   modules.addEventListener('click', deleteEl);

   function deleteEl(e) {
       e.preventDefault();
       if (e.target.nodeName === 'BUTTON') {
           let list = e.target.parentElement.parentElement;
           list.removeChild(e.target.parentElement);
           if (!list.firstChild) {
               list.parentElement.parentElement.removeChild(list.parentElement);
           }
       }
   }


   
  

   btn.addEventListener('click', onClick);


   function onClick(e) {
       e.preventDefault();
       if (nameInput.value != "" && dateInput.value != "" && selectInput.value != "Select module") {
        let doesExist = false;
        let targetDiv = undefined;
           let createdModukes = Array.from(modules.getElementsByTagName('h3')).forEach(h => {
               if(h.textContent === selectInput.value.toUpperCase() + "-MODULE") {
                   doesExist = true;
                   targetDiv = h.parentElement;
               }
           });
           if (doesExist === false) {
            let newDiv = document.createElement("div");
            newDiv.classList.add('module');
            let newHeading = document.createElement('h3');
            newHeading.textContent = selectInput.value.toUpperCase() + "-MODULE";
            let newList = document.createElement('ul');
            let newLi = document.createElement('li');
            newLi.classList.add('flex');
            let newH4 = document.createElement('h4');
            let dataArr = dateInput.value.split("T");
            while(dataArr[0].includes('-')) {
                dataArr[0] = dataArr[0].replace('-','/');
            }
            let result = dataArr.join(' - ');
            newH4.textContent = `${nameInput.value} - ${result}`;
            let delBtn = document.createElement('button');
            delBtn.classList.add('red');
            delBtn.textContent = "Del";
            newLi.appendChild(newH4);
            newLi.appendChild(delBtn);
            newList.appendChild(newLi);
            newDiv.appendChild(newHeading);
            newDiv.appendChild(newList);
            modules.appendChild(newDiv);
           } else {
               let listToUse = targetDiv.getElementsByTagName('ul')[0];
               let newLi = document.createElement('li');
            newLi.classList.add('flex');
            let newH4 = document.createElement('h4');
            let dataArr = dateInput.value.split("T");
            while(dataArr[0].includes('-')) {
                dataArr[0] = dataArr[0].replace('-','/');
            }
            let result = dataArr.join(' - ');
            newH4.textContent = `${nameInput.value} - ${result}`;
            let delBtn = document.createElement('button');
            delBtn.classList.add('red');
            delBtn.textContent = "Del";
            newLi.appendChild(newH4);
            newLi.appendChild(delBtn);
            listToUse.appendChild(newLi);
            let arr = Array.from(listToUse.getElementsByTagName('li')).sort((l,r) => l.getElementsByTagName('h4')[0].textContent.split(' - ')[1].localeCompare(r.getElementsByTagName('h4')[0].textContent.split(' - ')[1]));
            while(listToUse.firstChild) {
                listToUse.removeChild(listToUse.lastChild);
            }
            for(let el of arr) {
                listToUse.appendChild(el);
            }
           }
           
       }
   }
};