async function lockedProfile() {
    let main = document.querySelector("#main");

    const url = `http://localhost:3030/jsonstore/advanced/profiles`;
    const response = await fetch(url);
    const data = await response.json();
    Object.values(data).map(createProfile).forEach(r => main.appendChild(r));



    function createProfile(data) {
        let result = e('div', {className: 'profile'}, 
        e('img', {src:"./iconProfile2.png", className: 'userIcon'}),
        e('label', {}, 'Lock'), e('input', {type: 'radio', value:'lock', name:`${data.username}locked`, checked: true}), e('label', {}, 'Unlock'),e('input', {type: 'radio', name:`${data.username}locked`, value:'unlock'}), e('br', {}), e('hr', {}),e('label', {}, 'Username'), e('input', {type: 'text', value: `${data.username}`, disabled: true, readonly: true}), e('div', {id: `hiddenFields`, hidden: true}, e('hr', {}), e('label', {}, "Email:"), e('input', {type: 'email', name: `${data.username}email`, value: `${data.email}`, disabled: true, readonly: true}), e('br', {}), e('label', {}, 'Age:'), e('br', {}), e('input', {type: 'email', name: `${data.username}age`, value: `${data.age}`, disabled: true, readonly: true})));
        let btn = e('button', {}, 'Show more');
        btn.addEventListener('click', show);
        result.appendChild(btn);
        return result;
    }
    function show(e) {
        console.log('fire')
        let card = e.target.parentNode;
        let lockButtons = card.querySelectorAll("input[type='radio']");
        if (lockButtons[1].checked) {
            let hiddenDiv = card.querySelector('#hiddenFields');
            if (hiddenDiv.hidden) {
                hiddenDiv.hidden = false;
            } else {
                hiddenDiv.hidden = true;
            }
            
        }
        
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