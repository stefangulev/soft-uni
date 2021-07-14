const form = document.querySelector('form').addEventListener('submit', createStudent);
async function createStudent(e) {
    e.preventDefault();
    const data = new FormData(e.target);
    const firstName = data.get('firstName');
    const lastName = data.get('lastName');
    const number = data.get('number');
    const grade = data.get('grade');
    if (firstName.trim() === "" || lastName.trim() === "" || number.trim() === "" || grade.trim() === "" ) {
        return alert("All fields are mandatory");
    }

    const response = await fetch('http://localhost:3030/jsonstore/collections/students', {
        method: 'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({"firstName": firstName,"lastName": lastName, "facultyNumber": number, "grade": grade}),
    });
    extractStudnets();
}

async function extractStudnets() {
    const table = document.querySelector('tbody');
    table.innerHTML = "";
    const response = await fetch('http://localhost:3030/jsonstore/collections/students');
    const data = await response.json();
    Object.values(data).map(createTableRow).forEach(r => table.appendChild(r));
}

function createTableRow(entry) {
    const result = e('tr', {}, e('td', {}, entry.firstName),e('td', {}, entry.lastName),e('td', {}, entry.facultyNumber),e('td', {}, entry.grade));
    return result;
}
function e(type, attributes, ...content) {
    const result = document.createElement(type);

    for (let [attr, value] of Object.entries(attributes || {})) {
        if (attr.substring(0, 2) == 'on') {
            result.addEventListener(attr.substring(2).toLocaleLowerCase(), value);
        } else {
            result[attr] = value;
        }
    }

    content = content.reduce((a, c) => a.concat(Array.isArray(c) ? c : [c]), []);

    content.forEach(e => {
        if (typeof e == 'string' || typeof e == 'number') {
            const node = document.createTextNode(e);
            result.appendChild(node);
        } else {
            result.appendChild(e);
        }
    });

    return result;
}