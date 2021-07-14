function toggle() {
    let button = document.querySelector(".button");
    let text = document.querySelector("#extra");
   text.style.display = text.style.display === 'block' ? 'none' : "block"; 
   button.textContent = button.textContent === "Less" ? 'More' : "Less"; 

    // if (text.style.display === 'none' || text.style.display === "") {
    //     text.style.display = 'block';
    //     button.textContent = 'Less';
    // } else if (text.style.display === 'block') {
    //     text.style.display = 'none';
    //     button.textContent = 'More';
    // }
}