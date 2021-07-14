

const div = document.createElement('div');
div.classList.add('notifications');
const main = document.querySelector('main')

export function notify(message) {
    div.textContent = message;
    main.appendChild(div);
    setTimeout(() => main.removeChild(div), 3000);
}