class Person {
    constructor(first, last) {
        this.firstName = first;
        this.lastName = last;
    }

    get fullName() {
        return `${this.firstName} ${this.lastName}`;
    }
    set fullName(value) {
        if (value.split(" ").length === 2) {
            let[first, second] = value.split(" ");
            this.firstName = first;
            this.lastName = second;
        }
        
    }
}

let person = new Person("Peter", "Ivanov");
console.log(person.fullName);
person.fullName = "Ivan Gosho";
console.log(person.fullName);

