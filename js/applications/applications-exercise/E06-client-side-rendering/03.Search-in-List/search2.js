//works only if index.html contains article;
import {towns} from './towns.js';
import { html, render } 
	from '../node_modules/lit-html/lit-html.js';

function search() {
   const main = document.getElementById('towns');
   let townsLi = towns.map(liConvertor);
   

   render(showTowns(townsLi), main);
   

   const button = document.querySelector('button');
   button.addEventListener('click', () => {
      const liList = Array.from(document.querySelector('#towns ul').children);
      liList.forEach(e => e.classList.remove('active'));
      const val = document.getElementById('searchText').value;
      liList.forEach(e => {
         if (e.textContent.includes(val)) {
            e.classList.add('active');
         }
      })
      const div = document.getElementById('result');
      div.textContent = `${countActive(liList)} matches found`
   })
   
}
function countActive(arr) {
   let count = 0;
   arr.forEach(e => {
      if (e.classList.contains('active')) {
         count ++;
      }
   });
   return count;
}

function showTowns(towns) {
   const townsTemplate = html`
   <ul>
      ${towns};
   </ul>
   `
   return townsTemplate;
}

function liConvertor(town) {
   const li = html`
   <li>${town}</li>
   `
   return li;
}

window.addEventListener('load', () => search());



