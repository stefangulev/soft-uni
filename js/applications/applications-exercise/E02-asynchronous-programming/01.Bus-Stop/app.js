async function getInfo() {
    const input = document.querySelector("#stopId");
    const stopName =document.querySelector("#stopName");
    const list =document.querySelector("#buses");
    list.innerHTML = "";

    try {
        let url = `http://localhost:3030/jsonstore/bus/businfo/${input.value}`
        let response = await fetch(url);
        let data = await response.json();
        stopName.textContent = data.name;
        Object.entries(data.buses).map(([bus, time]) => `Bus ${bus} arrives in ${time}`).forEach(b => list.appendChild(e('li', {}, b)));
        input.value = "";
    } catch(error) {
        stopName.textContent = 'Error';
    }
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