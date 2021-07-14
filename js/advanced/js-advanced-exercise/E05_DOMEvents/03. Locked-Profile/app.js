function lockedProfile() {
    let buttons = Array.from(document.getElementsByTagName("button")).forEach(x => x.addEventListener("click", onClick));
    let lockFields = Array.from(document.querySelectorAll('input[value=lock]'));
    let hiddenFields = Array.from(document.getElementsByTagName("div")).filter(x => x.className !== "profile" && x.id !== "container");
  
    function onClick(event) {
        for (let field of lockFields) {
            if(event.target.parentElement === field.parentElement) {
                if (!field.checked) {
                    for (let hidden of hiddenFields) {
                        if (event.target.parentElement === hidden.parentElement) {
                            if (event.target.textContent === "Show more") {
                                hidden.style.display = "block";
                            event.target.textContent = "Hide it";
                            return;
                            } else if (event.target.textContent === "Hide it") {
                                hidden.style.display = "none";
                            event.target.textContent = "Show more";
                            return;
                            }
                            
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }


}