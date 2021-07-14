function solve() {
  let textToModify = document.getElementById("text").value.toLowerCase().split(" ");
  let type = document.getElementById("naming-convention");
  let result = document.getElementById("result");
  result.textContent = textToModify.map(x=> x[0].toUpperCase() + x.slice(1)).join("");
  

  if (type.value === "Camel Case") {
    result.textContent = result.textContent[0].toLowerCase() + result.textContent.slice(1);
  } else if (type.value !== "Pascal Case") {
    result.textContent = "Error!";
  } 
}