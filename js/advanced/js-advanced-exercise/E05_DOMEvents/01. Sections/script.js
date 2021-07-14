function create(words) {
   let resultDiv = document.getElementById("content");
   words.forEach(x => {
      let currentDiv = document.createElement("div");
      currentDiv.addEventListener("click", onClick);
      let p = document.createElement("p");
      p.textContent = x;
      p.style.display = "none";
      currentDiv.appendChild(p);
      resultDiv.appendChild(currentDiv);
   });
   

   function onClick(event) {
      event.target.childNodes[0].style.display = "block";
   }
}