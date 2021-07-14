function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);
   let rows = Array.from(document.querySelectorAll("tbody tr"));

   function onClick() {
      let input = document.getElementById("searchField").value;

      for(let element of rows) {
         if (element.textContent.includes(input)) {
            element.classList.add('select');
         } else {
            element.removeAttribute('class');
         }
      }

      

   }
}