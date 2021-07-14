class Bank {
    constructor(bankName) {
        this.bankName = bankName;
        this.allCustomers = [];
    }

    get bankName() {
        return this._bankName;
    }
    set bankName(bankName) {
        this._bankName = bankName;
    }
    
    newCustomer({firstName, lastName, personalId}) {
        if (this.allCustomers.some(e => e.firstName === firstName && e.lastName === lastName && e.personalId === personalId)) {
            throw new Error(`${firstName} ${lastName} is already our customer!`);
        }
        this.allCustomers.push({firstName, lastName, personalId});
        return {firstName, lastName, personalId};
    }
    depositMoney(personalID, amount) {
        let customer = this.allCustomers.find(e => e.personalId === personalID)
        if (!customer) {
            throw new Error('We have no customer with this ID!');
        }
        if (!customer['totalMoney']) {
            customer['totalMoney'] = amount;
        } else {
            customer['totalMoney'] += amount;
        }
        if (!customer['transactionInfo']) {
            customer['transactionInfo'] = [];
        }
        customer['transactionInfo'].unshift(`${customer['transactionInfo'].length + 1}. ${customer.firstName} ${customer.lastName} made deposit of ${amount}$!`);
        return `${customer.totalMoney}$`
    }
    withdrawMoney(personalID, amount) {
        let customer = this.allCustomers.find(e => e.personalId === personalID)
        if (!customer) {
            throw new Error('We have no customer with this ID!');
        }
        if (customer.totalMoney < amount) {
            throw new Error(`${customer.firstName} ${customer.lastName} does not have enough money to withdraw that amount!`)
        }
        
        customer['totalMoney'] -= amount;
        
        if (!customer['transactionInfo']) {
            customer['transactionInfo'] = [];
        }
        customer['transactionInfo'].unshift(`${customer['transactionInfo'].length + 1}. ${customer.firstName} ${customer.lastName} withdrew ${amount}$!`);
        return `${customer.totalMoney}$`
    }
    customerInfo(personalID) {
        let customer = this.allCustomers.find(e => e.personalId === personalID)
        if (!customer) {
            throw new Error('We have no customer with this ID!');
        }
        return `Bank name: ${this.bankName}\nCustomer name: ${customer.firstName} ${customer.lastName}\nCustomer ID: ${customer.personalId}\nTotal Money: ${customer.totalMoney}$\nTransactions:\n${customer.transactionInfo.join('\n')}`;
    }
    

}
let bank = new Bank('SoftUni Bank');

console.log(bank.newCustomer({firstName: 'Svetlin', lastName: 'Nakov', personalId: 6233267}));
console.log(bank.newCustomer({firstName: 'Mihaela', lastName: 'Mileva', personalId: 4151596}));
bank.depositMoney(6233267, 250);
console.log(bank.depositMoney(6233267, 250));
bank.depositMoney(4151596,555);
console.log(bank.withdrawMoney(6233267, 125));

console.log(bank.customerInfo(6233267));


