function extract(content) {
let element = document.getElementById(content).textContent;
const regex = /\((.+?)\)/gm;

let matcher = regex.exec(element);
let result = [];

while(matcher != null) {
    result.push(matcher);
    matcher = regex.exec(element);
}

return result.join("; ");
}