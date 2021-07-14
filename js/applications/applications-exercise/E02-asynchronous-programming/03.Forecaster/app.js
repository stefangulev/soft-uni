function attachEvents() {
    const submitBtn = document.querySelector('#submit');
    submitBtn.addEventListener('click', onClick);
    const input = document.querySelector('#location');
    const forecast = document.querySelector('#forecast');
    

    async function onClick() {
        forecast.style.display = 'block';
        const current = document.querySelector('#current');
        const upcoming = document.querySelector('#upcoming');
        try {
            const url = 'http://localhost:3030/jsonstore/forecaster/locations';
            const response = await fetch(url);
            const data = await response.json();
            let currentCity = data.find(e => e.name === input.value);
            if (!currentCity) {
                throw new Error();
            }
            const currentCondUrl = `http://localhost:3030/jsonstore/forecaster/today/${currentCity.code}`
            const currentCondResponse = await fetch(currentCondUrl);
            const currentCondData = await currentCondResponse.json();
            current.appendChild(createCurrentForecastElement(currentCondData));
            
            const threeDaysCondUrl = `http://localhost:3030/jsonstore/forecaster/upcoming/${currentCity.code}`;
            const threeDaysCondResponse = await fetch(threeDaysCondUrl);
            const threeDaysCondData = await threeDaysCondResponse.json();
            let actualData = threeDaysCondData.forecast;
            let div = e('div', {className: 'forecast-info'});
            actualData.map(createUpcomingForecastElement).forEach(e=> div.appendChild(e));
            upcoming.appendChild(div);
            current.querySelector('.label').textContent = "Current conditions";
        }catch {
            current.querySelector('.label').textContent = "Error";
        }
      input.value = "";
    }

    function createUpcomingForecastElement(upcomingData) {
        const result = e('span', {className: 'upcoming'}, e('span', {className:'symbol'}, displaySymbol(upcomingData.condition)), e('span', {className: 'forecast-data'}, `${upcomingData.low}${displaySymbol("Degrees")}/${upcomingData.high}${displaySymbol('Degrees')}`),e('span', {className: 'forecast-data'}, upcomingData.condition));
        return result;
    }
    function createCurrentForecastElement(currentData) {
        const result = e('div', {className: 'forecasts'}, e('span', {className: "condition symbol"}, displaySymbol(currentData.forecast.condition)),e('span', {className: 'condition'}, e('span', {className:'forecast-data'}, currentData.name),e('span', {className:'forecast-data'}, `${currentData.forecast.low}${displaySymbol('Degrees')}/${currentData.forecast.high}${displaySymbol('Degrees')}`), e('span', {className:'forecast-data'}, currentData.forecast.condition)));
        return result;
    }
    function displaySymbol(state) {
        let code = "";
        switch(state) {
            case "Sunny": code = "\u{2600}";
            break;
            case "Partly sunny": code = "\u{26C5}"
            break;
            case "Overcast": code = "\u{2601}"
            break;
            case "Rain": code = `\u{2614}`
            break;
            case "Degrees": code = "\u{B0}"
            break;
        }
        return code;
    }
}
function e(type, attributes, ...content) {
    const result = document.createElement(type);

    for (let [attr, value] of Object.entries(attributes || {})) {
        if (attr.substring(0, 2) == 'on') {
            result.addEventListener(attr.substring(2).toLocaleLowerCase(), value);
        } else {
            result[attr] = value;
        }
    }

    content = content.reduce((a, c) => a.concat(Array.isArray(c) ? c : [c]), []);

    content.forEach(e => {
        if (typeof e == 'string' || typeof e == 'number') {
            const node = document.createTextNode(e);
            result.appendChild(node);
        } else {
            result.appendChild(e);
        }
    });

    return result;
}

attachEvents();