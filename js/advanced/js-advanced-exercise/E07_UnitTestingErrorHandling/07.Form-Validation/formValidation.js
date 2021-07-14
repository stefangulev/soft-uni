function validate() {
    let checkbox = document.getElementById("company")
    let companyFieldSet = document.getElementById("companyInfo");
    checkbox.addEventListener('change', onChange) 

    function onChange(ev) {
        console.log(ev);
            if (ev.target.checked) {
                companyFieldSet.style.display = "block";
            } else {
                companyFieldSet.style.display = "none";
            }
    }

    let username = document.getElementById("username");
    let password = document.getElementById("password");
    let confirmPassword = document.getElementById("confirm-password");
    let email = document.getElementById("email");
    let submitBtn = document.getElementById("submit").addEventListener("click", (event) => {
        event.preventDefault();
        workElement(/^[A-Za-z0-9]{3,20}$/, username);
        if(!/^[A-Za-z0-9\_]{5,15}$/.test(password.value) || password.value != confirmPassword.value) {
            password.style.borderColor = 'red';
            confirmPassword.style.borderColor = 'red';
            
        } else {
            password.style.borderStyle = 'none';
            confirmPassword.style.borderStyle = 'none';
        }
        workElement(/.*@.*\.{1,}.*/, email);
        

        
    });

   
function workElement(regex, element) {
    if (!regex.test(element.value)) {
        element.style.borderColor = 'red';
    } else {
        element.style.borderStyle = 'none';
    }
}
    
}
