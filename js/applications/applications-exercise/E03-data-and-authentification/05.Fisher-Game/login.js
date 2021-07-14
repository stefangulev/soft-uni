const[registrationForm, loginForm] = Array.from(document.querySelectorAll('form'));
registrationForm.addEventListener("submit", register);
loginForm.addEventListener('submit', login);
async function login(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const email = data.get('email');
    const password = data.get('password');
    const response = await fetch('http://localhost:3030/users/login', {
        method: 'post',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({email,password})
    });
    if (!response.ok) {
        const error = response.json();
        return alert(error.message);
    }
    const result = await response.json();
    const authToken = result.accessToken;
    sessionStorage.setItem('authToken',authToken);
    alert("Welcome back!")
    window.location.pathname = './index.html';
    
}
async function register(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const email = data.get('email');
    const password = data.get('password');
    const rePass = data.get('rePass');

    const response = await fetch('http://localhost:3030/users/register', {
        method: 'post',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({email,password,rePass})
    });
    if (!response.ok) {
        const error = response.json();
        return alert(error.message);
    }
    const result = await response.json();
    const authToken = result.accessToken;
    sessionStorage.setItem('authToken',authToken);
    alert("You've registered successfully!")
    window.location.pathname = './index.html';
}