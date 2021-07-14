function starRectangle(number = 5) {
    for (index = 0; index < number; index++) {
        let symbol = '* '.repeat(number).trim();
        console.log(symbol);
    }
}

starRectangle(3);