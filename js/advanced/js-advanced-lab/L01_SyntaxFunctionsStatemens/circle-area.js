function circleArea(arg) {
    if (typeof arg !== 'number') {
        console.log(`We can not calculate the circle area, because we receive a ${typeof arg}.`);
    } else {
        console.log((Math.PI * arg**2).toFixed(2));
    }

}

circleArea(true);