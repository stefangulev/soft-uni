function attachGradientEvents() {
    let gradient = document.getElementById("gradient-box");
    let result = document.getElementById("result");

    gradient.addEventListener("mousemove", onMove);

    function onMove(ev) {
        const offsetX = ev.offsetX;
        const percent = Math.floor(offsetX / ev.target.clientWidth * 100);
        result.textContent = `${percent}%`;
    }


}