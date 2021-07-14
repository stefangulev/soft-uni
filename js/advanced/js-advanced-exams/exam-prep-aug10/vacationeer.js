class Vacationer {
    constructor(fullName, creditCard) {
        this.fullName = fullName;
        this.creditCard = creditCard;
        this.wishList = [];
        this.idNumber = this.generateIDNumber();
}
get creditCard() {
    return this._creditCard;
}
set creditCard(creditCard) {
    if (!creditCard) {
        this._creditCard = {
            cardNumber: 1111,
            expirationDate: "",
            securityNumber: 111,
        }
    } else {
        this.addCreditCardInfo(creditCard);
    }
}

get fullName() {
    return this._fullName;
}
set fullName(fullName) {
    if (fullName.length !== 3) {
        throw new Error("Name must include first name, middle name and last name"); 
    }

   if(fullName.some(e => !/^[A-Z]{1}[a-z]+$/.test(e))) {
       throw new Error("Invalid full name");
   }
   this._fullName = {
    firstName: fullName[0],
    middleName: fullName[1],
    lastName: fullName[2],
}
}
generateIDNumber() {
    let result = (231 * this.fullName.firstName.charCodeAt(0)) + (139 * this.fullName.middleName.length);
    let vowels = ["a", "e", "o", "i", "u"];
    let finalDigit = vowels.some(e=> e === this.fullName.lastName.charAt(this.fullName.lastName.length -1)) ? 8 : 7;
    return result.toString() + finalDigit;
}
addCreditCardInfo(input) {
    if (input.length !==3) {
        throw new Error('Missing credit card information');
    }
    if (typeof input[0] !== 'number' || typeof input[2] !== 'number') {
        throw new Error('Invalid credit card details');
    }
    this._creditCard = {
        cardNumber: Number(input[0]),
        expirationDate: input[1],
        securityNumber: Number(input[2]),
    }
}
addDestinationToWishList(destination) {
    if (this.wishList.some(e => e === destination)) {
        throw new Error("Destination already exists in wishlist");
    }
    this.wishList.push(destination);
    this.wishList.sort((l,r) => l.length - r.length);
}
getVacationerInfo() {
    return `Name: ${this.fullName.firstName} ${this.fullName.middleName} ${this.fullName.lastName}\nID Number: ${this.idNumber}\nWishlist:\n${this.wishList.length ===0 ? 'empty' : this.wishList.join(', ')}\nCredit Card:\nCard Number: ${this.creditCard.cardNumber}\nExpiration Date: ${this.creditCard.expirationDate}\nSecurity Number: ${this.creditCard.securityNumber}`
}

}

// Initialize vacationers with 2 and 3 parameters
let vacationer1 = new Vacationer(["Vania", "Ivanova", "Zhivkova"]);
let vacationer2 = new Vacationer(["Tania", "Ivanova", "Zhivkova"], 
[123456789, "10/01/2018", 777]);

// Should throw an error (Invalid full name)
try {
    let vacationer3 = new Vacationer(["Vania", "Ivanova", "ZhiVkova"]);
} catch (err) {
    console.log("Error: " + err.message);
}

// Should throw an error (Missing credit card information)
try {
    let vacationer3 = new Vacationer(["Zdravko", "Georgiev", "Petrov"]);
    vacationer3.addCreditCardInfo(['levski', "20/10/2018", 123]);
} catch (err) {
    console.log("Error: " + err.message);
}
vacationer1.addDestinationToWishList('Spain');
vacationer1.addDestinationToWishList('Germany');
vacationer1.addDestinationToWishList('Bali');
console.log(vacationer1.getVacationerInfo());
console.log(vacationer2.getVacationerInfo());
