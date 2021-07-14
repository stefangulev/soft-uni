function cards(face, suit) {
    let faces = ["2","3","4","5","6","7","8","9","10","J","Q","K","A"];
    let suits = {
        "S": "\u2660",
        "H": "\u2665",
        "D": "\u2666",
        "C": "\u2663"
    }

    if (!faces.includes(face.toUpperCase()) || suits[suit.toUpperCase()] === undefined ) {
        throw new Error;
    } else {
        return `${face.toUpperCase()}${suits[suit]}`;
    }
}
console.log(cards('A', 'S'));
console.log(cards('10', 'H'));
console.log(cards('1', 'C'));