function solve() {
  let buttons = document.getElementsByTagName("button");
  let textAreas = document.getElementsByTagName("textarea");
let firstTextArea = textAreas[0];
let generateBtn = buttons[0];
let table = document.getElementsByTagName("tbody")[0];
let buyBtn = buttons[1];
let secondTextArea = textAreas[1];
generateBtn.addEventListener("click", onClick);
buyBtn.addEventListener("click", buy);

let rows = [];

function buy() {
  let boughtFurniture = Array.from(table.querySelectorAll('input[type=checkbox]:checked'))
  .map(input => input.parentNode.parentNode);
  console.log(boughtFurniture);
  

  const result = {
    bought: [],
    totalPrice: 0,
    decFactorSum:0,
  }

  for (let arr of boughtFurniture) {
    let columns = arr.children;
    result.bought.push(columns[1].textContent);
    result.totalPrice += Number(columns[2].textContent);
    result.decFactorSum += Number(columns[3].textContent);

  }
  secondTextArea.textContent = `Bought furniture: ${result.bought.join(", ")}\nTotal price: ${result.totalPrice.toFixed(2)}\nAverage decoration factor: ${result.decFactorSum / boughtFurniture.length}`
  
  // validRows = rows.filter(x => x.lastChild.children[0].checked === true);
  
  // let names = validRows.map(x => x.children[1].textContent).join(", ");
  // let sumPrice = 0;
  // let decorSum = 0;
  // validRows.forEach(e => {
  //   sumPrice += Number(e.children[2].textContent);
  //   decorSum += Number(e.children[3].textContent);
  // });
  // let averageDecor = decorSum / validRows.length;
  // let result = `Bought furniture: ${names}\nTotal price: ${sumPrice.toFixed(2)}\nAverage decoration factor: ${averageDecor}`
  // secondTextArea.textContent = result;
}

function onClick(ev) {
  let arr = JSON.parse(firstTextArea.value);
  arr.forEach(x => {
    let row = document.createElement("tr");
    let firstTD = document.createElement("td");
    let image = document.createElement("img");
    image.setAttribute('src', x.img);
    firstTD.appendChild(image);
    let secondTD = document.createElement("td");
    let firstP = document.createElement("p");
    firstP.textContent = x["name"];
    secondTD.appendChild(firstP);
    let thirdTD = document.createElement("td");
    let secondP = document.createElement("p");
    secondP.textContent = x["price"];
    thirdTD.appendChild(secondP);
    let fourthTD = document.createElement("td");
    let thirdP = document.createElement("p");
    thirdP.textContent = x["decFactor"];
    fourthTD.appendChild(thirdP);
    let fifthTD = document.createElement("td");
    let input  = document.createElement("input");
    input.setAttribute("type","checkbox");
    fifthTD.appendChild(input);
    row.appendChild(firstTD);
    row.appendChild(secondTD);
    row.appendChild(thirdTD);
    row.appendChild(fourthTD);
    row.appendChild(fifthTD);
    table.appendChild(row);
    rows.push(row);
  });
}

}