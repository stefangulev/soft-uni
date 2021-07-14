function sumTable() {
// let elements = Array.from(document.querySelectorAll("td:nth-child(even)")).slice(0, -1);
// let totalSum = 0;
// for(let el of elements) {
//     totalSum += Number(el.textContent);
// }
// let result = document.querySelector("#sum");
// result.textContent = totalSum;
// let rows = document.querySelectorAll("table tr");

// let sum = 0;

// for(let i = 1; i < rows.length - 1; i++) {
//     let cols = rows[i].children;
//     sum += Number(cols[cols.length-1].textContent);
// }
// let result = document.querySelector("#sum");
// result.textContent = sum;
const rows = [...document.querySelectorAll("table tr")].slice(1, -1);
document.getElementById('sum').textContent = rows.reduce((sum, row) => {
    return sum + Number(row.lastElementChild.textContent);
}, 0);

}