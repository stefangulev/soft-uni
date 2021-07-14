function deck(arr) {
    function cards(card) {
        let [face, suit, third] = card.split("");
        if (!isNaN(Number(suit))) {
            face += suit;
            suit = third;
        }
        let faces = ["2","3","4","5","6","7","8","9","10","J","Q","K","A"];
        let suits = {
            "S": "\u2660",
            "H": "\u2665",
            "D": "\u2666",
            "C": "\u2663"
        }
    
        if (!faces.includes(face.toUpperCase()) || suits[suit.toUpperCase()] === undefined ) {
            throw new Error(`Invalid card: ${card}`);
        } else {
            return `${face.toUpperCase()}${suits[suit]}`;
        }
    }
    
    try {
     let result = arr.map(c => cards(c)).join(" ");
     console.log(result);
    } catch(ex){
        console.log(ex.message);
    }
}
deck(['AS', '10D', 'KH', '2C']);
deck(['5S', '3D', 'QD', '1C']);
