function addDestination() {
   const [cityInput, countryInput] = Array.from(document.querySelectorAll("#input input"));
   const seasonInput = document.querySelector("#seasons");
   const table = document.querySelector("#destinationsList");
   const results = document.querySelector("#summaryBox");
   if(cityInput.value && countryInput.value) {
    createTableRow(`${cityInput.value}, ${countryInput.value}`, seasonInput.value);
   }
   function createTableRow(location, season) {
       let tableRow = document.createElement("tr");
       let firstTd = document.createElement("td");
       firstTd.textContent = location;
       let secondTd = document.createElement('td');
       secondTd.textContent = season.charAt(0).toUpperCase() + season.substring(1);
       tableRow.appendChild(firstTd);
       tableRow.appendChild(secondTd);
        table.appendChild(tableRow);  
        cityInput.value = "";
        countryInput.value = "";
        seasonInput.value = seasonInput.firstChild.value;
        results.querySelector("#"+season).value = Number(results.querySelector("#"+season).value) + 1;
   }
} 
