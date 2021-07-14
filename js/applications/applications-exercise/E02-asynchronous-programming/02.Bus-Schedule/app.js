function solve() {
    const info = document.querySelector('#info');
    const departBtn = document.querySelector('#depart');
    const arriveBtn = document.querySelector('#arrive');
    let idOfNextStop = 'depot';
    let currentStop =""

    async function depart() {
        const startStop = `http://localhost:3030/jsonstore/bus/schedule/${idOfNextStop}`;
        const response = await fetch(startStop);
        const data = await response.json();
        info.textContent = `Next stop ${data.name}`
        departBtn.disabled = true;
        arriveBtn.disabled = false;
        idOfNextStop = data.next;
        currentStop = data.name;
    }

    function arrive() {
        console.log('arrive');
        info.textContent = `Arriving at ${currentStop}`
        departBtn.disabled = false;
        arriveBtn.disabled = true;
    }

    return {
        depart,
        arrive
    };
}

let result = solve();