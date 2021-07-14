function radar(currentSpeed, area) {
    let speed = Number(currentSpeed);
    let limit;

    switch(area) {
        case "motorway": limit = 130; break;
        case "interstate": limit = 90; break;
        case "city": limit = 50; break;
        case "residential": limit = 20; break;
    }

    let result;

    if (speed <= limit) {
        result = `Driving ${speed} km/h in a ${limit} zone`
    } else {
    let status;
    let difference = speed - limit;
    if (difference <= 20) {
        status = "speeding";
    } else if (difference <=40) {
        status = "excessive speeding"
    } else {
        status = "reckless driving";
    }
    result = `The speed is ${difference} km/h faster than the allowed speed of ${limit} - ${status}`;
    }
    return result;
}

console.log(radar(120, 'interstate'))
