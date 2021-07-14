const form = document.querySelector('form').addEventListener('submit', submitForm);

  async  function submitForm(e) {
        e.preventDefault();
        let data = new FormData(e.target);
        
        const email = data.get('email');
        const password = data.get('password');
        const rePass = data.get('rePass');

        if (email === "" || password === "") {
            return alert ("All fields are required");
        } else if (password != rePass) {
            return alert ("Passwords don\'t match");
        }

        const response = await fetch('http://localhost:3030/users/register', {
            method: 'post',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({email, password})
        })
        if (!response.ok) {
            const error = await response.json();
            return alert(error.message);
        }
        const result = await response.json();
        sessionStorage.setItem('authToken', result.accessToken);
        window.location.pathname = 'index.html' 
    }