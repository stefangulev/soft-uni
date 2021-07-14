function tickets(arr, criteria) {
    class Ticket {
        constructor(destination, price, status) {
            this.destination = destination;
            this.price = Number(price);
            this.status = status;
        }
    }

    const sortObj = {
        'destination': (a,b) => a.destination.localeCompare(b.destination),
        'price': (a,b) => a.price - b.price,
        'status': (a,b) => a.status.localeCompare(b.status),
    }
    
    return arr.map(e => {
        let curr = e.split("|");
        return new Ticket (curr[0],curr[1],curr[2])
    }).sort(sortObj[criteria]);
}
console.log(tickets(['Philadelphia|94.20|available',
'New York City|95.99|available',
'New York City|95.99|sold',
'Boston|126.20|departed'],
'price'));
