class Expert{
    constructor(name) {
        this.name = name;
    }
    get name() {
        return this.name;
    }
    set name(name) {
        this.name = name;
    } 
}

let person = new Expert("ivan");
console.log(person.name);