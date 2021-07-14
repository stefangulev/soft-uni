class Parking {
    constructor(capacity){
        this.capacity = capacity;
        this.vehicles = [];
    }

    addCar(carModel, carNumber) {
        if (this.vehicles.length === this.capacity) {
            throw new Error("Not enough parking space.");
        }
        const car = {
            carModel: carModel,
            carNumber: carNumber,
            payed: false
        }
        this.vehicles.push(car);
        return `The ${carModel}, with a registration number ${carNumber}, parked.`
    }
    removeCar(carNumber) {
        if (!this.vehicles.find(e => e.carNumber === carNumber)) {
            throw new Error("The car, you're looking for, is not found.");
        }

        if(this.vehicles.find(e => e.carNumber === carNumber).payed === false) {
            throw new Error(`${carNumber} needs to pay before leaving the parking lot.`);
        }

        this.vehicles = this.vehicles.splice(this.vehicles.findIndex(e =>e.carNumber === carNumber), 1);
        return `${carNumber} left the parking lot.`
    }
    pay(carNumber) {
        if (!this.vehicles.find(e => e.carNumber === carNumber)) {
            throw new Error(`${carNumber} is not in the parking lot.`);
        }
        if(this.vehicles.find(e => e.carNumber === carNumber).payed === true) {
            throw new Error(`${carNumber}'s driver has already payed his ticket.`);
        }
        this.vehicles.find(e => e.carNumber === carNumber).payed = true;
        return `${carNumber}'s driver successfully payed for his stay.`
    }
    getStatistics(carNumber) {
        if (carNumber) {
           let car = this.vehicles.find(e =>e.carNumber === carNumber);
          return `${car.carModel} == ${car.carNumber} - ` + (car.payed ? 'Has payed' : 'Not payed');
        } else {
            return `The Parking Lot has ${this.capacity - this.vehicles.length} empty spots left.\n` + this.vehicles.sort((l,r)=> l.carModel.localeCompare(r.carModel)).map(v =>`${v.carModel} == ${v.carNumber} - ` + (v.payed ? 'Has payed' : 'Not payed')).join('\n');
        }
    }
}
const parking = new Parking(12);

console.log(parking.addCar("Volvo t600", "TX3691CA"));
console.log(parking.addCar("Mercedes t600", "CB3691CA"));
console.log(parking.getStatistics());

console.log(parking.pay("TX3691CA"));
console.log(parking.removeCar("TX3691CA"));
