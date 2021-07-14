function solve() {
  let textArea = document.getElementById("input");
  let output = document.getElementById("output");
  let inputArray = textArea.value.split(".").filter(x => x.length >= 1);

  while(inputArray.length) {
    const p = document.createElement('p');
    const currentArr = inputArray.splice(0, 3);

    for(let el of currentArr) {
      if (el === undefined) {
        break;
      }

      p.textContent += el + '.';
    }

    output.appendChild(p);
  }

}