function extractText() {
    let elements = Array.from(document.getElementsByTagName("li")).map(x => x.textContent);
    let textArea = document.getElementById("result");
    textArea.value = elements.join('\n');
}