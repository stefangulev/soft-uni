function deleteByEmail() {
    let input = document.querySelector
    ('input[name="email"]').value;

    let rows = Array.from
    (document.querySelectorAll("tbody tr"));


    let deleted = false;
    for (let el of rows) {
        if (el.textContent.includes(input)) {
            let parent = el.parentNode;
            parent.removeChild(el);
            deleted = true;
        }
    }

    let result = document.getElementById("result");
    result.textContent = "Not found.";
    if(deleted) {
        result.textContent = "Deleted.";
    }
}