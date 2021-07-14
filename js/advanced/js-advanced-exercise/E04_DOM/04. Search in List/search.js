function search() {
   let searchInput = document.getElementById("searchText").value;
   let towns = Array.from(document.getElementById("towns").children);
   let result = document.getElementById("result");
   let matchesCount = 0;
   for (let element of towns) {
      if (element.textContent.includes(searchInput) && searchInput != "") {
         matchesCount++;
         element.style.textDecoration = "underline";
         element.style.fontWeight = "bold";
      }
   }
   result.textContent = `${matchesCount} matches found`;


}
